package br.edu.ifsp.projetofinal.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.utils.Constant;
import br.edu.ifsp.projetofinal.utils.UserSession;

public class RequestDaoSQLite implements  IRequestDao{
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public RequestDaoSQLite(Context context){
        mHelper = new SQLiteHelper(context);
    }

    public static String createTable(){
        String sql = "CREATE TABLE " + Constant.REQUEST + "(";
        sql += Constant.DATABASE_ID + " INTEGER PRIMARY KEY, ";
        sql += Constant.ID_USER + " INTEGER, ";
        sql += Constant.ORIGEM + " TEXT NOT NULL, ";
        sql += Constant.DESTINO + " TEXT NOT NULL, ";
        sql += Constant.DATA_VIAGEM + " TEXT NOT NULL, ";
        sql += Constant.STATUS + " TEXT NOT NULL, ";
        sql += Constant.ANEXO_NOTA + " TEXT NOT NULL, ";
        sql += Constant.ANEXO_KM_ANTES + " TEXT NOT NULL, ";
        sql += Constant.ANEXO_KM_DEPOIS + " TEXT NOT NULL, ";
        sql += "FOREIGN KEY (" + Constant.ID_USER + ") REFERENCES " + Constant.REQUEST + "(" + Constant.DATABASE_ID + "));";
        return sql;

    }

    @Override
    public boolean create(Request request) {
        ContentValues values = new ContentValues();
        values.put(Constant.ID_USER, UserSession.getInstance().getUser().getId());
        values.put(Constant.ORIGEM, request.getOrigem());
        values.put(Constant.DESTINO, request.getDestino());
        values.put(Constant.DATA_VIAGEM, request.getDataViagem());
        values.put(Constant.STATUS, request.getStatus());
        values.put(Constant.ANEXO_NOTA, request.getAnexoNotaFiscal());
        values.put(Constant.ANEXO_KM_ANTES, request.getAnexoKmAntes());
        values.put(Constant.ANEXO_KM_DEPOIS, request.getAnexoKmDepois());

        mDatabase = mHelper.getWritableDatabase();
        long lines = mDatabase
                .insert(Constant.REQUEST, null, values);
        mDatabase.close();
        return lines == -1 ? false : true;
    }
    @Override
    public boolean update(String status, Request request) {
        return false;
    }


    @Override
    public List<Request> findAll() {
        if (Objects.equals(UserSession.getInstance().getUser().getUsername(), "")) {
            List<Request> request = new ArrayList<>();
            return request;
        }
        List<Request> list = new ArrayList<>();
        Request request;
        Cursor cursor;

        String columns[] = new String[]{
                Constant.DATABASE_ID,
                Constant.ORIGEM,
                Constant.DESTINO,
                Constant.DATA_VIAGEM,
                Constant.ANEXO_NOTA,
                Constant.ANEXO_KM_ANTES,
                Constant.ANEXO_KM_DEPOIS,
                Constant.STATUS
        };

        mDatabase = mHelper.getReadableDatabase();
        cursor = mDatabase.query(
                Constant.REQUEST,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Constant.DATABASE_ID));
            String origem = cursor.getString(cursor.getColumnIndexOrThrow(Constant.ORIGEM));
            String destino = cursor.getString(cursor.getColumnIndexOrThrow(Constant.DESTINO));
            String dataViagem = cursor.getString(cursor.getColumnIndexOrThrow(Constant.DATA_VIAGEM));
            String anexoNotaFiscal = cursor.getString(cursor.getColumnIndexOrThrow(Constant.ANEXO_NOTA));
            String anexoKmAntes = cursor.getString(cursor.getColumnIndexOrThrow(Constant.ANEXO_KM_ANTES));
            String anexoKmDepois = cursor.getString(cursor.getColumnIndexOrThrow(Constant.ANEXO_KM_DEPOIS));
            String status = cursor.getString(cursor.getColumnIndexOrThrow(Constant.STATUS));
            request = new Request(id, origem, destino, dataViagem, anexoNotaFiscal, anexoKmAntes, anexoKmDepois, status);
            list.add(request);
        }

        cursor.close();
        mDatabase.close();
        return list;
    }
    @Override
    public List<Request> findByUserId() {
        if (Objects.equals(UserSession.getInstance().getUser().getId(), "")) {
            List<Request> request = new ArrayList<>();
            return request;
        }
        List<Request> list = new ArrayList<>();
        Cursor cursor;
        String[] projection = {
                Constant.DATABASE_ID,
                Constant.ID_USER,
                Constant.ORIGEM,
                Constant.DESTINO,
                Constant.DATA_VIAGEM,
                Constant.ANEXO_NOTA,
                Constant.ANEXO_KM_ANTES,
                Constant.ANEXO_KM_DEPOIS,
                Constant.STATUS
        };
        int userId = UserSession.getInstance().getUser().getId();
        Log.v("teste findbyuserid","userId " + userId);
        String selection = Constant.ID_USER + " = ? " + Constant.REQUEST + " = ?";
        String[] selectionArgs = {String.valueOf(userId), "0", "1"};
        mDatabase = mHelper.getReadableDatabase();
        cursor = mDatabase.query(
                Constant.REQUEST,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Constant.DATABASE_ID));
            String idUser = cursor.getString(cursor.getColumnIndexOrThrow(Constant.ID_USER));
            String origem = cursor.getString(cursor.getColumnIndexOrThrow(Constant.ORIGEM));
            String destino = cursor.getString(cursor.getColumnIndexOrThrow(Constant.DESTINO));
            String dataViagem = cursor.getString(cursor.getColumnIndexOrThrow(Constant.DATA_VIAGEM));
            String anexoNotaFiscal = cursor.getString(cursor.getColumnIndexOrThrow(Constant.ANEXO_NOTA));
            String anexoKmAntes = cursor.getString(cursor.getColumnIndexOrThrow(Constant.ANEXO_KM_ANTES));
            String anexoKmDepois = cursor.getString(cursor.getColumnIndexOrThrow(Constant.ANEXO_KM_DEPOIS));
            String status = cursor.getString(cursor.getColumnIndexOrThrow(Constant.STATUS));
            Request requestInsert = new Request(id, idUser, origem, destino, dataViagem, anexoNotaFiscal, anexoKmAntes, anexoKmDepois, status);
            list.add(requestInsert);
        }
        cursor.close();
        mDatabase.close();
        return list;
    }
}

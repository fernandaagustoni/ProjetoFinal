package br.edu.ifsp.projetofinal.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.model.entities.User;
import br.edu.ifsp.projetofinal.utils.Constant;

public class RequestDaoSQLite implements  IRequestDao{
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public RequestDaoSQLite(Context context){
        mHelper = new SQLiteHelper(context);
    }

    public static String createTable(){
        String sql = "CREATE TABLE " + Constant.REQUEST + "(";
        sql += Constant.ID_REQUEST + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += Constant.ORIGEM + " TEXT NOT NULL, ";
        sql += Constant.DESTINO + " TEXT NOT NULL, ";
        sql += Constant.DATA_VIAGEM + " TEXT NOT NULL, ";
        sql += Constant.STATUS + " TEXT NOT NULL, ";
        sql += Constant.ANEXO_NOTA + " TEXT NOT NULL, ";
        sql += Constant.ANEXO_KM_ANTES + " TEXT NOT NULL, ";
        sql += Constant.ANEXO_KM_DEPOIS + " TEXT NOT NULL) ";
        return sql;
    }

    @Override
    public void create(Request request) {
        ContentValues values = new ContentValues();
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
    }
    @Override
    public boolean update(String status, Request request) {
        return false;
    }

    @Override
    public boolean delete(Request request) {
        return false;
    }

    @Override
    public Request findById(String id) {
        return null;
    }

    @Override
    public List<Request> findByUser(User user) {
        return null;
    }

    @Override
    public List<Request> findAll() {
        List<Request> list = new ArrayList<>();
        Request request;
        Cursor cursor;

        String columns[] = new String[]{
                Constant.ORIGEM,
                Constant.DESTINO,
                Constant.DATA_VIAGEM,
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
            request = new Request(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
            list.add(request);
        }
        cursor.close();
        mDatabase.close();
        return list;
    }
}

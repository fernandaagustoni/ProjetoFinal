package br.edu.ifsp.projetofinal.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
        sql += Constant.ID_REQUEST + " INTEGER PRIMARY KEY, ";
        sql += Constant.ORIGEM + " TEXT NOT NULL, ";
        sql += Constant.DESTINO + " TEXT NOT NULL, ";
        sql += Constant.DATA_VIAGEM + " DATE NOT NULL ";
        sql += Constant.ANEXO_NOTA + " BLOB NOT NULL, ";
        sql += Constant.ANEXO_KM_ANTES + " BLOB NOT NULL, ";
        sql += Constant.ANEXO_KM_DEPOIS + " BLOB NOT NULL, ";
        return sql;
    }

    @Override
    public void create(Request request) {

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
        return null;
    }
}

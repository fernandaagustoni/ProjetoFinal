package br.edu.ifsp.projetofinal.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.edu.ifsp.projetofinal.utils.Constant;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "app_reembolso.db";
    public static final int DATABASE_VERSION = 1;
    public SQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = UserDaoSQLite.createTable();
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";

        switch (oldVersion){
            case 1:
                //renomeia a table article para article_old
                sql = "ALTER TABLE " + Constant.USERNAME;
                sql += " RENAME TO " + Constant.USERNAME + "_old";
                db.execSQL(sql);

                //criar a nova table article
                db.execSQL(UserDaoSQLite.createTable());

                //insere todos os dados já cadastrados na tabela nova
                sql = "INSERT INTO " + Constant.USER + " (";
                sql += Constant.USERNAME + ", ";
                sql += Constant.PASSWORD + ", ";
                sql += Constant.IS_ADMIN + ") ";
                sql += "SELECT ";
                sql += Constant.USERNAME + ", ";
                sql += Constant.PASSWORD + ", ";
                sql += Constant.IS_ADMIN;
                sql += " FROM " + Constant.USER + "_old";
                db.execSQL(sql);

                //Apagar tabela antiga
                sql = "DROP TABLE " + Constant.USER + "_old";
                db.execSQL(sql);

        }
    }
}

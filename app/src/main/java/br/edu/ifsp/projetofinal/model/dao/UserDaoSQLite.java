package br.edu.ifsp.projetofinal.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.edu.ifsp.projetofinal.exception.UserDuplicatedException;
import br.edu.ifsp.projetofinal.model.entities.User;
import br.edu.ifsp.projetofinal.utils.Constant;

public class UserDaoSQLite implements IUserDao{
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public UserDaoSQLite(Context context){
        mHelper = new SQLiteHelper(context);
    }

    public static String createTable(){
        String sql = "CREATE TABLE " + Constant.USER + "(";
        sql += Constant.USERNAME + " TEXT PRIMARY KEY, ";
        sql += Constant.PASSWORD + " TEXT NOT NULL, ";
        sql += Constant.IS_ADMIN + " INTEGER NOT NULL ";
        sql += "CHECK(" + Constant.IS_ADMIN + " IN(0, 1)))";
        return sql;
    }

    @Override
    public void create(User user) throws UserDuplicatedException {
        ContentValues values = new ContentValues();
        values.put(Constant.USERNAME, user.getUsername());
        values.put(Constant.PASSWORD, user.getPassword());
        values.put(Constant.IS_ADMIN, user.isIs_admin()?1:0);

        mDatabase = mHelper.getWritableDatabase();
        long lines = mDatabase
                .insert(Constant.USER, null, values);
        mDatabase.close();
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public boolean validateUser(String username, String password) {
        return false;
    }
}
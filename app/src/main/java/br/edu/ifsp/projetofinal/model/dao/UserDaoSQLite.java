package br.edu.ifsp.projetofinal.model.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.edu.ifsp.projetofinal.exception.UserDuplicatedException;
import br.edu.ifsp.projetofinal.model.entities.User;
import br.edu.ifsp.projetofinal.utils.Constant;
import br.edu.ifsp.projetofinal.utils.UserSession;

public class UserDaoSQLite implements IUserDao {
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;
    public UserDaoSQLite(Context context) {
        mHelper = new SQLiteHelper(context);
    }

    public static String createTable() {
        String sql = "CREATE TABLE " + Constant.USER + "(";
        sql += Constant.DATABASE_ID + " INTEGER PRIMARY KEY, ";
        sql += Constant.USERNAME + " TEXT, ";
        sql += Constant.FULLNAME + " TEXT NOT NULL, ";
        sql += Constant.EMAIL + " TEXT NOT NULL, ";
        sql += Constant.PASSWORD + " TEXT NOT NULL, ";
        sql += Constant.IS_ADMIN + " INTEGER NOT NULL ";
        sql += "CHECK(" + Constant.IS_ADMIN + " IN(0, 1)))";
        return sql;
    }

    @Override
    public boolean create(User user) throws UserDuplicatedException {
        ContentValues values = new ContentValues();
        values.put(Constant.FULLNAME, user.getFullname());
        values.put(Constant.EMAIL, user.getEmail());
        values.put(Constant.USERNAME, user.getUsername());
        values.put(Constant.PASSWORD, user.getPassword());
        values.put(Constant.IS_ADMIN, user.isIs_admin());

        mDatabase = mHelper.getWritableDatabase();
        long lines = mDatabase
                .insert(Constant.USER, null, values);
        mDatabase.close();
        return lines == -1 ? false : true;
    }

    @SuppressLint("Range")
    @Override
    public boolean validateUser(String username, String password) {
        boolean retorno = false;
        String columns[] = new String[]{
                Constant.DATABASE_ID,
                Constant.USERNAME,
                Constant.FULLNAME,
                Constant.EMAIL,
                Constant.PASSWORD,
                Constant.IS_ADMIN,
        };

        String selection = Constant.USERNAME + " like '" + username + "'";
        selection += "AND " + Constant.PASSWORD + " like '" + password + "'";

        try {
            mDatabase = mHelper.getReadableDatabase();
            Cursor cursor = mDatabase.query(
                    Constant.USER,
                    columns,
                    selection,
                    null,
                    null,
                    null,
                    null
            );
            if (cursor.moveToNext()) {
                User user = new User();
                UserSession.getInstance().setUser(user);
                UserSession.getInstance().getUser().setId(cursor.getInt(cursor.getColumnIndex(Constant.DATABASE_ID)));
                UserSession.getInstance().getUser().setUsername(cursor.getString(cursor.getColumnIndex(Constant.USERNAME)));
                UserSession.getInstance().getUser().setFullname(cursor.getString(cursor.getColumnIndex(Constant.FULLNAME)));
                UserSession.getInstance().getUser().setEmail(cursor.getString(cursor.getColumnIndex(Constant.EMAIL)));
                UserSession.getInstance().getUser().setPassword(cursor.getString(cursor.getColumnIndex(Constant.PASSWORD)));
                UserSession.getInstance().getUser().setIs_admin(cursor.getInt(cursor.getColumnIndex(Constant.IS_ADMIN)));
                retorno = true;
            }
            cursor.close();
        } catch (Exception e) {
            retorno = false;
        } finally {
            mDatabase.close();
        }
        return retorno;
    }
}

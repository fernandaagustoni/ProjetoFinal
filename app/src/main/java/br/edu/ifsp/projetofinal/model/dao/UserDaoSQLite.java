package br.edu.ifsp.projetofinal.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.edu.ifsp.projetofinal.exception.UserDuplicatedException;
import br.edu.ifsp.projetofinal.model.entities.User;
import br.edu.ifsp.projetofinal.utils.Constant;

public class UserDaoSQLite implements IUserDao {
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public UserDaoSQLite(Context context) {
        mHelper = new SQLiteHelper(context);
    }

    public static String createTable() {
        String sql = "CREATE TABLE " + Constant.USER + "(";
        sql += Constant.USERNAME + " TEXT PRIMARY KEY, ";
        sql += Constant.FULLNAME + " TEXT NOT NULL, ";
        sql += Constant.EMAIL + " TEXT NOT NULL, ";
        sql += Constant.PASSWORD + " TEXT NOT NULL, ";
        sql += Constant.IS_ADMIN + " INTEGER NOT NULL ";
        sql += "CHECK(" + Constant.IS_ADMIN + " IN(0, 1)))";
        return sql;
    }


    @Override
    public void create(User user) throws UserDuplicatedException {
        ContentValues values = new ContentValues();
        values.put(Constant.FULLNAME, user.getFullname());
        values.put(Constant.EMAIL, user.getEmail());
        values.put(Constant.USERNAME, user.getUsername());
        values.put(Constant.PASSWORD, user.getPassword());
        values.put(Constant.IS_ADMIN, user.isIs_admin() ? 1 : 0);

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
        User user = null;
        int flag = 0;
        String columns[] = new String[]{
                Constant.USERNAME,
                Constant.FULLNAME,
                Constant.EMAIL,
                Constant.PASSWORD,
                Constant.IS_ADMIN,
        };

        String selection = Constant.USERNAME + " = ? ";
        selection += "AND " + Constant.FULLNAME + " = ? ";
        String selectionArgs[] = {username, password};

        try {
            mDatabase = mHelper.getReadableDatabase();
            Cursor cursor = mDatabase.query(
                    Constant.USER,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );
            if (cursor.moveToNext()) {
                user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getInt(4)==1?true:false);
                flag = 1;
            }
            cursor.close();
        } catch (Exception e) {
            user = null;
            return false;
        } finally {
            mDatabase.close();
        }
        if (flag == 0) {
            return false;
        }
        return true;
    }

    }

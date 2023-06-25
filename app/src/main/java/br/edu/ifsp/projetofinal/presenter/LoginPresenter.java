package br.edu.ifsp.projetofinal.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import br.edu.ifsp.projetofinal.model.dao.IUserDao;
import br.edu.ifsp.projetofinal.model.dao.UserDaoSQLite;
import br.edu.ifsp.projetofinal.mvp.LoginMVP;
import br.edu.ifsp.projetofinal.utils.Constant;
import br.edu.ifsp.projetofinal.utils.Cryptography;
import br.edu.ifsp.projetofinal.utils.UserSession;
import br.edu.ifsp.projetofinal.view.RequestActivity;
import br.edu.ifsp.projetofinal.view.UserAddActivity;

public class LoginPresenter implements LoginMVP.Presenter{
    private LoginMVP.View view;
    private IUserDao userDao;

    public LoginPresenter (LoginMVP.View view){
        this.view = view;
        userDao = new UserDaoSQLite(view.getContext());
    }
    @Override
    public void deatach() {
        view = null;
    }
    @Override
    public boolean autenticate(String username, String password, boolean savePreference) {
        String passwordWithoutEncrypt = password;
        password = Cryptography.encrypt(password);
        boolean result = userDao.validateUser(username, password);
        if (!result) {
            Log.d("Erro", "Senha Incorreta");
            return false;
        } else {
            if (savePreference) {
                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences(Constant.USER_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Constant.ID_USER, UserSession.getInstance().getUser().getId());
                editor.putString(Constant.USERNAME, username);
                editor.putString(Constant.PASSWORD, passwordWithoutEncrypt);
                editor.apply();
            }
            openRequestForm();
            return true;
        }
    }

    @Override
    public void openSignUp() {
        Intent intent = new Intent(view.getContext(), UserAddActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void openRequestForm() {
        Intent intent = new Intent(view.getContext(), RequestActivity.class);
        view.getContext().startActivity(intent);
    }

}

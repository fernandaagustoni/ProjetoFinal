package br.edu.ifsp.projetofinal.presenter;

import android.content.Intent;
import android.util.Log;
import br.edu.ifsp.projetofinal.model.dao.IUserDao;
import br.edu.ifsp.projetofinal.model.dao.UserDaoSQLite;
import br.edu.ifsp.projetofinal.mvp.LoginMVP;
import br.edu.ifsp.projetofinal.utils.Cryptography;
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
    public boolean autenticate(String username, String password) {
        password = Cryptography.encrypt(password);
        boolean result = userDao.validateUser(username, password);
        if (!result) {
            Log.d("Erro", "Senha Incorreta");
            return false;
        } else {
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

package br.edu.ifsp.projetofinal.presenter;

import android.content.Intent;
import android.util.Log;

import br.edu.ifsp.projetofinal.model.dao.IUserDao;
import br.edu.ifsp.projetofinal.model.dao.UserDaoSQLite;
import br.edu.ifsp.projetofinal.model.entities.User;
import br.edu.ifsp.projetofinal.mvp.LoginMVP;
import br.edu.ifsp.projetofinal.view.RequestActivity;
import br.edu.ifsp.projetofinal.view.UserAddActivity;

public class LoginPresenter implements LoginMVP.Presenter{
    private LoginMVP.View view;
    private IUserDao userDao;
    public User user;

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
        if(userDao.validateUser(username, password)){
            openRequestForm();
            return true;
        } else {
            Log.d("Erro", "Senha Incorreta");
            return false;
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

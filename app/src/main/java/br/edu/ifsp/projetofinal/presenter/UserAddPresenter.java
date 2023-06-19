package br.edu.ifsp.projetofinal.presenter;

import android.content.Intent;
import android.util.Log;
import br.edu.ifsp.projetofinal.exception.UserDuplicatedException;
import br.edu.ifsp.projetofinal.model.dao.IUserDao;
import br.edu.ifsp.projetofinal.model.dao.UserDaoSQLite;
import br.edu.ifsp.projetofinal.model.entities.User;
import br.edu.ifsp.projetofinal.mvp.UserAddMVP;
import br.edu.ifsp.projetofinal.view.MainActivity;

public class UserAddPresenter implements UserAddMVP.Presenter {
    private UserAddMVP.View view;
    private IUserDao userDao;
    public User user;

    public UserAddPresenter(UserAddMVP.View view){
        this.view = view;
        user = null;
        userDao = new UserDaoSQLite(view.getContext());
    }

    @Override
    public void deatach() {
        this.view = null;
    }

    @Override
    public void saveUser(String fullname, String email, String username, String password, Boolean is_admin) {
        Log.v("", "Save user" + fullname + " " + email + " " + username + " " + password + " " + is_admin);
        user = new User(fullname, email, username, password, is_admin);
        try {
            userDao.create(user);
        }catch (UserDuplicatedException e){
            Log.v("SaveUser", "Usuario duplicado");
        }
        view.close();
    }

    @Override
    public boolean checkPassword(String senha, String confirmaSenha) {
        if (senha.equals(confirmaSenha)){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void openLogin() {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        view.getContext().startActivity(intent);
    }
}

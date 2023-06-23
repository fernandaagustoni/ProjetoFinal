package br.edu.ifsp.projetofinal.presenter;

import android.util.Log;
import br.edu.ifsp.projetofinal.exception.UserDuplicatedException;
import br.edu.ifsp.projetofinal.model.dao.IUserDao;
import br.edu.ifsp.projetofinal.model.dao.UserDaoSQLite;
import br.edu.ifsp.projetofinal.model.entities.User;
import br.edu.ifsp.projetofinal.mvp.UserAddMVP;
import br.edu.ifsp.projetofinal.utils.Cryptography;

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
    public boolean registerUser(User user) {
        user.setPassword(Cryptography.encrypt(user.getPassword()));
        try {
            userDao.create(user);
            return true;
        }catch (UserDuplicatedException e){
            Log.v("SaveUser", "Usuario duplicado");
            return false;
        }
    }
    @Override
    public boolean checkPassword(String senha, String confirmaSenha) {
        if (senha.equals(confirmaSenha)){
            return true;
        } else{
            return false;
        }
    }

}

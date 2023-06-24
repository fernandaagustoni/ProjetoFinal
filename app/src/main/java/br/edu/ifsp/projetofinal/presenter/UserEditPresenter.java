package br.edu.ifsp.projetofinal.presenter;

import android.util.Log;
import android.widget.Toast;

import java.util.Objects;
import br.edu.ifsp.projetofinal.model.dao.IUserDao;
import br.edu.ifsp.projetofinal.model.dao.UserDaoSQLite;
import br.edu.ifsp.projetofinal.model.entities.User;
import br.edu.ifsp.projetofinal.mvp.UserEditMVP;
import br.edu.ifsp.projetofinal.utils.Cryptography;
import br.edu.ifsp.projetofinal.utils.UserSession;

public class UserEditPresenter implements UserEditMVP.Presenter {
    private UserEditMVP.View view;
    private IUserDao dao;

    public UserEditPresenter(UserEditMVP.View view) {
        this.view = view;
        dao = new UserDaoSQLite(view.getContext());
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public boolean editUser(User user) {
        if (!Objects.equals(user.getPassword(), UserSession.getInstance().getUser().getPassword())) {
            user.setPassword(Cryptography.encrypt(user.getPassword()));
        }
        if (!dao.edit(user)){
            Log.v("Erro editUser","Erro editar usuario");
        } else {
            Log.v("editUser","Alteração realizada com sucesso");
        }
        return true;
    }
}

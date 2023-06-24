package br.edu.ifsp.projetofinal.mvp;

import android.content.Context;

import br.edu.ifsp.projetofinal.model.entities.User;

public interface UserEditMVP {
    interface  View {
        void setToolbar();
        Context getContext();
    }
    interface Presenter {
        void deatach();
        boolean editUser(User user);
    }
}

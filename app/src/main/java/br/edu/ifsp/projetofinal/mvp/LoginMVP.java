package br.edu.ifsp.projetofinal.mvp;

import android.content.Context;

import br.edu.ifsp.projetofinal.model.entities.User;

public interface LoginMVP {
    interface View{
        Context getContext();
    }
    interface Presenter{
        void deatach();
        boolean autenticate(String username, String password);
        void openSignUp();
        void openRequestForm();
    }
}

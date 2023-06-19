package br.edu.ifsp.projetofinal.mvp;

import android.content.Context;

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

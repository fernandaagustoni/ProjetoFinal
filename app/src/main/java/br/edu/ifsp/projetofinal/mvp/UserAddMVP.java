package br.edu.ifsp.projetofinal.mvp;

import android.content.Context;
import android.os.Bundle;

public interface UserAddMVP {
    interface View{
        Context getContext();
        Bundle getBundle();
        void close();
    }
    interface Presenter{
        void deatach();
        void saveUser(String fullname, String email, String username, String password, Boolean is_admin);
        boolean checkPassword(String password, String confirmPassword);
        void openLogin();
    }
}

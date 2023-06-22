package br.edu.ifsp.projetofinal.mvp;

import android.content.Context;
import android.os.Bundle;
import br.edu.ifsp.projetofinal.model.entities.User;

public interface UserAddMVP {
    interface View{
        Context getContext();
        Bundle getBundle();
        void close();
    }
    interface Presenter{
        void deatach();
        boolean registerUser(User user);
        boolean checkPassword(String password, String confirmPassword);
        void openLogin();
    }
}

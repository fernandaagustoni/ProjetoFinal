package br.edu.ifsp.projetofinal.mvp;

import android.content.Context;
import android.os.Bundle;

public interface RequestEditMVP {
    interface View{
        Context getContext();
        Bundle getBundle();
        void close();
    }

    interface Presenter{
        void deatach();
        void saveRequest(String status);
    }
}

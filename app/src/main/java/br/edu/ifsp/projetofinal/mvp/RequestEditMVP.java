package br.edu.ifsp.projetofinal.mvp;

import android.content.Context;
import android.os.Bundle;

import br.edu.ifsp.projetofinal.model.entities.Request;

public interface RequestEditMVP {
    interface View{
        void setMenu();
        Context getContext();
        Bundle getBundle();
        void close();
    }

    interface Presenter{
        void deatach();
        boolean updateRequest(Request request);
    }
}

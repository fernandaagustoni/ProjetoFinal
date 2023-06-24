package br.edu.ifsp.projetofinal.mvp;

import android.content.Context;
import android.os.Bundle;
import br.edu.ifsp.projetofinal.model.entities.Request;

public interface RequestAddMVP {
    interface View{
        Context getContext();
        Bundle getBundle();
        void setMenu();
        Request getRequest();
        String returnBase64();
        void close();
    }
    interface Presenter{
        void deatach();
        void saveNewRequest(Request request);
    }
}

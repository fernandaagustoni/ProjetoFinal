package br.edu.ifsp.projetofinal.mvp;

import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import br.edu.ifsp.projetofinal.model.entities.Request;

public interface RequestMVP {
    interface View{
        Context getContext();
        Bundle getBundle();
        Request getRequest();
        void close();
    }

    interface Presenter{
        void deatach();
        void populateList(RecyclerView recyclerView);
        void openNewRequest();
        void openRequestAdmin(Request request);
        void openRequest(Request request);
    }
}

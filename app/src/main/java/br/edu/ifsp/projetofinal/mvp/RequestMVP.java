package br.edu.ifsp.projetofinal.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.recyclerview.widget.RecyclerView;
import br.edu.ifsp.projetofinal.model.entities.Request;

public interface RequestMVP {
    interface View{
        Context getContext();
        Bundle getBundle();
        void setMenu();
        void close();
    }

    interface Presenter{
        void deatach();
        void updateUI(Intent intent, EditText status);
        boolean isNewRequest();
        void populateList(RecyclerView recyclerView);
        void saveNewRequest(Integer id, String origem, String destino, String dataViagem, String anexoNotaFiscal, String anexoKmAntes, String anexoKmDepois, String status);
        void saveNewRequest(String origem, String destino, String dataViagem, String anexoNotaFiscal, String anexoKmAntes, String anexoKmDepois, String status);
        void updateRequest(String status);
        void deleteRequest();
        void openNewRequest();
        void openRequest(Request request);
    }
}

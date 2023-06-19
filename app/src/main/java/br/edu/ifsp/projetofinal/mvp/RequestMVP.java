package br.edu.ifsp.projetofinal.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

public interface RequestMVP {
    interface View{
        Context getContext();
        Bundle getBundle();
        void close();
    }

    interface Presenter{
        void deatach();
        void updateUI(Intent intent, EditText status);
        boolean isNewRequest();
        void populateList(RecyclerView recyclerView);
        void saveNewRequest(Integer id, String origem, String destino, Date dataViagem, String anexoNotaFiscal, String anexoKmAntes, String anexoKmDepois, String status);
        void saveNewRequest(String origem, String destino, String dataViagem, String anexoNotaFiscal, String anexoKmAntes, String anexoKmDepois, String status);
        void updateRequest(String status);
        void deleteRequest();
        void openNewRequest();
    }
}

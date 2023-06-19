package br.edu.ifsp.projetofinal.mvp;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import java.util.Date;

public interface RequestMVP {
    interface View{
        Context getContext();
    }

    interface Presenter{
        void detach();
        void updateUI(Intent intent, EditText status);
        boolean isNewRequest();
        void saveNewRequest(Integer id, String origem, String destino, Date dataViagem, String anexoNotaFiscal, String anexoKmAntes, String anexoKmDepois, String status);
        void updateRequest(String status);
        void deleteRequest();
    }
}

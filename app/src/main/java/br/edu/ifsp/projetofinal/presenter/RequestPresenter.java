package br.edu.ifsp.projetofinal.presenter;

import android.content.Intent;
import android.widget.EditText;

import java.util.Date;

import br.edu.ifsp.projetofinal.mvp.RequestMVP;

public class RequestPresenter implements RequestMVP.Presenter{
    @Override
    public void detach() {

    }

    @Override
    public void updateUI(Intent intent, EditText status) {

    }

    @Override
    public boolean isNewRequest() {
        return false;
    }

    @Override
    public void saveNewRequest(Integer id, String origem, String destino, Date dataViagem, String anexoNotaFiscal, String anexoKmAntes, String anexoKmDepois, String status) {

    }

    @Override
    public void updateRequest(String status) {

    }

    @Override
    public void deleteRequest() {

    }
}

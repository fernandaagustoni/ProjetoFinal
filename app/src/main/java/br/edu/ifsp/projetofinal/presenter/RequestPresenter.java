package br.edu.ifsp.projetofinal.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Date;
import br.edu.ifsp.projetofinal.model.dao.IRequestDao;
import br.edu.ifsp.projetofinal.model.dao.RequestDaoSQLite;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.mvp.RequestMVP;
import br.edu.ifsp.projetofinal.view.RequestAddActivity;


public class RequestPresenter implements RequestMVP.Presenter{
    private RequestMVP.View view;
    private IRequestDao requestDao;
    public Request request;
    public RequestPresenter(RequestMVP.View view){
        this.view = view;
        request = null;
        requestDao = new RequestDaoSQLite(view.getContext());
    }
    @Override
    public void deatach() {
        this.view = null;
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
    public void saveNewRequest(String origem, String destino, String dataViagem, String anexoNotaFiscal, String anexoKmAntes, String anexoKmDepois, String status) {
        Log.v("SaveRequestTest", "Entrou funcao saveNewRequest");
        request = new Request(origem, destino, dataViagem, anexoNotaFiscal, anexoKmAntes, anexoKmAntes, status);
        requestDao.create(request);
        Log.v("SaveRequestTest", "Request criada" + request);
        view.close();
    }

    @Override
    public void updateRequest(String status) {

    }

    @Override
    public void deleteRequest() {

    }
    @Override
    public void openNewRequest() {
        Intent intent = new Intent(view.getContext(), RequestAddActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void populateList(RecyclerView recyclerView) {
    }
}

package br.edu.ifsp.projetofinal.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.edu.ifsp.projetofinal.model.dao.IRequestDao;
import br.edu.ifsp.projetofinal.model.dao.RequestDaoSQLite;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.mvp.RequestMVP;
import br.edu.ifsp.projetofinal.utils.Constant;
import br.edu.ifsp.projetofinal.view.RecyclerViewItemClickListener;
import br.edu.ifsp.projetofinal.view.RequestAddActivity;
import br.edu.ifsp.projetofinal.view.adapter.ItemRecyclerAdapter;


public class RequestPresenter implements RequestMVP.Presenter{
    private RequestMVP.View view;
    private IRequestDao requestDao;
    public Request request;
    public RequestPresenter(RequestMVP.View view){
        this.view = view;
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
    public void saveNewRequest(Integer id, String origem, String destino, String dataViagem, String anexoNotaFiscal, String anexoKmAntes, String anexoKmDepois, String status) {

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
    public void openRequest(Request request) {
        Intent intent = new Intent(view.getContext(), RequestAddActivity.class);
        intent.putExtra(Constant.DATABASE_ID, request.getOrigem());
        view.getContext().startActivity(intent);
    }

    @Override
    public void populateList(RecyclerView recyclerView) {
        ItemRecyclerAdapter adapter = new
                ItemRecyclerAdapter(view.getContext(), requestDao.findAll(),  this);
        adapter.setClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                request = requestDao.findAll().get(position);
                openRequest(request);
            }
        });
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}

package br.edu.ifsp.projetofinal.presenter;

import android.content.Intent;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.edu.ifsp.projetofinal.model.dao.IRequestDao;
import br.edu.ifsp.projetofinal.model.dao.RequestDaoSQLite;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.mvp.RequestMVP;
import br.edu.ifsp.projetofinal.utils.Constant;
import br.edu.ifsp.projetofinal.utils.UserSession;
import br.edu.ifsp.projetofinal.view.RecyclerViewItemClickListener;
import br.edu.ifsp.projetofinal.view.RequestAddActivity;
import br.edu.ifsp.projetofinal.view.RequestEditActivity;
import br.edu.ifsp.projetofinal.view.RequestEditAdminActivity;
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
    public void openNewRequest() {
        Intent intent = new Intent(view.getContext(), RequestAddActivity.class);
        view.getContext().startActivity(intent);
    }
    @Override
    public void openRequest(Request request) {
        Intent intent = new Intent(view.getContext(), RequestEditActivity.class);
        intent.putExtra(Constant.REQUEST, request);
        view.getContext().startActivity(intent);
    }

    @Override
    public void openRequestAdmin(Request request) {
        Intent intent = new Intent(view.getContext(), RequestEditAdminActivity.class);
        intent.putExtra(Constant.REQUEST, request);
        view.getContext().startActivity(intent);
    }

    @Override
    public void populateList(RecyclerView recyclerView) {
        if(UserSession.getInstance().getUser().isIs_admin() == 0){
            ItemRecyclerAdapter adapter = new
                    ItemRecyclerAdapter(view.getContext(), requestDao.findByUserId(),  this);
            adapter.setClickListener(new RecyclerViewItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    request = requestDao.findByUserId().get(position);
                    openRequest(request);
                }
            });
            RecyclerView.LayoutManager layoutManager = new
                    LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }else if(UserSession.getInstance().getUser().isIs_admin() == 1){
            ItemRecyclerAdapter adapter = new
                    ItemRecyclerAdapter(view.getContext(), requestDao.findAll(),  this);
            adapter.setClickListener(new RecyclerViewItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Log.v("id populate", "position " + position);
                    request = requestDao.findAll().get(position);
                    request.setId(position);
                    openRequestAdmin(request);
                }
            });
            RecyclerView.LayoutManager layoutManager = new
                    LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

    }

}

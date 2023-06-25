package br.edu.ifsp.projetofinal.presenter;

import android.util.Log;
import br.edu.ifsp.projetofinal.model.dao.IRequestDao;
import br.edu.ifsp.projetofinal.model.dao.RequestDaoSQLite;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.mvp.RequestAddMVP;


public class RequestAddPresenter implements RequestAddMVP.Presenter {
    private RequestAddMVP.View view;
    private IRequestDao requestDao;
    public Request request;

    public RequestAddPresenter(RequestAddMVP.View viewRecept){
        view = viewRecept;
        requestDao = new RequestDaoSQLite(view.getContext());
    }

    @Override
    public void deatach()  {
        view = null;
    }

    @Override
    public void saveNewRequest(Request request) {
        Log.v("teste id", "id " + request.getId());
        requestDao.create(request);
        view.close();
    }
}

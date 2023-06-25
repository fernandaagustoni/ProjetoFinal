package br.edu.ifsp.projetofinal.presenter;

import br.edu.ifsp.projetofinal.model.dao.IRequestDao;
import br.edu.ifsp.projetofinal.model.dao.RequestDaoSQLite;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.mvp.RequestEditMVP;

public class RequestEditPresenter implements RequestEditMVP.Presenter{
    private RequestEditMVP.View view;
    private IRequestDao requestDao;
    public Request request;
    public RequestEditPresenter(RequestEditMVP.View view){
        this.view = view;
        request = null;
        requestDao = new RequestDaoSQLite(view.getContext());
    }

    @Override
    public void deatach() {
    }

    @Override
    public boolean updateRequest(Request request) {
        requestDao.update(request);
        return true;
    }

}

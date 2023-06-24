package br.edu.ifsp.projetofinal.presenter;

import android.os.Bundle;

import br.edu.ifsp.projetofinal.model.dao.IRequestDao;
import br.edu.ifsp.projetofinal.model.dao.RequestDaoSQLite;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.mvp.RequestAddMVP;
import br.edu.ifsp.projetofinal.mvp.RequestEditMVP;
import br.edu.ifsp.projetofinal.utils.Constant;


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

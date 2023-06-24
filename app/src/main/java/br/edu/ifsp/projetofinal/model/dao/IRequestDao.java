package br.edu.ifsp.projetofinal.model.dao;

import java.util.List;
import br.edu.ifsp.projetofinal.model.entities.Request;

public interface IRequestDao {
    boolean create(Request request);
    boolean update(String status, Request request);
    List<Request> findAll();
    List<Request> findByUserId();

}

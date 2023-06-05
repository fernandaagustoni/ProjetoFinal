package br.edu.ifsp.projetofinal.model.dao;

import java.util.List;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.model.entities.User;

public interface IRequestDao {

    void create(Request request);
    boolean update(String status, Request request);
    boolean delete(Request request);
    Request findById(String id);
    List<Request> findByUser(User user);
    List<Request> findAll();

}

package br.edu.ifsp.projetofinal.model.dao;

import br.edu.ifsp.projetofinal.exception.UserDuplicatedException;
import br.edu.ifsp.projetofinal.model.entities.User;

public interface IUserDao {
    boolean create(User user) throws UserDuplicatedException;
    boolean validateUser(String username, String password);
}

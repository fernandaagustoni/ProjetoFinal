package br.edu.ifsp.projetofinal.utils;

import br.edu.ifsp.projetofinal.model.entities.User;

public class UserSession {
    private static UserSession instance;
    private User user;
    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }
    public User getUser() {
        return user;
    }
    public  void setUser(User usuario) {
        user = usuario;
    }

}

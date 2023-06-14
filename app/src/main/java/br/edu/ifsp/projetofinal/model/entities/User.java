package br.edu.ifsp.projetofinal.model.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private boolean is_admin;
    private List<Request> request;
    private void init(){
        request = new ArrayList<>();
    }

    public User(String fullname, String email, String username, String password, Boolean is_admin) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.is_admin = is_admin;
        init();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }
}

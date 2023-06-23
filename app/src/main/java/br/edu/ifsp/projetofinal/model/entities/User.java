package br.edu.ifsp.projetofinal.model.entities;

public class User {
    public int id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private int is_admin;

    public User(int id, String fullname, String email, String username, String password, int is_admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.is_admin = is_admin;
    }
    public User(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
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

}

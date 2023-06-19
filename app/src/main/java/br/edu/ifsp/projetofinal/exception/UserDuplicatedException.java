package br.edu.ifsp.projetofinal.exception;

public class UserDuplicatedException extends Exception{
    public UserDuplicatedException(String username){
        super("Duplicated user on database: " + username);
    }

}

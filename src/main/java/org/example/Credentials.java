package org.example;

public class Credentials {
    private String email;
    private String parola;

    public Credentials(String mail, String pass){
        this.email = mail;
        this.parola = pass;
    }

    public String getEmail(){

        return email;
    }

    public String getPass(){

        return parola;
    }
}

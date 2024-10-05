package org.example;

public class Pairs {
    private String film;
    private String tip;

    public Pairs(String movie, String tip){
        this.film = movie;
        this.tip = tip;
    }

    public String getFilm(){
        return this.film;
    }
    public String getTip(){
        return this.tip;
    }
}

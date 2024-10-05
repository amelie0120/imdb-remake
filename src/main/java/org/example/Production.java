package org.example;

import java.util.List;

enum Genre{
    Action,
    Adventure,
    Comedy,
    Drama,
    Horror,
    SF,
    Fantasy,
    Romance,
    Mystery,
    Thriller,
    Crime,
    Biography,
    War
}

public abstract class Production implements Comparable<Production>{
    String titluProductie;
    List<String> regizori;
    List<String> numeActori;
    List<Genre> genuri;
    List<Rating> evaluari;
    String descriereFilm;
    Double notaFilm;

    public Production(String titluProductie, List<String> regizori, List<String> numeActori, List<Genre> genuri, List<Rating> evaluari, String descriereFilm, Double notaFilm){
        this.titluProductie = titluProductie;
        this.regizori = regizori;
        this.numeActori = numeActori;
        this.genuri = genuri;
        this.descriereFilm = descriereFilm;
        this.notaFilm = notaFilm;
        this.evaluari = evaluari;
    }

    public List<Rating> getEvaluari(){
        return evaluari;
    }

    public abstract void displayInfo();

    private Observer observer;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void sendNotification(String sir) {
        if (observer != null) {
            observer.update(sir);
        }
    }


}

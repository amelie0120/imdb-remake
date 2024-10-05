package org.example;

public class Rating {
    String username;
    int nota;
    String comment;

    public Rating(String username, int nota, String comment){
        this.username = username;
        this.nota = nota;
        this.comment = comment;
    }

    public String getUsername(){
        return username;
    }

    public int getNota(){
        return nota;
    }

    public String getComment(){
        return comment;
    }

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

package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

enum RequestType{
    DELETE_ACCOUNT,
    ACTOR_ISSUE,
    MOVIE_ISSUE,
    OTHERS
}
public class Request {
    private RequestType tipCerere;
    private LocalDateTime dataCreare;
    String production;
    String descriere;
    String usernameCreator;
    String usernameRezolva;


    public Request(RequestType tipCerere, LocalDateTime dataCreare, String production, String descriere, String usernameCreator, String usernameRezolva){
        this.tipCerere = tipCerere;
        this.dataCreare = dataCreare;
        this.production = production;
        this.descriere = descriere;
        this.usernameCreator = usernameCreator;
        this.usernameRezolva = usernameRezolva;

    }
    public Request(RequestType tipCerere, LocalDateTime dataCreare, String descriere, String usernameCreator, String usernameRezolva){
        this.tipCerere = tipCerere;
        this.dataCreare = dataCreare;
        this.descriere = descriere;
        this.usernameCreator = usernameCreator;
        this.usernameRezolva = usernameRezolva;

    }

    public void displayInfo(){
        System.out.println("Tip cerere: " + this.tipCerere);
        System.out.println("Data creare: " + this.dataCreare);
        System.out.println("Descriere: " + this.descriere);
        System.out.println("Creat de: " + this.usernameCreator);
        System.out.println("Responsabil: " + this.usernameRezolva);
        System.out.println();
    }

    public RequestType getTipCerere(){
        return tipCerere;
    }
    public String getDescriere(){
        return descriere;
    }

    public String getUsernameCreator(){
        return usernameCreator;
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

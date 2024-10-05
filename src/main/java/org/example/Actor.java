package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;


public class Actor implements Comparable<Actor>{
    String numeActor;
    String biography;
    List<Pairs> listaPerechi;

    public Actor(String actor, List<Pairs> perechi, String biografie){
        this.numeActor = actor;
        this.listaPerechi = perechi;
        this.biography = biografie;
    }

    public void displayInfo(){
        System.out.println("Nume: " + this.numeActor);
        System.out.println("Biografie " + this.biography);
        System.out.println("Productiile sale:");
        for (Pairs p:this.listaPerechi){
            System.out.println(" Titlu: " + p.getFilm());
            System.out.println("  Tip: " + p.getTip());
        }
        System.out.println();
    }


    @Override
    public int compareTo(@NotNull Actor o) {
        return this.numeActor.compareTo(o.numeActor);
    }
}

package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class Movie extends Production {
    String filmDuration;
    int anLansare;

    public Movie(String titluProductie, List<String> regizori, List<String> numeActori, List<Genre> genuri, List<Rating> ratinguri, String descriereFilm, Double notaFilm, String filmDuration, int anLansare) {
        super(titluProductie, regizori, numeActori, genuri, ratinguri, descriereFilm, notaFilm);
        this.anLansare = anLansare;
        this.filmDuration = filmDuration;
    }

    public void displayInfo(){
        System.out.println("Title: " + this.titluProductie);

        if (!this.regizori.isEmpty())
            System.out.print("Directors: ");
        for (String sir:this.regizori)
            System.out.print(sir);
        System.out.println();

        if (!this.numeActori.isEmpty())
            System.out.print("Actors: ");
        for (String sir:this.numeActori)
            System.out.print(sir);
        System.out.println();

        if (!this.genuri.isEmpty())
            System.out.print("Genre: ");
        for (Genre sir:this.genuri)
            System.out.print(sir);
        System.out.println();

        if (!this.evaluari.isEmpty())
            System.out.println("Ratings: ");
        for (Rating sir:this.evaluari){
            System.out.println("This production received a rating of " + sir.nota + " from " + sir.username);
            System.out.println("    " + sir.comment);
        }

        if (!Objects.equals(this.descriereFilm, ""))
            System.out.println("Plot: " + this.descriereFilm);

        if (this.notaFilm != 0)
            System.out.println("Rating: " + this.notaFilm);

        if (!Objects.equals(this.filmDuration, null))
            System.out.println("Duration: " + this.filmDuration);

        if (this.anLansare != 0)
            System.out.println("Release year: " + this.anLansare);

        System.out.println();
    }

    @Override
    public int compareTo(@NotNull Production o) {
        return this.titluProductie.compareTo(o.titluProductie);
    }
}

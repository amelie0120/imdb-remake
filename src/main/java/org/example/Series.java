package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Series extends Production{
    int anLansare;
    int nrSezoane;
    Map<String, List<Episode>> dictionar;

    public Series(String titluProductie, List<String> regizori, List<String> numeActori, List<Genre> genuri, List<Rating> evaluari, String descriereFilm, Double notaFilm, int anLansare, int nrSezoane, Map<String, List<Episode>> dictionar) {
        super(titluProductie, regizori, numeActori, genuri, evaluari, descriereFilm, notaFilm);
        this.anLansare = anLansare;
        this.nrSezoane = nrSezoane;
        this.dictionar = dictionar;
    }

    @Override
    public int compareTo(@NotNull Production o) {
        return this.titluProductie.compareTo(o.titluProductie);
    }

    @Override
    public void displayInfo() {
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
            System.out.println("  This production received a rating of " + sir.nota + " from " + sir.username);
            System.out.println("    " + sir.comment);
        }

        if (!Objects.equals(this.descriereFilm, ""))
            System.out.println("Plot: " + this.descriereFilm);

        if (this.notaFilm != 0)
            System.out.println("Rating: " + this.notaFilm);

        if (this.anLansare != 0)
            System.out.println("Release year: " + this.anLansare);

        if (this.nrSezoane != 0)
            System.out.println("Seasons: " + this.nrSezoane);
        for(int i = 1; i <= this.nrSezoane; i++){
            List<Episode> sezon = dictionar.get("Season " + i);
            for (Episode ep:sezon)
            {
                System.out.println("    Episode name: " + ep.numeEpisod);
                System.out.println("    Episode length: " + ep.durataEpisod);
                System.out.println();
            }


        }

        System.out.println();
    }
}

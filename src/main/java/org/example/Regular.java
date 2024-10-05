package org.example;

import java.util.List;
import java.util.Objects;
import java.util.SortedSet;

interface RequestsManager{
    public void createRequest(Request r);
    public void removeRequest(Request r);
}
public class Regular extends User implements RequestsManager{

    public Regular(String name, int experienta, AccountType tipcont, Information information, SortedSet<Production> favProd, SortedSet<Actor> favActori,
                   List<String> notifications) {
        super(name, experienta, tipcont, information, favProd, favActori, notifications);
    }

    public void addRating(Rating r){

    }
    @Override
    public void createRequest(Request r) {
        RequestsHolder.cereriAdmini.add(r);
    }

    @Override
    public void removeRequest(Request r) {
        RequestsHolder.cereriAdmini.remove(r);
    }

    @Override
    public void addProductionSystem(Production p) {
        int gasit = 0;
        for(Movie m:IMDB.filme){
            if (Objects.equals(m.titluProductie, p.titluProductie)){
                IMDB.filme.add((Movie) p);
                gasit = 1;
                break;
            }
        }
        if (gasit == 0)
            for(Series s:IMDB.seriale){
                if (Objects.equals(s.titluProductie, p.titluProductie)){
                    IMDB.seriale.add((Series) p);
                    gasit = 1;
                    break;
                }
            }
    }

    @Override
    public void addActorSystem(Actor a) {
        IMDB.actori.add(a);

    }

    @Override
    public void removeProductionSystem(String name) {
        IMDB.filme.removeIf(m -> Objects.equals(m.titluProductie, name));
        IMDB.seriale.removeIf(s -> Objects.equals(s.titluProductie, name));

    }

    @Override
    public void removeActorSystem(String name) {
        IMDB.actori.removeIf(a -> Objects.equals(a.numeActor, name));

    }

    @Override
    public void updateProduction(Production p) {
        int gasit = 0;
        for(Movie m:IMDB.filme){
            if (Objects.equals(m.titluProductie, p.titluProductie)){
                m.regizori = p.regizori;
                m.numeActori = p.numeActori;
                m.genuri = p.genuri;
                m.evaluari = p.evaluari;
                m.descriereFilm = p.descriereFilm;
                m.notaFilm = p.notaFilm;
                gasit = 1;
                break;
            }
        }
        if (gasit == 0)
            for(Series s:IMDB.seriale){
                if (Objects.equals(s.titluProductie, p.titluProductie)){
                    s.regizori = p.regizori;
                    s.numeActori = p.numeActori;
                    s.genuri = p.genuri;
                    s.evaluari = p.evaluari;
                    s.descriereFilm = p.descriereFilm;
                    s.notaFilm = p.notaFilm;
                    break;
                }
            }
    }

    @Override
    public void updateActor(Actor a) {
        for (Actor actor:IMDB.actori){
            if (Objects.equals(actor.numeActor, a.numeActor))
            {
                actor.biography = a.biography;
                actor.listaPerechi = a.listaPerechi;
            }
        }
    }
}

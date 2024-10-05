package org.example;

import java.util.List;
import java.util.Objects;
import java.util.SortedSet;

interface StaffInterface{
    public void addProductionSystem(Production p);
    public void addActorSystem(Actor a);
    public void removeProductionSystem(String name);
    public void removeActorSystem(String name);
    public void updateProduction(Production p);
    public void updateActor(Actor a);

}
public abstract class Staff extends User implements StaffInterface{

    List<Request> listaCereri;
    SortedSet<Production> adaugariProd;
    SortedSet<Actor> adaugariActori;
    public Staff(String name, int experienta, AccountType tipcont, User.Information information, SortedSet<Production> favProd,
                 SortedSet<Actor> favActor, SortedSet<Production> prodAdds, SortedSet<Actor> actorsAdds, List<Request> requestList, List<String> notifications) {
        super(name, experienta, tipcont, information, favProd, favActor, notifications);
        this.adaugariProd = prodAdds;
        this.adaugariActori = actorsAdds;
        this.listaCereri = requestList;
    }

    public SortedSet<Production> getAdaugariProd(){
        return adaugariProd;
    }

    public SortedSet<Actor> getAdaugariActori(){
        return adaugariActori;
    }


    @Override
    public void addProductionSystem(Production p) {
        adaugariProd.add(p);
    }

    @Override
    public void addActorSystem(Actor a) {
        adaugariActori.add(a);
    }

    @Override
    public void removeProductionSystem(String name) {
        adaugariProd.removeIf(p -> Objects.equals(p.titluProductie, name));
    }

    @Override
    public void removeActorSystem(String name) {
        adaugariActori.removeIf(a -> Objects.equals(a.numeActor, name));
        IMDB.actori.removeIf(a -> Objects.equals(a.numeActor, name));
    }

    @Override
    public void updateProduction(Production p) {

        for (Regular r:IMDB.regular) {
            for (Production prod : r.favoriteProd) {
                if (Objects.equals(prod.titluProductie, p.titluProductie)) {
                    r.favoriteProd.remove(prod);
                    r.favoriteProd.add(p);
                }
            }
        }

        for (Contributor c:IMDB.contributor) {
            for (Production prod : c.favoriteProd) {
                if (Objects.equals(prod.titluProductie, p.titluProductie)) {
                    c.favoriteProd.remove(prod);
                    c.favoriteProd.add(p);
                }
            }
            for (Production prod : c.adaugariProd) {
                if (Objects.equals(prod.titluProductie, p.titluProductie)) {
                    c.adaugariProd.remove(prod);
                    c.adaugariProd.add(p);
                }
            }
        }

        for (Admin adm:IMDB.admin){
            for (Production prod : adm.favoriteProd) {
                if (Objects.equals(prod.titluProductie, p.titluProductie)) {
                    adm.favoriteProd.remove(prod);
                    adm.favoriteProd.add(p);
                }
            }
            for (Production prod : adm.adaugariProd) {
                if (Objects.equals(prod.titluProductie, p.titluProductie)) {
                    adm.adaugariProd.remove(prod);
                    adm.adaugariProd.add(p);
                }
            }
        }
    }

    @Override
    public void updateActor(Actor a) {
        for (Regular r:IMDB.regular) {
            for (Actor actor : r.favoriteActors) {
                if (Objects.equals(actor.numeActor, a.numeActor)) {
                    actor.biography = a.biography;
                    actor.listaPerechi = a.listaPerechi;
                }
            }
        }

        for (Contributor c:IMDB.contributor) {
            for (Actor actor : c.favoriteActors) {
                if (Objects.equals(actor.numeActor, a.numeActor)) {
                    actor.biography = a.biography;
                    actor.listaPerechi = a.listaPerechi;
                }
            }
            for (Actor actor:c.adaugariActori) {
                if (Objects.equals(actor.numeActor, a.numeActor)) {
                    actor.biography = a.biography;
                    actor.listaPerechi = a.listaPerechi;
                }
            }
        }

        for (Admin adm:IMDB.admin){
            for (Actor actor:adm.favoriteActors) {
                if (Objects.equals(actor.numeActor, a.numeActor)) {
                    actor.biography = a.biography;
                    actor.listaPerechi = a.listaPerechi;
                }
            }
            for (Actor actor:adm.adaugariActori) {
                if (Objects.equals(actor.numeActor, a.numeActor)) {
                    actor.biography = a.biography;
                    actor.listaPerechi = a.listaPerechi;
                }
            }
        }


    }
}

package org.example;

import java.util.List;
import java.util.SortedSet;

//interface RequestsManager{
//    public void createRequest(Request r);
//    public void removeRequest(Request r);
//}
public class Contributor extends Staff implements RequestsManager{
    public Contributor(String name, int experienta, AccountType tipcont, User.Information information, SortedSet<Production> favoriteProd,
                       SortedSet<Actor> favoriteActors, SortedSet<Production> adaugariProd, SortedSet<Actor> adaugariActori, List<Request> requestList,
                       List<String> notifications) {
        super(name, experienta, tipcont, information, favoriteProd, favoriteActors, adaugariProd, adaugariActori, requestList, notifications);

    }
    @Override
    public void createRequest(Request r) {

    }

    @Override
    public void removeRequest(Request r) {

    }
}

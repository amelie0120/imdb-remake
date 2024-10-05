package org.example;
import org.example.*;

import java.util.List;
import java.util.SortedSet;

public class Admin extends Staff{
    public Admin(String name, int experienta, AccountType tipcont, Information information, SortedSet<Production> favoriteProd, SortedSet<Actor> favoriteActors,
                 SortedSet<Production> adaugariProd, SortedSet<Actor> adaugariActori, List<Request> requestList, List<String> notifications) {
        super(name, experienta, tipcont, information, favoriteProd, favoriteActors, adaugariProd, adaugariActori,requestList, notifications);

    }

}

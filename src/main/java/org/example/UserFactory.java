package org.example;

import java.util.List;
import java.util.SortedSet;

interface Factory{
    Regular createRegular(String name, int experienta, AccountType tipcont, User.Information information,
                          SortedSet<Production> favProd,SortedSet<Actor> favActori, List<String> notifications);
    Contributor createContributor(String name, int experienta, AccountType tipcont, User.Information information, SortedSet<Production> favProd, SortedSet<Actor> favActori,
                                  SortedSet<Production> adaugariProd,SortedSet<Actor> adaugariActori, List<Request> requestList, List<String> notifications);
    Admin createAdmin(String name, int experienta, AccountType tipcont, User.Information information, SortedSet<Production> favProd,SortedSet<Actor> favActori,
                      SortedSet<Production> adaugariProd, SortedSet<Actor> adaugariActori, List<Request> requestList, List<String> notifications);
}
public class UserFactory implements Factory{
    public Regular createRegular(String name, int experienta, AccountType tipcont, User.Information information, SortedSet<Production> favProd,
                                 SortedSet<Actor> favActori, List<String> notifications){
        return new Regular(name, experienta, tipcont, information, favProd, favActori, notifications);
    }

    public Contributor createContributor(String name, int experienta, AccountType tipcont, User.Information information, SortedSet<Production> favProd,
                                         SortedSet<Actor> favActori, SortedSet<Production> adaugariProd, SortedSet<Actor> adaugariActori,
                                         List<Request> requestList, List<String> notifications){
        return new Contributor(name, experienta, tipcont, information, favProd, favActori, adaugariProd, adaugariActori, requestList, notifications);
    }

    public Admin createAdmin(String name, int experienta, AccountType tipcont, User.Information information, SortedSet<Production> favProd,
                             SortedSet<Actor> favActori,SortedSet<Production> adaugariProd, SortedSet<Actor> adaugariActori, List<Request> requestList,
                             List<String> notifications){
        return new Admin(name, experienta, tipcont, information, favProd, favActori, adaugariProd, adaugariActori, requestList, notifications);
    }
}

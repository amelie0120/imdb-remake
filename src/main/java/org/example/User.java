package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;


interface Observer {
    void update(String notification);
}
public abstract class User implements Observer{

    private final String username;
    private final Information info;
    private final AccountType tipUser;
    private int experience;
    private final List<String> notifications;
    SortedSet<Actor> favoriteActors;
    SortedSet<Production> favoriteProd;


    public User(String userName, int experienta, AccountType tipcont, Information information, SortedSet<Production> favoriteProd, SortedSet<Actor> favoriteActors, List<String> notifications) {
        this.username = userName;
        this.experience = experienta;
        this.tipUser = tipcont;
        this.info = information;
        this.favoriteActors = favoriteActors;
        this.favoriteProd = favoriteProd;
        this.notifications = notifications;


    }
    public int getExperience(){
       return experience;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail(){
        return info.credentiale.getEmail();
    }
    public String getPass(){
        return info.credentiale.getPass();
    }

    public List<String> getNotifications(){
        return notifications;
    }

    public void increaseExperience(){
        this.experience++;
    }

    @Override
    public void update(String notification) {
        this.notifications.add(notification);
    }

    public void actualizareExp() {

    }

    public abstract void addProductionSystem(Production p);

    public abstract void addActorSystem(Actor a);

    public abstract void removeProductionSystem(String name);

    public abstract void removeActorSystem(String name);

    public abstract void updateProduction(Production p);

    public abstract void updateActor(Actor a);

    static class Information {
        private final String nume;
        private final String tara;
        private final int varsta;
        private final String gen;
        private final Credentials credentiale;

        public String getName() {
            return nume;
        }

        public int getAge() {
            return varsta;
        }

        public String getCountry() {
            return tara;
        }

        public String getGender() {
            return gen;
        }

        Information(InformationBuilder info) {
            this.nume = info.nume;
            this.tara = info.tara;
            this.varsta = info.varsta;
            this.gen = info.gen;
            this.credentiale = info.credentiale;
            LocalDateTime dataNasterii = info.dataNasterii;
        }

        public static class InformationBuilder {
            private final String nume;
            private final String tara;
            private final int varsta;
            private final String gen;
            private final Credentials credentiale;
            private final LocalDateTime dataNasterii;

            public InformationBuilder(String name, String country, int age, String gender, Credentials credentials, LocalDateTime birthDate) {
                this.nume = name;
                this.tara = country;
                this.varsta = age;
                this.gen = gender;
                this.credentiale = credentials;
                this.dataNasterii = birthDate;

            }
        }
    }
}

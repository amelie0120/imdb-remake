package org.example;

//import org.example.*;
import java.util.Random;
import java.security.SecureRandom;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings("unused")

enum AccountType {
    REGULAR,
    CONTRIBUTOR,
    ADMIN
}


public class IMDB {
    static List<Regular> regular;
    static List<Contributor> contributor;
    static List<Admin> admin;
    static List<Actor> actori;
    static List<Request> request;
    static List<Pairs> perechi;
    static List<Movie> filme;
    static List<Series> seriale;

    public static void updateActorProduction(){
        Scanner myObj = new Scanner(System.in);

        System.out.println("Please choose whether you want to update an actor or a production");
        System.out.println("Enter a number from these options:");
        System.out.println("1) Actor");
        System.out.println("2) Production");
        System.out.println();

        String sir = myObj.nextLine();
        if (!isNumeric(sir))
        {
            System.out.println("Invalid input\n");
            return;
        }
        int alegere = Integer.parseInt(sir);

        switch (alegere){
            case 1:
                System.out.println("Enter the name of the actor");
                String name = myObj.nextLine();

                System.out.println("Choose the number of a field you want to change");
                System.out.println("1) Biography");

                System.out.println("2) Performances");

                String choice = myObj.nextLine();
                if (!isNumeric(choice)){
                    System.out.println("\nInvalid input\n");
                    return;
                }
                int c = Integer.parseInt(choice);

                Actor act = null;
                for (Actor a:actori)
                {
                    if (Objects.equals(a.numeActor, name)){
                        act = a;
                        break;
                    }
                }
                if (act == null)
                    return;

                switch (c){
                    case 1:
                        System.out.println("This is the current biography:\n");
                        System.out.println(act.biography);
                        System.out.println();
                        System.out.println("Enter a new biography");
                        String bio = myObj.nextLine();
                        act.biography = bio;
                        System.out.println("Biography updated successfully!\n");
                        break;
                    case 2:
                        System.out.println("These are the current performances:\n");
                        for (Pairs p:act.listaPerechi){
                            System.out.println(" Titlu: " + p.getFilm());
                            System.out.println("  Tip: " + p.getTip());
                        }
                        System.out.println();
                        System.out.println("Enter a new title that you want to add to its performances or an existent title that you want to remove");
                        String title = myObj.nextLine();

                        for (Pairs p:act.listaPerechi)
                            if (Objects.equals(p.getFilm(), name)){
                                act.listaPerechi.remove(p);
                                System.out.println("\nPerformance removed successfully!\n");
                                return;
                            }
                        String type = null;
                        for (Movie m:filme){
                            if (Objects.equals(m.titluProductie, title)){
                                type = "Movie";
                                break;
                            }
                        }

                        if (type == null){
                            for (Series s:seriale)
                                if (Objects.equals(s.titluProductie, title))
                                {
                                    type = "Series";
                                    break;
                                }
                        }

                        if (type == null){
                            System.out.println("This performance is not in the system. Please specify whether its type, entering 'Movie' or 'Series'");
                            type = myObj.nextLine();
                            if (!Objects.equals(type, "Movie") && !Objects.equals(type, "Series")){
                                System.out.println("Invalid input");
                                return;
                            }
                        }

                        Pairs newpair = new Pairs(title, type);
                        act.listaPerechi.add(newpair);

                        System.out.println("\nPerformance added successfully!\n");
                        break;
                    default:
                        System.out.println("\nInvalid choice\n");
                        break;
                }



                break;
            case 2:
                System.out.println("Enter the title of the production");
                String title = myObj.nextLine();

                System.out.println("Choose the number of a field you want to change");
                System.out.println("1) Directors");

                System.out.println("2) Actors");

                System.out.println("3) Genres");

                System.out.println("4) Plot");

                System.out.println("5) Release year");

                Movie film = null;
                Series serial;
                for (Movie m:filme)
                    if (Objects.equals(m.titluProductie, title)){
                        film = m;
                        System.out.println("6) Duration");

                        String sir2 = myObj.nextLine();
                        if (!isNumeric(sir2))
                        {
                            System.out.println("Invalid input\n");
                            return;
                        }
                        int alegere2 = Integer.parseInt(sir2);
                        switch (alegere2){
                            case 1:
                                System.out.println("This is the current list of directors");
                                for (String d:film.regizori)
                                    System.out.println(d);
                                System.out.println();

                                System.out.println("Enter an existent name from this list if you want to delete it or a new name if you want to add it");
                                name = myObj.nextLine();

                                for (String d:film.regizori)
                                    if (Objects.equals(d, name)){
                                        film.regizori.remove(d);
                                        System.out.println("\nDirector removed successfully!\n");
                                        return;
                                    }

                                film.regizori.add(name);
                                System.out.println("\nDirector added successfully!\n");

                                break;
                            case 2:
                                System.out.println("This is the current list of actors");
                                for (String d:film.numeActori)
                                    System.out.println(d);
                                System.out.println();

                                System.out.println("Enter an existent name from this list if you want to delete it or a new name if you want to add it");
                                name = myObj.nextLine();

                                for (String d:film.numeActori)
                                    if (Objects.equals(d, name)){
                                        film.numeActori.remove(d);
                                        System.out.println("\nActor removed successfully!");
                                        return;
                                    }

                                film.numeActori.add(name);
                                System.out.println("\nActor added successfully!");
                                break;
                            case 3:
                                System.out.println("This is the current list of genres");
                                for (Genre g:film.genuri)
                                    System.out.println(g);
                                System.out.println();

                                System.out.println("Enter an existent genre from this list if you want to delete it or a new genre if you want to add it");
                                name = myObj.nextLine();

                                for (Genre g:film.genuri)
                                    if (Objects.equals(g.toString(), name)){
                                        film.genuri.remove(g);
                                        System.out.println("\nGenre removed successfully!");
                                        return;
                                    }

                                film.genuri.add(Genre.valueOf(name));
                                System.out.println("\nGenre added successfully!");
                                break;
                            case 4:
                                System.out.println("This is the current plot");
                                System.out.println(film.descriereFilm + '\n');
                                System.out.println("Enter another description of this film:");
                                name = myObj.nextLine();
                                film.descriereFilm = name;
                                System.out.println("\nDescription changed successfully!\n");
                                break;
                            case 5:
                                System.out.println("This is the current release year");
                                System.out.println(film.anLansare + '\n');
                                System.out.println("Enter another release year of this film:");
                                name = myObj.nextLine();
                                if (!isNumeric(name)){
                                    System.out.println("Invalid input\n");
                                    return;
                                }
                                film.anLansare = Integer.parseInt(name);
                                System.out.println("\nRelease year changed successfully!\n");
                                break;
                            case 6:
                                System.out.println("This is the current duration of the movie");
                                System.out.println(film.filmDuration + '\n');
                                System.out.println("Enter another duration of this movie:");
                                name = myObj.nextLine();
                                if (!isNumeric(name)){
                                    System.out.println("Invalid input\n");
                                    return;
                                }
                                film.filmDuration = name + " minutes";
                                System.out.println("\nDuration changed successfully!\n");
                                break;
                            default:
                                System.out.println("Invalid input\n");
                                break;
                        }

                        break;
                    }

                if(film == null){
                    for (Series s:seriale){
                        if (Objects.equals(s.titluProductie, title)){
                            serial = s;
                            System.out.println("6) Episodes");

                            String sir2 = myObj.nextLine();
                            if (!isNumeric(sir2))
                            {
                                System.out.println("Invalid input\n");
                                return;
                            }
                            int alegere2 = Integer.parseInt(sir2);
                            switch (alegere2){
                                case 1:
                                    System.out.println("This is the current list of directors");
                                    for (String d:serial.regizori)
                                        System.out.println(d);
                                    System.out.println();

                                    System.out.println("Enter an existent name from this list if you want to delete it or a new name if you want to add it");
                                    name = myObj.nextLine();

                                    for (String d:serial.regizori)
                                        if (Objects.equals(d, name)){
                                            serial.regizori.remove(d);
                                            System.out.println("\nDirector removed successfully!\n");
                                            return;
                                        }

                                    serial.regizori.add(name);
                                    System.out.println("\nDirector added successfully!\n");
                                    break;
                                case 2:
                                    System.out.println("This is the current list of actors");
                                    for (String d:serial.numeActori)
                                        System.out.println(d);
                                    System.out.println();

                                    System.out.println("Enter an existent name from this list if you want to delete it or a new name if you want to add it");
                                    name = myObj.nextLine();

                                    for (String d:serial.numeActori)
                                        if (Objects.equals(d, name)){
                                            serial.numeActori.remove(d);
                                            System.out.println("\nActor removed successfully!\n");
                                            return;
                                        }

                                    serial.numeActori.add(name);
                                    System.out.println("\nActor added successfully!");
                                    break;
                                case 3:
                                    System.out.println("This is the current list of genres");
                                    for (Genre g:serial.genuri)
                                        System.out.println(g);
                                    System.out.println();

                                    System.out.println("Enter an existent genre from this list if you want to delete it or a new genre if you want to add it");
                                    name = myObj.nextLine();

                                    for (Genre g:serial.genuri)
                                        if (Objects.equals(g.toString(), name)){
                                            serial.genuri.remove(g);
                                            System.out.println("\nGenre removed successfully!\n");
                                            return;
                                        }

                                    serial.genuri.add(Genre.valueOf(name));
                                    System.out.println("\nGenre added successfully!");
                                    break;
                                case 4:
                                    System.out.println("This is the current plot");
                                    System.out.println(serial.descriereFilm + '\n');
                                    System.out.println("Enter another description of this film:");
                                    name = myObj.nextLine();
                                    serial.descriereFilm = name;
                                    System.out.println("\nDescription changed successfully!\n");
                                    break;
                                case 5:
                                    System.out.println("This is the current release year");
                                    System.out.println(serial.anLansare + '\n');
                                    System.out.println("Enter another release year of this film:");
                                    name = myObj.nextLine();
                                    if (!isNumeric(name)){
                                        System.out.println("Invalid input\n");
                                        return;
                                    }
                                    serial.anLansare = Integer.parseInt(name);
                                    System.out.println("\nRelease year changed successfully!\n");
                                    break;
                                case 6:
                                    System.out.println("These are the details about the episodes:");
                                    for(int i = 1; i <= serial.nrSezoane; i++){
                                        System.out.println("Season " + i);
                                        List<Episode> sezon = serial.dictionar.get("Season " + i);
                                        for (Episode ep:sezon)
                                        {
                                            System.out.println("    Episode name: " + ep.numeEpisod);
                                            System.out.println("    Episode length: " + ep.durataEpisod);
                                            System.out.println();
                                        }
                                    }


                                    System.out.println("Enter the changes you want to make, like in this example:\n2,1,33");
                                    System.out.println("where 1 is the number of the season, 2 is the number of the episode and 33 is the new length");
                                    System.out.println("Another example:\n3,1,Sam gets sad");
                                    System.out.println("where Sam gets sad is the new name of the episode\n");

                                    name = myObj.nextLine();
                                    String[] wordsArray = name.split(",");

                                    if (!isNumeric(wordsArray[0]) && !isNumeric((wordsArray[1])))
                                    {
                                        System.out.println("Invalid input");
                                        return;
                                    }
                                    int sez = Integer.parseInt(wordsArray[0]);
                                    int ep = Integer.parseInt(wordsArray[1]);

                                    List <Episode> list = serial.dictionar.get("Season " + sez);
                                    int i = 1;
                                    for (Episode e:list)
                                    {
                                        if (i == ep) {
                                            if (isNumeric(wordsArray[2])) {
                                                e.durataEpisod = wordsArray[2] + " minutes";
                                                System.out.println("\nDuration of episode changed\n");
                                                return;
                                            } else {
                                                e.numeEpisod = wordsArray[2];
                                                System.out.println("\nName of episode changed\n");
                                                return;
                                            }
                                        }
                                        i++;
                                    }
//
                                    break;
                                default:
                                    System.out.println("\nInvalid input\n");
                                    break;
                            }
                            break;
                        }
                    }
                }





                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public static int usernameUsed(String username){
        for (Regular r:regular)
            if (Objects.equals(r.getUsername(), username))
                return 1;

        for (Contributor c:contributor)
            if (Objects.equals(c.getUsername(), username))
                return 1;

        for (Admin a:admin)
            if (Objects.equals(a.getUsername(), username))
                return 1;

        return 0;
    }

    private static String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

        // Build the password with random characters
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }

    public static void addUser(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("\nPlease enter the first name of the user:");
        String first_name = myObj.nextLine();
        System.out.println("\nPlease enter the last name of the user:");
        String last_name = myObj.nextLine();

        System.out.println("\nPlease enter the email:");
        String email = myObj.nextLine();

        System.out.println("\nPlease enter user type (Regular/Contributor/Admin):");
        String tip = myObj.nextLine();

        if (!tip.equalsIgnoreCase("regular") && !tip.equalsIgnoreCase("contributor") && !tip.equalsIgnoreCase("admin")){
            System.out.println("\nInvalid input\n");
            return;
        }

        System.out.println("\nPlease enter the country:");
        String country = myObj.nextLine();

        System.out.println("\nPlease enter the age:");
        String age = myObj.nextLine();

        if (!isNumeric(age)){
            System.out.println("\nInvalid input\n");
            return;
        }

        System.out.println("\nPlease enter the gender (Male/Female):");
        String gender = myObj.nextLine();

        if (!Objects.equals(gender.toLowerCase(), "male") && !Objects.equals(gender.toLowerCase(), "female")){
            System.out.println("\nInvalid input\n");
            return;
        }

        System.out.println("\nPlease enter date of birth (dd-MM-yyyy):");
        String userInput = myObj.nextLine();

        LocalDateTime date;
        try {
            LocalDate formatter = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            date = formatter.atStartOfDay();
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd HH:mm:ss.");
            return;
        }

        String username;

        Random rand = new Random();
        do {
            int nr = rand.nextInt(1000);
            username = first_name.toLowerCase() + '_' + last_name.toLowerCase() + '_' + nr;
            System.out.println(username);
        }while (usernameUsed(username) == 1);

        String pass = generatePassword();

        Credentials credentials = new Credentials(email, pass);
        User.Information.InformationBuilder builder = new User.Information.InformationBuilder(first_name + " " + last_name, country, Integer.parseInt(age), gender, credentials, date);
        User.Information info = new User.Information(builder);

        SortedSet<Actor> favA = new TreeSet<>();
        SortedSet<Production> favP = new TreeSet<>();
        SortedSet<Actor> ConA = new TreeSet<>();
        SortedSet<Production> ConP = new TreeSet<>();
        List<Request> requests = new ArrayList<>();
        List<String> notif = new ArrayList<>();

        if (tip.equalsIgnoreCase("regular")){
            Regular reg = new Regular(username, 0, AccountType.REGULAR, info, favP, favA, notif);
            regular.add(reg);
        }
        else if (tip.equalsIgnoreCase("contributor")){
            Contributor con = new Contributor(username, 0, AccountType.CONTRIBUTOR, info, favP, favA, ConP, ConA, requests, notif);
            contributor.add(con);
        }
        else{
            Admin adm = new Admin(username, 0, AccountType.ADMIN, info, favP, favA, ConP, ConA, requests, notif);
            admin.add(adm);
        }


    }

    public static void deleteSystem(User user){
        Scanner myObj = new Scanner(System.in);
        String sir;

        System.out.println("Please choose whether you want to delete an actor or a production");
        System.out.println("Enter a number from these options:");
        System.out.println("1) Actor");
        System.out.println("2) Production");
        System.out.println();

        Contributor con = null;
        String nume;

        for (Contributor c:contributor)
            if (Objects.equals(c.getUsername(), user.getUsername()))
            {
                con = c;
                break;
            }

        sir = myObj.nextLine();
        if (!isNumeric(sir))
        {
            System.out.println("Invalid input\n");
            return;
        }
        int alegere = Integer.parseInt(sir);
        switch (alegere){
            case 1:
                System.out.println("Please enter the name of the actor you want to delete:");
                nume = myObj.nextLine();
                if (con != null){
                    for (Actor a:con.adaugariActori){
                        if(Objects.equals(a.numeActor, nume)) {
                            con.removeActorSystem(nume);
                            actori.remove(a);
                            System.out.println("Actor removed successfully\n");
                            return;
                        }
                    }
                    System.out.println("This actor was not added by you\n");
                    return;
                }
                for (Admin adm:admin){
                    for (Actor a: adm.adaugariActori){
                        if(Objects.equals(a.numeActor, nume)) {
                            adm.removeActorSystem(nume);
                            actori.remove(a);
                            System.out.println("Actor removed successfully\n");
                            return;
                        }
                    }
                }

                System.out.println("This actor was not found\n");
                break;
            case 2:
                System.out.println("Please enter the title of the production you want to delete:");
                nume = myObj.nextLine();
                if (con != null){
                    for (Production p:con.adaugariProd){
                        if(Objects.equals(p.titluProductie, nume)) {
                            con.removeProductionSystem(nume);
                            filme.removeIf(m -> Objects.equals(m.titluProductie, nume));
                            seriale.removeIf(s -> Objects.equals(s.titluProductie, nume));
                            System.out.println("Production removed successfully\n");
                            return;
                        }
                    }
                    System.out.println("This production was not added by you\n");
                    return;
                }
                for (Admin adm:admin){
                    for (Production p:adm.adaugariProd){
                        if(Objects.equals(p.titluProductie, nume)) {
                            adm.removeProductionSystem(nume);
                            filme.removeIf(m -> Objects.equals(m.titluProductie, nume));
                            seriale.removeIf(s -> Objects.equals(s.titluProductie, nume));
                            System.out.println("Production removed successfully\n");
                            return;
                        }
                    }
                }

                System.out.println("This production was not found\n");
                break;
            default:
                System.out.println("Invalid option\n");
                break;
        }
    }

    public static void addDeleteFav(User user){
        String name;
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please choose a number from these options:");
        System.out.println("1) Actor");
        System.out.println("2) Production");
        System.out.println();

        String sir2 = myObj.nextLine();
        if (!isNumeric(sir2))
        {
            System.out.println("Invalid input\n");
            return;
        }
        int choice = Integer.parseInt(sir2);

        int choice2;
        switch (choice){
            case 1:
                System.out.println("These are your favorite actors:\n");
                for (Actor a:user.favoriteActors)
                    System.out.println(a.numeActor);
                System.out.println();
                System.out.println("Please choose a number from these options:");
                System.out.println("1) Add");
                System.out.println("2) Delete");
                System.out.println();

                String sir3 = myObj.nextLine();
                if (!isNumeric(sir3))
                {
                    System.out.println("Invalid input\n");
                    return;
                }
                choice2 = Integer.parseInt(sir3);

                switch (choice2){
                    case 1:
                        System.out.println("Please enter the name of the actor you want to add");
                        name = myObj.nextLine();
                        for (Actor a:actori){
                            if (Objects.equals(a.numeActor, name)){
                                user.favoriteActors.add(a);
                                System.out.println("Actor added successfully\n");
                                return;
                            }
                        }
                        System.out.println("Actor not found");
                        break;
                    case 2:
                        System.out.println("Please enter the name of the actor you want to delete");
                        name = myObj.nextLine();
                        for (Actor a:user.favoriteActors){
                            if (Objects.equals(a.numeActor, name)){
                                user.favoriteActors.remove(a);
                                System.out.println("Actor removed successfully\n");
                                return;
                            }
                        }
                        System.out.println("Actor not found\n");
                        break;
                    default:
                        System.out.println("Invalid choice\n");
                        break;
                }
                break;
            case 2:
                System.out.println("These are your favorite productions:\n");
                for (Production p:user.favoriteProd)
                    System.out.println(p.titluProductie);
                System.out.println();

                System.out.println("Please choose a number from these options:");
                System.out.println("1) Add");
                System.out.println("2) Delete");
                System.out.println();

                String sir4 = myObj.nextLine();
                if (!isNumeric(sir4))
                {
                    System.out.println("Invalid input\n");
                    return;
                }
                choice2 = Integer.parseInt(sir4);
                switch (choice2){
                    case 1:
                        System.out.println("Please enter the title of the production you want to add");
                        name = myObj.nextLine();
                        for (Movie m:filme){
                            if (Objects.equals(m.titluProductie, name)){
                                user.favoriteProd.add(m);
                                System.out.println("Production added successfully\n");
                                return;
                            }
                        }
                        for (Series s:seriale){
                            if (Objects.equals(s.titluProductie, name)){
                                user.favoriteProd.add(s);
                                System.out.println("Production added successfully\n");
                                return;
                            }
                        }
                        System.out.println("Production not found\n");
                        break;
                    case 2:
                        System.out.println("Please enter the title of the production you want to delete");
                        name = myObj.nextLine();
                        for (Production p:user.favoriteProd){
                            if (Objects.equals(p.titluProductie, name)){
                                user.favoriteProd.remove(p);
                                System.out.println("Production removed successfully\n");
                                return;
                            }
                        }
                        System.out.println("Production not found\n");
                        break;
                    default:
                        System.out.println("Invalid choice\n");
                        return;
                }
                break;
            default:
                System.out.println("Invalid choice\n");
                break;
        }
    }
    public static void addDeleteRating(Regular reg){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please choose a number from these options:");
        System.out.println("1) Add rating");
        System.out.println("2) Delete rating");

        String sir = myObj.nextLine();
        if (!isNumeric(sir))
        {
            System.out.println("Invalid input\n");
            return;
        }
        int choice = Integer.parseInt(sir);
        String title;
        switch (choice){
            case 1:
                System.out.println("Please enter the title of the production");
                title = myObj.nextLine();
                for (Movie m:filme){
                    if (Objects.equals(m.titluProductie, title)){
                        for(Rating r: m.evaluari)
                            if (Objects.equals(r.username, reg.getUsername()))
                            {
                                System.out.println("You have already reviewed this production.\n");
                                return;
                            }
                        System.out.println("New rating:");
                        System.out.println("Rating (from 1 to 10):");
                        String rat = myObj.nextLine();

                        if (!isNumeric(rat))
                        {
                            System.out.println("Invalid input\n");
                            return;
                        }
                        int rating = Integer.parseInt(rat);
                        System.out.println("Comment:");
                        String comment = myObj.nextLine();
                        Rating ev = new Rating(reg.getUsername(), rating, comment);
                        m.evaluari.add(ev);
                        reg.increaseExperience();

                        for (Rating r:m.evaluari)
                            if (!Objects.equals(r.username, reg.getUsername()))
                            {
                                for (Regular userReg:regular)
                                    if (Objects.equals(r.username, userReg.getUsername())) {
                                        r.setObserver(userReg);
                                        r.sendNotification("Another user also reviewed " + m.titluProductie +'\n');
                                    }

                                for (Contributor c:contributor)
                                    if (Objects.equals(r.username, c.getUsername())) {
                                        r.setObserver(c);
                                        r.sendNotification("Another user also reviewed " + m.titluProductie + '\n');
                                    }

                                for (Admin a:admin)
                                    if (Objects.equals(r.username, a.getUsername())) {
                                        r.setObserver(a);
                                        r.sendNotification("Another user also reviewed " + m.titluProductie + '\n');
                                    }
                            }

                        for (Contributor c:contributor)
                        {
                            for (Production p:c.adaugariProd)
                                if (Objects.equals(p.titluProductie, m.titluProductie)){
                                    p.setObserver(c);
                                    p.sendNotification("Another user reviewed a production added by you\n");
                                    return;
                                }
                        }
                        for (Admin a:admin)
                        {
                            for (Production p:a.adaugariProd)
                                if (Objects.equals(p.titluProductie, m.titluProductie)){
                                    p.setObserver(a);
                                    p.sendNotification("Another user reviewed a production added by you\n");
                                    return;
                                }
                        }
                        return;
                    }
                }
                for (Series s:seriale){
                    if (Objects.equals(s.titluProductie, title)){
                        for(Rating r:s.evaluari)
                            if (Objects.equals(r.username, reg.getUsername()))
                            {
                                System.out.println("You have already reviewed this production.\n");
                                return;
                            }
                        System.out.println("New rating:");
                        System.out.println("Rating (from 1 to 10):");
                        String rat = myObj.nextLine();

                        if (!isNumeric(rat))
                        {
                            System.out.println("Invalid input\n");
                            return;
                        }
                        int rating = Integer.parseInt(rat);
                        System.out.println("Comment:");
                        String comment = myObj.nextLine();
                        Rating ev = new Rating(reg.getUsername(), rating, comment);
                        s.evaluari.add(ev);

                        for (Rating r:s.evaluari)
                            if (!Objects.equals(r.username, reg.getUsername()))
                            {
                                for (Regular userReg:regular)
                                    if (Objects.equals(r.username, userReg.getUsername())) {
                                        r.setObserver(userReg);
                                        r.sendNotification("Another user also reviewed " + s.titluProductie + '\n');
                                    }

                                for (Contributor c:contributor)
                                    if (Objects.equals(r.username, c.getUsername())) {
                                        r.setObserver(c);
                                        r.sendNotification("Another user also reviewed " + s.titluProductie + '\n');
                                    }

                                for (Admin a:admin)
                                    if (Objects.equals(r.username, a.getUsername())) {
                                        r.setObserver(a);
                                        r.sendNotification("Another user also reviewed " + s.titluProductie + '\n');
                                    }
                            }

                        for (Contributor c:contributor)
                        {
                            for (Production p:c.adaugariProd)
                                if (Objects.equals(p.titluProductie, s.titluProductie)){
                                    p.setObserver(c);
                                    p.sendNotification("Another user reviewed a production added by you\n");
                                    return;
                                }
                        }
                        for (Admin a:admin)
                        {
                            for (Production p:a.adaugariProd)
                                if (Objects.equals(p.titluProductie, s.titluProductie)){
                                    p.setObserver(a);
                                    p.sendNotification("Another user reviewed a production added by you\n");
                                    return;
                                }
                        }
                        return;
                    }
                }
                System.out.println("Production not found\n");
                break;
            case 2:
                System.out.println("Please enter the title of the production");
                title = myObj.nextLine();
                for (Movie m:filme){
                    if (Objects.equals(m.titluProductie, title)){
                        for(Rating r:m.evaluari)
                            if (Objects.equals(r.username, reg.getUsername()))
                            {
                                m.evaluari.remove(r);
                                System.out.println("Production removed successfully\n");
                                return;
                            }
                        System.out.println("You have not given any review to this production\n");
                        return;
                    }
                }
                for (Series s:seriale){
                    if (Objects.equals(s.titluProductie, title)){
                        for(Rating r:s.evaluari)
                            if (Objects.equals(r.username, reg.getUsername()))
                            {
                                s.evaluari.remove(r);
                                System.out.println("Production removed successfully\n");
                                return;
                            }
                        System.out.println("You have not given any review to this production\n");
                        return;
                    }
                }
                System.out.println("Production not found\n");
                break;
            default:
                System.out.println("Invalid choice\n");
                break;
        }
    }

    public static void deleteUser(String name){

        for(Movie m:filme){
            m.evaluari.removeIf(r -> r.username.equals(name));
        }

        for (Series s:seriale){
            s.evaluari.removeIf(e -> Objects.equals(e.username, name));
        }

        Contributor con = null;
        for (Contributor c:contributor)
            if (Objects.equals(c.getUsername(), name)) {
                con = c;
                break;
            }
        if (con != null){
            for (Request r:request)
                if(Objects.equals(r.usernameRezolva, name)) {
                    r.usernameRezolva = "ADMIN";
                    RequestsHolder.cereriAdmini.add(r);
                }

            for (Admin a:admin){
                a.adaugariProd.addAll(con.getAdaugariProd());
                a.adaugariActori.addAll(con.getAdaugariActori());
            }

            contributor.remove(con);
            System.out.println("User was successfully removed!\n");
        }
        else{
            Regular reg = null;
            for (Regular r:regular)
                if (Objects.equals(r.getUsername(), name)){
                    reg = r;
                    break;
                }

            if (reg == null){
                System.out.println("User cannot be removed because it is an admin or cannot be found\n");
            }
            else{
                request.removeIf(r -> Objects.equals(r.usernameCreator, name));
                regular.remove(reg);
                System.out.println("User was successfully removed!\n");
            }

        }

    }

    public static void addDeleteRequest(User user){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please choose a number from these options:");
        System.out.println("1) Add a request");
        System.out.println("2) Delete a request");

        String sir = myObj.nextLine();
        if (!isNumeric(sir))
        {
            System.out.println("Invalid input\n");
            return;
        }
        int choice = Integer.parseInt(sir);
        RequestType tip;
        switch (choice){
            case 1:
                    System.out.println("Choose type of request:");
                    System.out.println("1) Delete accout");
                    System.out.println("2) Actor issue");
                    System.out.println("3) Movie issue");
                    System.out.println("4) Others");
                    String sirissue = myObj.nextLine();
                    if (!isNumeric(sirissue))
                    {
                        System.out.println("Invalid input\n");
                        return;
                    }
                    int issue = Integer.parseInt(sirissue);
                    switch (issue){
                        case 1:
                            tip = RequestType.DELETE_ACCOUNT;
                            break ;
                        case 2:
                            tip = RequestType.ACTOR_ISSUE;
                            break ;
                        case 3:
                            tip = RequestType.MOVIE_ISSUE;
                            break ;
                        case 4:
                            tip = RequestType.OTHERS;
                            break ;
                        default:
                            System.out.println("Invalid option\n");
                            return;
                    }

                    System.out.println("Please describe the problem:");
                    String issueDescr = myObj.nextLine();
                    Request newRequest;
                    if (tip == RequestType.DELETE_ACCOUNT || tip == RequestType.OTHERS){
                        newRequest = new Request(tip, LocalDateTime.now(), issueDescr, user.getUsername(), "ADMIN");
                        RequestsHolder.cereriAdmini.add(newRequest);
                        request.add(newRequest);
                        return;
                    }
                    else{
                        String nume;
                        if (tip == RequestType.MOVIE_ISSUE) {
                            System.out.println("Please enter the title of the movie");
                            nume = myObj.nextLine();

                            for (Contributor c:contributor){
                                for(Production p:c.getAdaugariProd())
                                    if (Objects.equals(nume.toLowerCase(), p.titluProductie.toLowerCase())) {
                                        if (Objects.equals(c.getUsername(), user.getUsername())){
                                            System.out.println("You cannot create a request for a production added by you!\n");
                                            return;
                                        }
                                        newRequest = new Request(tip, LocalDateTime.now(), p.titluProductie, issueDescr, user.getUsername(), c.getUsername());
                                        c.listaCereri.add(newRequest);
                                        request.add(newRequest);

                                        newRequest.setObserver(c);
                                        newRequest.sendNotification("You have received a request\n");
                                        return;
                                    }
                            }

                            for (Admin a : admin) {
                                for (Production p : a.getAdaugariProd())
                                    if (Objects.equals(nume.toLowerCase(), p.titluProductie.toLowerCase())) {
                                        newRequest = new Request(tip, LocalDateTime.now(), p.titluProductie, issueDescr, user.getUsername(), a.getUsername());
                                        a.listaCereri.add(newRequest);
                                        request.add(newRequest);

                                        newRequest.setObserver(a);
                                        newRequest.sendNotification("You have received a request\n");
                                        return;
                                    }
                            }

                            System.out.println("No production was found\n");
                        }
                        else{
                            System.out.println("Please enter the name of the actor");
                            nume = myObj.nextLine();
                            for (Contributor c:contributor){
                                for(Actor act:c.getAdaugariActori())
                                    if (Objects.equals(nume.toLowerCase(), act.numeActor.toLowerCase())) {
                                        if (Objects.equals(c.getUsername(), user.getUsername())){
                                            System.out.println("You cannot create a request for an actor added by you!\n");
                                            return;
                                        }
                                        newRequest = new Request(tip, LocalDateTime.now(), act.numeActor, issueDescr, user.getUsername(), c.getUsername());
                                        request.add(newRequest);
                                        return;
                                    }
                            }
                            for (Admin a : admin) {
                                for(Actor act:a.getAdaugariActori())
                                    if (Objects.equals(nume.toLowerCase(), act.numeActor.toLowerCase())) {
                                        newRequest = new Request(tip, LocalDateTime.now(), act.numeActor, issueDescr, user.getUsername(), a.getUsername());
                                        request.add(newRequest);
                                        return;
                                    }
                            }
                            System.out.println("No actor was found\n");
                        }


                }
                break;
            case 2:
                System.out.println("These are the requests created by you:");
                System.out.println();
                int i = 1;
                for (Request r:request){
                    if (Objects.equals(r.usernameCreator, user.getUsername())) {
                        System.out.print(i + ") ");
                        i++;
                        r.displayInfo();
                    }
                }
                System.out.println("Please choose the number of the request you want to delete or the cancel option typing x");

                String sir2 = myObj.nextLine();
                if (!isNumeric(sir2))
                {
                    System.out.println("Invalid input\n");
                    return;
                }
                int alegere = Integer.parseInt(sir2);
                if (alegere < 1 && alegere > i)
                {
                    System.out.println("Invalid option\n");
                    return;
                }
                i = 1;
                for (Request r:request){
                    if (i == alegere) {
                        request.remove(r);
                        break;
                    }
                    i++;
                }
                System.out.println("The request was successfully deleted!\n");
                System.out.println();

                break;
            default:
                System.out.println("Invalid option\n");
                break;
        }

    }

    public static void searchFor(Scanner myObj){
        System.out.println("Please choose a number:");
        System.out.println("1) Actor");
        System.out.println("2) Movie");
        System.out.println("3) Series");
        String titlu;
        int gasit = 0;

        String sirsearch = myObj.nextLine();
        if (!isNumeric(sirsearch)){
            System.out.println("Invalid input\n");
            return;
        }
        int search = Integer.parseInt(sirsearch);
        switch (search) {
            case 1:
                System.out.println("Enter name:");
                String nume = myObj.nextLine();
                for (Actor om : actori)
                    if (om.numeActor.toLowerCase().contains(nume.toLowerCase())) {
                        om.displayInfo();
                        gasit = 1;
//                            break;
                    }
                if (gasit == 0)
                    System.out.println("No results\n");
                break ;

            case 2:
                System.out.println("Introdu titlu:");
                titlu = myObj.nextLine();
                for (Movie m : filme)
                    if (m.titluProductie.toLowerCase().contains(titlu.toLowerCase())) {
                        m.displayInfo();
                        gasit = 1;
//                            break;
                    }
                if (gasit == 0)
                    System.out.println("No results\n");
                break ;
            case 3:
                System.out.println("Introdu titlu:");
                titlu = myObj.nextLine();
                for (Series s : seriale)
                    if (s.titluProductie.toLowerCase().contains(titlu.toLowerCase())) {
                        s.displayInfo();
                        gasit = 1;
                    }
                if (gasit == 0)
                    System.out.println("No results\n");
                break;
            default:
                System.out.println("Invalid option\n");
                break;
        }

    }
    public static void addSystem(User conPrezent){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Choose:");
        System.out.println("1) Actor");
        System.out.println("2) Production");

        String sir = myObj.nextLine();
        if (!isNumeric(sir))
        {
            System.out.println("Invalid input\n");
            return;
        }
        int choice = Integer.parseInt(sir);
        switch (choice){
            case 1:
                System.out.println("Enter name:");
                String nume = myObj.nextLine();
                System.out.println("Enter biography:");
                String biografie = myObj.nextLine();
                System.out.println("Number of productions:");

                String sirnr = myObj.nextLine();
                if (!isNumeric(sirnr)){
                    System.out.println("Invalid input\n");
                    return;
                }
                int nr = Integer.parseInt(sirnr);
                List<Pairs> per = new ArrayList<>();
                for (int i = 1; i <= nr; i++){
                    System.out.println("Title: ");
                    String film = myObj.nextLine();
                    System.out.println("Type (Movie/Series): ");
                    String tip = myObj.nextLine();
                    Pairs p = new Pairs(film, tip);
                    per.add(p);
                }
                Actor a = new Actor(nume, per, biografie);
                conPrezent.addActorSystem(a);
                actori.add(a);
                break;
            case 2:
                System.out.println("Enter title:");
                String title = myObj.nextLine();
                System.out.println("Enter type (Movie/Series):");
                String type = myObj.nextLine();

                if (!Objects.equals(type, "Movie") && !Objects.equals(type, "Series")){
                    System.out.println("Invalid input\n");
                    return;
                }

                System.out.println("Enter the genres of the production, separated by comma:");
                String input = myObj.nextLine();

                String[] parts = input.split(",");
                List<Genre> genuri = new ArrayList<>();

                for (String part : parts) {
                    int este = 0;
                    for (Genre g:Genre.values())
                        if (Objects.equals(part, g.toString())) {
                            este = 1;
                            genuri.add(g);
                        }
                    if (este == 0)
                    {
                        System.out.println("Genre " + part + " not found in the system\n");
                        return;
                    }
                }

                System.out.println("Enter the directors of the production, separated by comma:");
                input = myObj.nextLine();

                parts = input.split(",");
                List<String> directors = new ArrayList<>();

                for (String part : parts) {
                    directors.add(part.trim());
                }

                System.out.println("Enter the actors of the production, separated by comma:");
                input = myObj.nextLine();

                parts = input.split(",");
                List<String> actors = new ArrayList<>();

                for (String part : parts) {
                    actors.add(part.trim());
                }

                System.out.println("Enter plot description:");
                String plot = myObj.nextLine();

                System.out.println("Enter release year:");
                String release = myObj.nextLine();
                if (!isNumeric(release) && !Objects.equals(release, "\n"))
                {
                    System.out.println("Invalid input\n");
                    return;
                }
                int year;
                if (release.equals("\n"))
                    year = 0;
                else year = Integer.parseInt(release);

                List<Rating> ratings = new ArrayList<>();

                if (type.equals("Movies")){
                    System.out.println("Enter duration (in minutes):");
                    String minute = myObj.nextLine();
                    String duration = minute + "minutes";

                    Movie m = new Movie(title, directors, actors, genuri, ratings, plot, 0.0, duration, year);
                    filme.add(m);
                    conPrezent.addProductionSystem(m);
                    return;
                }

                System.out.println("Enter number of seasons:");
                String nrsez = myObj.nextLine();

                if (!isNumeric(nrsez) && !Objects.equals(release, "\n"))
                {
                    System.out.println("Invalid input\n");
                    return;
                }

                int nrs;
                if (nrsez.equals("\n"))
                    nrs = 0;
                else nrs = Integer.parseInt(nrsez);

                Map<String, List<Episode>> dictionar = new HashMap<>();


                for (int i = 1; i <= nrs; i++){
                    System.out.println("Season " + i);
                    System.out.println("Enter number of episodes in this season:");
                    String nre = myObj.nextLine();
                    if (!isNumeric(nre))
                    {
                        System.out.println("Invalid input\n");
                        return;
                    }
                    int nrep = Integer.parseInt(nre);

                    List<Episode> listaEpisoade = new ArrayList<>();
                    for (int e = 1; e <= nrep; e++)
                    {
                        System.out.println("Enter episode name:");
                        String epname = myObj.nextLine();
                        System.out.println("Enter duration of episode (in minutes):");
                        String d = myObj.nextLine();
                        String minutes = d + "minutes";
                        Episode episod = new Episode(epname, minutes);
                        listaEpisoade.add(episod);
                    }

                    dictionar.put("Season " + i, listaEpisoade);

                }
                Series serial = new Series(title, directors, actors, genuri, ratings, plot, 0.0, year, nrs, dictionar);
                seriale.add(serial);
                conPrezent.addProductionSystem(serial);

                break;
            default:
                System.out.println("Invalid input\n");
                break;
        }

    }
    public static int testare(String email, String pass){
        for (Regular reg:regular){
            if (Objects.equals(reg.getEmail(), email)){
                if(!Objects.equals(reg.getPass(), pass)){
                    System.out.println("Password or username incorrect. Please try again!\n");
                    return 0;
                }
                else return 1;
            }
        }
        for (Contributor con:contributor){
            if (Objects.equals(con.getEmail(), email)){
                if(!Objects.equals(con.getPass(), pass)){
                    System.out.println("Password or username incorrect. Please try again!\n");
                    return 0;
                }
                else return 2;
            }
        }
        for (Admin adm:admin){
            if (Objects.equals(adm.getEmail(), email)){
                if(!Objects.equals(adm.getPass(), pass)){
                    System.out.println("Password or username incorrect. Please try again!\n");
                    return 0;
                }
                else return 3;
            }
        }
        System.out.println("Password or username incorrect. Please try again!");
        return 0;
    }

    public static void main(String[] args) throws IOException {

        JSONParser jsonParser = new JSONParser();

        actori = new ArrayList<>();
        try(FileReader reader = new FileReader("src/test/resources/testResources/actors.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray j = (JSONArray) obj;
            for (Object o : j) {
                JSONObject jsonObject = (JSONObject) o;
                String nameActor = (String) ((JSONObject) o).get("name");
                String biography = (String) ((JSONObject) o).get("biography");

                JSONArray performances = (JSONArray) jsonObject.get("performances");
                perechi = new ArrayList<>();

                for (Object x:performances){
                    String title = (String) ((JSONObject) x).get("title");
                    String type = (String) ((JSONObject) x).get("type");
                    Pairs pereche = new Pairs(title, type);
                    perechi.add(pereche);
                }
                Actor actornou = new Actor(nameActor, perechi, biography);
                actori.add(actornou);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        filme = new ArrayList<>();
        seriale = new ArrayList<>();
        try(FileReader reader = new FileReader("src/test/resources/testResources/production.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray j = (JSONArray) obj;
            for (Object o : j) {
                JSONObject jsonObject = (JSONObject) o;
                String title = (String) ((JSONObject) o).get("title");
                String type = (String) ((JSONObject) o).get("type");
                String plot = (String) ((JSONObject) o).get("plot");
                Double averageRating = (Double) ((JSONObject) o).get("averageRating");
                Long releaseYear = (Long) ((JSONObject) o).get("releaseYear");
                if (releaseYear == null)
                    releaseYear = 0L;
                String filmDuration = (String) ((JSONObject) o).get("filmDuration");
                JSONArray directors = (JSONArray) jsonObject.get("directors");
                List<String> regizori = new ArrayList<>();
                for (Object x:directors){
                    String director = (String) x;
                    regizori.add(director);
                }

                JSONArray actors = (JSONArray) jsonObject.get("actors");
                List<String> actori = new ArrayList<>();
                for (Object x:actors){
                    String actor = (String) x;
                    actori.add(actor);
                }

                JSONArray genuri = (JSONArray) jsonObject.get("genres");
                List<Genre> genres = new ArrayList<>();
                for (Object x:genuri){
                    String gen = (String) x;
                    for(Genre g:Genre.values())
                        if (Objects.equals(g.toString(), gen)){
                            genres.add(g);
//                            break;
                        }

                }
                Long nota;
                JSONArray ratings = (JSONArray) jsonObject.get("ratings");
                List<Rating> ratinguri = new ArrayList<>();
                for (Object x:ratings){
                    String username = (String) ((JSONObject) x).get("username");
                    nota = (Long) ((JSONObject) x).get("rating");
                    String comment = (String) ((JSONObject) x).get("comment");
                    Rating r1 = new Rating(username, Math.toIntExact(nota), comment);
                    ratinguri.add(r1);

                }
                if (Objects.equals(type, "Movie")){
                    Movie film = new Movie(title, regizori, actori, genres, ratinguri, plot, averageRating, filmDuration, Math.toIntExact(releaseYear));
                    filme.add(film);
                }
                else{
                    Long numSeasons = (Long) ((JSONObject) o).get("numSeasons");
                    JSONObject seasonsObj = (JSONObject) ((JSONObject) o).get("seasons");
                    Map<String, List<Episode>> dictionar = new HashMap<>();
                    int i = 1;
                    for (Object k:seasonsObj.keySet()){
                        JSONArray specificSeason = (JSONArray) seasonsObj.get("Season " + i);
                        List<Episode> listaEpisoade = new ArrayList<>();
                        for(Object x:specificSeason){
                            String episodeName = (String) ((JSONObject) x).get("episodeName");
                            String duration = (String) ((JSONObject) x).get("duration");
                            Episode episod = new Episode(episodeName, duration);
                            listaEpisoade.add(episod);
                        }
                        dictionar.put("Season " + i, listaEpisoade);
                        i++;
                    }
                    Series serial = new Series(title, regizori, actori, genres, ratinguri, plot, averageRating, Math.toIntExact(releaseYear), Math.toIntExact(numSeasons), dictionar);
                    seriale.add(serial);

                }


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        String username;
        String sir_exp;
        Long age;
        int exp;
        String pass;
        String email = null;
        AccountType tipCont = null;
        regular = new ArrayList<>();
        contributor = new ArrayList<>();
        admin = new ArrayList<>();

        try( FileReader reader = new FileReader("accounts.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray j = (JSONArray) obj;
            for (Object o : j) {
                JSONObject jsonObject = (JSONObject) o;
                username = (String) ((JSONObject) o).get("username");
                sir_exp = (String) ((JSONObject) o).get("experience");
                if (sir_exp == null)
                    exp = 0;
                else exp = Integer.parseInt(sir_exp);

                JSONObject information = (JSONObject) jsonObject.get("information");

                email = (String) ((JSONObject) information.get("credentials")).get("email");
                pass = (String) ((JSONObject) information.get("credentials")).get("password");
                String name = (String) information.get("name");
                String country = (String) information.get("country");
                age = (Long) information.get("age");
                String gender = (String) information.get("gender");
                String birthDate = (String) information.get("birthDate");
                LocalDate formatter = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDateTime date = formatter.atStartOfDay();

                String userType = (String) ((JSONObject) o).get("userType");
                for (AccountType type : AccountType.values()) {
                    if (type.name().equalsIgnoreCase(userType)) {
                        tipCont = type;
                    }
                }
                Credentials credentials = new Credentials(email, pass);
                User.Information.InformationBuilder builder = new User.Information.InformationBuilder(name, country, Math.toIntExact(age), gender, credentials, date);
                User.Information info = new User.Information(builder);

                SortedSet<Production> favP = new TreeSet<>();
                JSONArray favoriteProductions = (JSONArray) ((JSONObject) o).get("favoriteProductions");
                if (favoriteProductions != null){
                    for (Object k:favoriteProductions){
                        String nume = (String) k;
                        for (Movie m:filme){
                            if(Objects.equals(m.titluProductie, nume)){
                                favP.add(m);
                            }
                        }
                        for (Series s:seriale){
                            if (Objects.equals(s.titluProductie, nume)){
                                favP.add(s);
                            }
                        }
                    }
                }

                SortedSet<Actor> favA = new TreeSet<>();
                JSONArray favoriteActors = (JSONArray) ((JSONObject) o).get("favoriteActors");
                if (favoriteActors != null){
                    for (Object k:favoriteActors){
                        String nume = (String) k;
                        for (Actor a:actori){
                            if(Objects.equals(a.numeActor, nume)){
                                favA.add(a);
                                break;
                            }
                        }
                    }

                }

                SortedSet<Production> addProd = new TreeSet<>();
                JSONArray addedProductions = (JSONArray) ((JSONObject) o).get("productionsContribution");
                if (addedProductions != null){
                    for (Object k:addedProductions){
                        String nume = (String) k;
                        for (Movie m:filme){
                            if(Objects.equals(m.titluProductie, nume)){
                                addProd.add(m);
//                                break;
                            }
                        }
                        for (Series s:seriale){
                            if (Objects.equals(s.titluProductie, nume)){
                                addProd.add(s);
//                                break;
                            }
                        }
                    }

                }

                SortedSet<Actor> addActors = new TreeSet<>();
                JSONArray addedActors = (JSONArray) ((JSONObject) o).get("actorsContribution");
                if (addedActors != null){
                    for (Object k:addedActors){
                        String nume = (String) k;
                        for (Actor a:actori){
                            if(Objects.equals(a.numeActor, nume)){
                                addActors.add(a);
                                break;
                            }
                        }
                    }
                }
                List<Request> requestList = new ArrayList<>();
                List<String> notifications = new ArrayList<>();
                if (tipCont == AccountType.REGULAR){
                    UserFactory clientRegularFact = new UserFactory();
                    Regular clientRegular = clientRegularFact.createRegular(username, exp, tipCont, info, favP, favA, notifications);
                    regular.add(clientRegular);
                }
                else if (tipCont == AccountType.CONTRIBUTOR){

                    UserFactory clientContributorFact = new UserFactory();
                    Contributor clientContributor = clientContributorFact.createContributor(username, exp, tipCont, info, favP, favA, addProd, addActors, requestList, notifications);
                    contributor.add(clientContributor);

                }
                else {
                    UserFactory clientAdminFact = new UserFactory();
                    Admin clientAdmin = clientAdminFact.createAdmin(username, exp, tipCont, info, favP, favA, addProd, addActors, requestList, notifications);
                    admin.add(clientAdmin);
                }

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        request = new ArrayList<>();
        try(FileReader reader = new FileReader("src/test/resources/testResources/requests.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray j = (JSONArray) obj;
            for (Object o : j) {
                RequestType cerere = null;

                String type = (String) ((JSONObject) o).get("type");
                String createdDate = (String) ((JSONObject) o).get("createdDate");
                String username_creator = (String) ((JSONObject) o).get("username");
                String username_rezolves = (String) ((JSONObject) o).get("to");
                String description = (String) ((JSONObject) o).get("description");


                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                LocalDateTime creationDate = LocalDateTime.parse(createdDate, formatter);

                for(RequestType req:RequestType.values()){
                    if (Objects.equals(req.toString(), type)){
                        cerere = req;
//                        break;
                    }
                }

                Request newRequest;
                if (cerere == RequestType.ACTOR_ISSUE){
                    String nume = (String) ((JSONObject) o).get("actorName");
                    newRequest = new Request(cerere, creationDate, nume, description, username_creator, username_rezolves);

                }
                else if (cerere == RequestType.MOVIE_ISSUE)
                {
                    String nume = (String) ((JSONObject) o).get("movieTitle");
                    newRequest = new Request(cerere, creationDate, nume, description, username_creator, username_rezolves);
                }
                else{
                    newRequest = new Request(cerere, creationDate, description, username_creator, username_rezolves);
                }

                if (cerere == RequestType.DELETE_ACCOUNT || cerere == RequestType.OTHERS){
                    RequestsHolder.cereriAdmini.add(newRequest);
                }

                for (Contributor c:contributor)
                    if (Objects.equals(c.getUsername(), username_rezolves))
                        c.listaCereri.add(newRequest);
                for(Admin a:admin)
                    if (Objects.equals(a.getUsername(), username_rezolves))
                        a.listaCereri.add(newRequest);
                request.add(newRequest);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Scanner myObj = new Scanner(System.in);

        login: while(true) {

            int gasit = 0;
            System.out.println("Welcome back! Enter your credentials!");

            while (gasit == 0) {
                System.out.println("email:");
                email = myObj.nextLine();

                System.out.println("password:");
                pass = myObj.nextLine();

                gasit = testare(email, pass);

            }

            if (gasit == 1) {
                Regular regPrezent = null;
                for (Regular reg : regular)
                    if (Objects.equals(reg.getEmail(), email)) {
                        System.out.println("Welcome back user " + reg.getUsername() + '!');
                        regPrezent = reg;
                        break;
                    }

                System.out.println("Username: " + regPrezent.getUsername());
                System.out.println("User experience: " + regPrezent.getExperience());

                beginningR: while(true) {
                    System.out.println("Choose action:");
                    System.out.println("1) View production details");
                    System.out.println("2) View actors details");
                    System.out.println("3) View notifications");
                    System.out.println("4) Search for actor/movie/series");
                    System.out.println("5) Add/Delete actor/movie/series to/from favorites");
                    System.out.println("6) Add/Delete request");
                    System.out.println("7) Add/Delete production review");
                    System.out.println("8) Logout");


                    String sir = myObj.nextLine();
                    if (!isNumeric(sir))
                    {
                        System.out.println("Invalid input");
                         break ;
                    }
                    int alegere = Integer.parseInt(sir);
                    switch (alegere) {
                        case 1:
                            for (Movie film : filme)
                                film.displayInfo();
                            for (Series serial : seriale)
                                serial.displayInfo();
                            continue beginningR;
                        case 2:
                            for (Actor a : actori)
                                a.displayInfo();
                            continue beginningR;
                        case 3:
                            for (String n:regPrezent.getNotifications())
                                System.out.println(n);
                            System.out.println();
                            continue beginningR;
                        case 4:
                            searchFor(myObj);
                            continue beginningR;
                        case 5:
                            addDeleteFav(regPrezent);

                            continue beginningR;
                        case 6:
                            addDeleteRequest(regPrezent);
                            continue beginningR;
                        case 7:
                            addDeleteRating(regPrezent);

                            continue beginningR;
                        case 8:
                            continue login;

                    }
                }

            } else if (gasit == 2) {
                Contributor conPrezent = null;
                for (Contributor con : contributor)
                    if (Objects.equals(con.getEmail(), email)) {
                        System.out.println("Welcome back user " + con.getUsername() + '!');
                        conPrezent = con;
                        break;
                    }

                beginningC: while(true) {
                    System.out.println("Username: " + conPrezent.getUsername());
                    System.out.println("User experience: " + conPrezent.getExperience());
                    System.out.println("Choose action:");
                    System.out.println("1) View production details");
                    System.out.println("2) View actors details");
                    System.out.println("3) View notifications");
                    System.out.println("4) Search for actor/movie/series");
                    System.out.println("5) Add/Delete actor/movie/series to/from favorites");
                    System.out.println("6) Add/Delete request");
                    System.out.println("7) Add/Delete actor/movie/series from system");
                    System.out.println("8) View and solve requests");
                    System.out.println("9) Update production/actor details");
                    System.out.println("10) Logout");


                    String sir = myObj.nextLine();
                    if (!isNumeric(sir))
                    {
                        System.out.println("Invalid input");
                        continue beginningC;
                    }
                    int alegere = Integer.parseInt(sir);
                    switch (alegere) {
                        case 1:
                            for (Movie film : filme)
                                film.displayInfo();
                            for (Series serial : seriale)
                                serial.displayInfo();
                            continue beginningC;
                        case 2:
                            for (Actor a : actori)
                                a.displayInfo();
                            continue beginningC;
                        case 3:
                            for (String n:conPrezent.getNotifications())
                                System.out.println(n);
                            System.out.println();
                            continue beginningC;
                        case 4:
                            searchFor(myObj);
                            continue beginningC;
                        case 5:
                            addDeleteFav(conPrezent);
                            continue beginningC;
                        case 6:
                            addDeleteRequest(conPrezent);
                            continue beginningC;
                        case 7:
                            System.out.println("Please enter the number of one of these actions");
                            System.out.println("1) Add to the system");
                            System.out.println("2) Delete from the system");

                            String choice = myObj.nextLine();
                            if (!isNumeric(choice)){
                                System.out.println("Invalid choice\n");
                                continue login;
                            }
                            int c = Integer.parseInt(choice);
                            switch (c){
                                case 1:
                                    IMDB.addSystem(conPrezent);
                                    break;
                                case 2:
                                    deleteSystem(conPrezent);
                                default:
                                    System.out.println("Invalid choice\n");
                                    break;
                            }
                            continue beginningC;
                        case 8:
                            System.out.println();
                            System.out.println("These are the requests you can solve");
                            int i = 0;

                            for (Request r : conPrezent.listaCereri) {
                                i++;
                                System.out.print(i + ") ");
                                r.displayInfo();
                                System.out.println();

                            }
                            System.out.println("Enter the number of the request you want to solve or type anything else to go back to menu");


                            String sir2 = myObj.nextLine();
                            if (!isNumeric(sir2))
                            {
                                System.out.println("Invalid input");
                                continue beginningC;
                            }
                            int choicee = Integer.parseInt(sir2);
                            if (choicee < 1 || choicee > i)
                                continue beginningC;

                            int k = 0;
                            Request request1 = null;

                            for (Request r : conPrezent.listaCereri) {
                                k++;
                                if (k == choicee) {
                                    request1 = r;
                                    conPrezent.listaCereri.remove(r);
                                    break;
                                }

                            }
                            request.remove(request1);

                            System.out.println("Choose the number of an option:");
                            System.out.println("1) Reject the request");
                            System.out.println("2) Solve the request");

                            String sir3 = myObj.nextLine();
                            if (!isNumeric(sir3))
                            {
                                System.out.println("Invalid input");
                                continue beginningC;
                            }
                            int choice2 = Integer.parseInt(sir3);
                            switch (choice2){
                                case 1:
                                    for (Regular reg:regular)
                                        if (Objects.equals(reg.getUsername(), request1.usernameCreator)) {
                                            request1.setObserver(reg);
                                            break;
                                        }

                                    for (Contributor con:contributor)
                                        if (Objects.equals(con.getUsername(), request1.usernameCreator)) {
                                            request1.setObserver(con);
                                            break;
                                        }

                                    for (Admin a:admin)
                                        if (Objects.equals(a.getUsername(), request1.usernameCreator)) {
                                            request1.setObserver(a);
                                            break;
                                        }

                                    request1.sendNotification("One of your requests was rejected");
                                    break;
                                case 2:
                                    if (request1.getTipCerere() == RequestType.MOVIE_ISSUE || request1.getTipCerere() == RequestType.ACTOR_ISSUE){
                                        for (Regular reg:regular)
                                            if (Objects.equals(reg.getUsername(), request1.usernameCreator)) {
                                                reg.increaseExperience();
                                                request1.setObserver(reg);
                                                break;
                                            }

                                        for (Contributor con:contributor)
                                            if (Objects.equals(con.getUsername(), request1.usernameCreator)) {
                                                request1.setObserver(con);
                                                con.increaseExperience();
                                                break;
                                            }

                                        request1.sendNotification("One of your request was solved, your experience was increased");
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }
                            continue beginningC;
                        case 9:
                            updateActorProduction();
                            continue beginningC;
                        case 10:
                            continue login;

                    }
                }

            } else {
                Admin admPrezent = null;
                for (Admin adm : admin)
                    if (Objects.equals(adm.getEmail(), email)) {
                        admPrezent = adm;
                        break;
                    }

                System.out.println("Welcome back user " + admPrezent.getUsername() + '!');

                beginningA:
                while (true){
                    System.out.println("Username: " + admPrezent.getUsername());
                    System.out.println("User experience: -");
                    System.out.println("Choose action:");
                    System.out.println("1) View production details");
                    System.out.println("2) View actors details");
                    System.out.println("3) View notifications");
                    System.out.println("4) Search for actor/movie/series");
                    System.out.println("5) Add/Delete actor/movie/series to/from favorites");
                    System.out.println("6) Add/Delete user");
                    System.out.println("7) Add/Delete actor/movie/series from system");
                    System.out.println("8) Update production/actor details");
                    System.out.println("9) Solve a request");
                    System.out.println("10) Logout");



                    String sir = myObj.nextLine();
                    if (!isNumeric(sir))
                    {
                        System.out.println("Invalid input");
                        continue beginningA;
                    }
                    int alegere = Integer.parseInt(sir);
                    switch (alegere) {
                        case 1:
                            for (Movie film : filme)
                                film.displayInfo();
                            for (Series serial : seriale)
                                serial.displayInfo();
                            continue beginningA;
                        case 2:
                            for (Actor a : actori)
                                a.displayInfo();
                            continue beginningA;
                        case 3:
                            for (String n:admPrezent.getNotifications())
                                System.out.println(n);
                            System.out.println();
                            continue beginningA;
                        case 4:
                            searchFor(myObj);
                            continue beginningA;
                        case 5:
                            addDeleteFav(admPrezent);
                            continue beginningA;
                        case 6:
                            System.out.println("These are the Regular users in the systems:");
                            for (Regular r : regular)
                                System.out.println(r.getUsername());
                            System.out.println();
                            System.out.println("These are the Contributor users in the systems:");
                            for (Contributor c : contributor)
                                System.out.println(c.getUsername());
                            System.out.println();
                            System.out.println("PLease choose the number of the action you want to do:");
                            System.out.println("1) Add user");
                            System.out.println("2) Remove user");
                            String choice = myObj.nextLine();

                            if (!isNumeric(choice))
                            {
                                System.out.println("\nInvalid input\n");
                                return;
                            }

                            switch (Integer.parseInt(choice)){
                                case 1:
                                    addUser();
                                    break;
                                case 2:
                                    System.out.println("Please type the username of the user you want to remove");
                                    String deScos = myObj.nextLine();
                                    deleteUser(deScos);
                                    break;
                                default:
                                    System.out.println("\nInvalid choice\n");
                                    break;
                            }

                            continue beginningA;
                        case 7:
                            System.out.println("Please enter the number of one of these actions");
                            System.out.println("1) Add to the system");
                            System.out.println("2) Delete from the system");

                            String choice3 = myObj.nextLine();
                            if (!isNumeric(choice3)){
                                System.out.println("Invalid choice\n");
                                continue login;
                            }
                            int c = Integer.parseInt(choice3);
                            switch (c){
                                case 1:
                                    IMDB.addSystem(admPrezent);
                                    break;
                                case 2:
                                    deleteSystem(admPrezent);
                                default:
                                    System.out.println("Invalid choice\n");
                                    break;
                            }

                            continue beginningA;
                        case 8:
                            updateActorProduction();
                            continue beginningA;
                        case 9:
                            System.out.println();
                            System.out.println("These are the requests you can solve");
                            int i = 0;
                            for (Request r : RequestsHolder.cereriAdmini) {
                                i++;
                                System.out.print(i + ") ");
                                r.displayInfo();
                                System.out.println();

                            }
                            for (Request r : admPrezent.listaCereri) {
                                i++;
                                System.out.print(i + ") ");
                                r.displayInfo();
                                System.out.println();

                            }
                            System.out.println("Enter the number of the request you want to solve or type anything else to go back to menu");

                            String sir2 = myObj.nextLine();
                            if (!isNumeric(sir2))
                            {
                                System.out.println("Invalid input");
                                continue beginningA;
                            }
                            int choicee = Integer.parseInt(sir2);
                            if (choicee < 1 || choicee > i)
                                continue beginningA;

                            int k = 0;
                            Request request1 = null;
                            for (Request r : RequestsHolder.cereriAdmini) {
                                k++;
                                if (k == i) {
                                    request1 = r;
                                    RequestsHolder.cereriAdmini.remove(r);
                                    break;
                                }
                            }
                            for (Request r : admPrezent.listaCereri) {
                                k++;
                                if (k == i) {
                                    request1 = r;
                                    admPrezent.listaCereri.remove(r);
                                    break;
                                }

                            }
                            request.remove(request1);

                            System.out.println("Choose the number of an option:");
                            System.out.println("1) Reject the request");
                            System.out.println("2) Solve the request");


                            String sir3 = myObj.nextLine();
                            if (!isNumeric(sir3))
                            {
                                System.out.println("Invalid input");
                                continue beginningA;
                            }
                            int choice2 = Integer.parseInt(sir3);
                            switch (choice2){
                                case 1:
                                    for (Regular reg:regular)
                                        if (Objects.equals(reg.getUsername(), request1.usernameCreator)) {
                                            request1.setObserver(reg);
                                            break;
                                        }

                                    for (Contributor con:contributor)
                                        if (Objects.equals(con.getUsername(), request1.usernameCreator)) {
                                            request1.setObserver(con);
                                            break;
                                        }

                                    for (Admin a:admin)
                                        if (Objects.equals(a.getUsername(), request1.usernameCreator)) {
                                            request1.setObserver(a);
                                            break;
                                        }

                                    request1.sendNotification("One of your requests was rejected");
                                    break;
                                case 2:
                                    if (request1.getTipCerere() == RequestType.MOVIE_ISSUE || request1.getTipCerere() == RequestType.ACTOR_ISSUE){
                                        for (Regular reg:regular)
                                            if (Objects.equals(reg.getUsername(), request1.usernameCreator)) {
                                                reg.increaseExperience();
                                                request1.setObserver(reg);
                                                break;
                                            }

                                        for (Contributor con:contributor)
                                            if (Objects.equals(con.getUsername(), request1.usernameCreator)) {
                                                con.increaseExperience();
                                                request1.setObserver(con);
                                                break;
                                            }
                                        request1.sendNotification("One of your request was solved");
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }


                            continue beginningA;
                        case 10:
                            continue login;
                    }
                }
            }

        }


    }

}
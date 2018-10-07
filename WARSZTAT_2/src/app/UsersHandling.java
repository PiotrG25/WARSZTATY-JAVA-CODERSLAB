package app;

import classes.Users;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UsersHandling {
    public static void addUser(Connection conn)throws SQLException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Dodawanie uzytkownika");

        System.out.println("Podaj nazwe uzytkownika(username)");
        String username = scan.next();

        System.out.println("Podaj email");
        String email = scan.next();

        System.out.println("Podaj haslo(password)");
        String password = scan.next();

        System.out.println("Podaj do ktorej grupy nalezy(user_group_id)");
        int user_group_id;
        while(!scan.hasNextInt()){
            System.out.println("Podaj wartosc calkowita");
            System.out.println("Podaj do ktorej grupy nalezy(user_group_id)");
        }
        user_group_id = scan.nextInt();

        Users user = new Users(username,email,password,user_group_id);

        boolean added = false;

        do{
            String adding = user.saveToDB(conn);

            switch (adding){
                case "group":
                    System.out.println("Podana grupa(user_group_id) nie istnieje");
                    while(!scan.hasNextInt()){
                        System.out.println("Podaj wartosc calkowita");
                        scan.next();
                    }
                    user_group_id = scan.nextInt();
                    user.setUser_group_id(user_group_id);
                    break;
                case "email":
                    System.out.println("Podany email juz istnieje");
                    email = scan.next();
                    user.setEmail(email);
                    break;
                default:
                    added = true;
                    break;
            }
        }while(added == false);

        scan.close();
        System.out.println("----------");
    }

    public static void editUser(Connection conn)throws SQLException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Edytowanie uzytkownika");

        System.out.println("Podaj id uzytkownika");
        int id;
        while (!scan.hasNextInt()){
            System.out.println("To nie jest liczba calkowita");
            scan.next();
        }
        id = scan.nextInt();

        Users user = Users.loadUserById(conn, id);
        while (user == null){
            System.out.println("Nie ma uzytkownika o takim id");
            System.out.println("Podaj id uzytkownika");
            while (!scan.hasNextInt()){
                System.out.println("To nie jest liczba calkowita");
                scan.next();
            }
            id = scan.nextInt();
            user = Users.loadUserById(conn, id);
        }

        boolean repeatchoice;

        do{
            System.out.println("Podaj wartosc jaka chcesz edytowac");
            System.out.println("[username] - nazwa uzytkownika");
            System.out.println("[email] - email uzytkownika");
            System.out.println("[group] - grupa uzytkownika");
            System.out.println("[password] - haslo uzytkownika");

            String toEdit = scan.next();
            repeatchoice = false;

            switch (toEdit){
                case "username":
                    System.out.println("Podaj nowa nazwe uzytkownika");
                    user.setUserName(scan.next());
                    break;
                case "email":
                    System.out.println("Podaj nowy email");
                    user.setEmail(scan.next());
                    break;
                case "group":
                    System.out.println("Podaj id grupy");
                    while (!scan.hasNextInt()){
                        System.out.println("to nie jest liczba calkowita");
                        scan.next();
                    }
                    user.setUser_group_id(scan.nextInt());
                    break;
                case "password":
//                todo ustawic podwujne potwierdzenie hasla i sprawdzenie czy uzytkownik zna stare haslo
                    System.out.println("Podaj nowe haslo");
                    user.setPassword(scan.next());
                    break;
                default:
                    repeatchoice = true;
                    System.out.println("Wybierz z pocizszych");
                    break;
            }
        }while(repeatchoice);

        boolean added = false;

        do{
            String adding = user.saveToDB(conn);

            switch (adding){
                case "group":
                    System.out.println("Podana grupa(user_group_id) nie istnieje");
                    while(!scan.hasNextInt()){
                        System.out.println("Podaj wartosc calkowita");
                        scan.next();
                    }
                    user.setUser_group_id(scan.nextInt());
                    break;
                case "email":
                    System.out.println("Podany email juz istnieje");
                    user.setEmail(scan.next());
                    break;
                default:
                    added = true;
                    break;
            }
        }while(added == false);

        scan.close();
        System.out.println("----------");
    }

    public static void deleteUser(Connection conn)throws SQLException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Usuwanie uzytkownika");

        System.out.println("Podaj id uzytkownika");
        int id;
        while (!scan.hasNextInt()){
            System.out.println("To nie jest liczba calkowita");
            scan.next();
        }
        id = scan.nextInt();

        Users user = Users.loadUserById(conn, id);
        while (user == null){
            System.out.println("Nie ma uzytkownika o takim id");
            System.out.println("Podaj id uzytkownika");
            while (!scan.hasNextInt()){
                System.out.println("To nie jest liczba calkowita");
                scan.next();
            }
            id = scan.nextInt();
            user = Users.loadUserById(conn, id);
        }

        user.delete(conn);

        scan.close();
        System.out.println("----------");
    }

    public static void printUser(Connection conn)throws SQLException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Wyswietlanie uzytkownika");
        System.out.println("[id] - wg id");
        System.out.println("[email] - wg emaila");

        boolean repeatChoice;
        do{
            repeatChoice = false;
            String method = scan.next();

            switch (method){
                case "id":
                    printUserById(conn);
                    break;
                case "email":
                    printUserByEmail(conn);
                    break;
                default:
                    repeatChoice = true;
                    System.out.println("Wybiez z ponizszych");
                    break;
            }
        }while (repeatChoice);

        scan.close();
        System.out.println("----------");
    }

    private static void printUserById(Connection conn)throws SQLException{
        System.out.println("Wyswietlanie uzytkownika");
        Scanner scan = new Scanner(System.in);

        System.out.println("Podaj id uzytkownika");
        int id;
        while (!scan.hasNextInt()){
            System.out.println("To nie jest liczba calkowita");
            scan.next();
        }
        id = scan.nextInt();

        Users user = Users.loadUserById(conn, id);
        while (user == null){
            System.out.println("Nie ma uzytkownika o takim id");
            System.out.println("Podaj id uzytkownika");
            while (!scan.hasNextInt()){
                System.out.println("To nie jest liczba calkowita");
                scan.next();
            }
            id = scan.nextInt();
            user = Users.loadUserById(conn, id);
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getUser_group_id());

        scan.close();
    }

    private static void printUserByEmail(Connection conn)throws SQLException{
        System.out.println("Wyswietlanie uzytkownika");
        Scanner scan = new Scanner(System.in);

        System.out.println("Podaj email uzytkownika");
        String email = scan.next();

        Users user = Users.loadUserByEmail(conn, email);
        while (user == null){
            System.out.println("Nie ma uzytkownika o takim emailu");
            System.out.println("Podaj email uzytkownika");
            email = scan.next();
            user = Users.loadUserByEmail(conn, email);
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getUser_group_id());

        scan.close();
    }

    public static void printAllUsers(Connection conn)throws SQLException{
        System.out.println("Wyswietlanie uzytkownikow");

        Users[] users = Users.loadAllUsers(conn);

        if(users == null){
            System.out.println("Brak uzytkownikow");
        }else{
            for(int i = 0; i < users.length; i++){
                System.out.println(users[i].getId());
                System.out.println(users[i].getUsername());
                System.out.println(users[i].getEmail());
                System.out.println(users[i].getPassword());
                System.out.println(users[i].getUser_group_id());
                System.out.println("----------");
            }
        }
    }
}

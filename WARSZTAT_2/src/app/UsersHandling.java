package app;

import classes.Users;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersHandling {
    public static void addUser(Connection conn)throws SQLException{
        System.out.println("Dodawanie uzytkownika");

        System.out.println("Podaj nazwe uzytkownika(username)");
        String username = MainApp.scanner.next();

        System.out.println("Podaj email");
        String email = MainApp.scanner.next();

        System.out.println("Podaj haslo(password)");
        String password = MainApp.scanner.next();

        System.out.println("Podaj do ktorej grupy nalezy(user_group_id)");
        int user_group_id;
        while(!MainApp.scanner.hasNextInt()){
            System.out.println("Podaj liczbe calkowita");
            MainApp.scanner.next();
        }
        user_group_id = MainApp.scanner.nextInt();

        Users user = new Users(username,email,password,user_group_id);

        String adding = user.saveToDB(conn);

        if(adding.equals("group")){
            System.out.println("Nie ma grupy o takim id");
        }

        System.out.println("----------");
    }

    public static void editUser(Connection conn)throws SQLException{
        System.out.println("Edytowanie uzytkownika");

        System.out.println("Podaj id uzytkownika");
        int id;
        while (!MainApp.scanner.hasNextInt()){
            System.out.println("Podaj liczbe calkowita");
            MainApp.scanner.next();
        }
        id = MainApp.scanner.nextInt();

        Users user = Users.loadUserById(conn, id);
        if(user == null){
            System.out.println("Nie ma uzytkownika o takim id");
            System.out.println("----------");
            return;
        }

        System.out.println("Podaj wartosc jaka chcesz edytowac");
        System.out.println("[quit] - nic");
        System.out.println("[username] - nazwa uzytkownika");
        System.out.println("[email] - email uzytkownika");
        System.out.println("[group] - grupa uzytkownika");
        System.out.println("[password] - haslo uzytkownika");

        String toEdit = MainApp.scanner.next();

        switch (toEdit){
            case "quit":
                break;
            case "username":
                System.out.println("Podaj nowa nazwe uzytkownika");
                user.setUserName(MainApp.scanner.next());
                break;
            case "email":
                System.out.println("Podaj nowy email");
                user.setEmail(MainApp.scanner.next());
                break;
            case "group":
                System.out.println("Podaj id grupy");
                while (!MainApp.scanner.hasNextInt()){
                    System.out.println("to nie jest liczba calkowita");
                    MainApp.scanner.next();
                }
                user.setUser_group_id(MainApp.scanner.nextInt());
                break;
            case "password":
//                todo ustawic podwujne potwierdzenie hasla i sprawdzenie czy uzytkownik zna stare haslo
                System.out.println("Podaj nowe haslo");
                user.setPassword(MainApp.scanner.next());
                break;
            default:
                System.out.println("Nie obslugiwane wyrazenie");
                break;
        }

        String adding = user.saveToDB(conn);

        if(adding.equals("group")){
            System.out.println("Nie ma grupy o takim id");
        }
        if(adding.equals("email")){
            System.out.println("Ten email juz istnieje");
        }

        System.out.println("----------");
    }

    public static void deleteUser(Connection conn)throws SQLException{
        System.out.println("Usuwanie uzytkownika");

        System.out.println("Podaj id uzytkownika");
        int id;
        while (!MainApp.scanner.hasNextInt()){
            System.out.println("Podaj liczbe calkowita");
            MainApp.scanner.next();
        }
        id = MainApp.scanner.nextInt();

        Users user = Users.loadUserById(conn, id);
        if(user == null) {
            System.out.println("Nie ma uzytkownika o takim id");
        }else{
            user.delete(conn);
            System.out.println("usunieto uzytkownika");
        }

        System.out.println("----------");
    }

    public static void printUser(Connection conn)throws SQLException{
        System.out.println("Wyswietlanie uzytkownika");
        System.out.println("[id] - wg id");
        System.out.println("[email] - wg emaila");

        String method = MainApp.scanner.next();

        switch (method){
            case "id":
                printUserById(conn);
                break;
            case "email":
                printUserByEmail(conn);
                break;
            default:
                System.out.println("Nie obslugiwane wyrazenie");
                break;
        }

        System.out.println("----------");
    }

    private static void printUserById(Connection conn)throws SQLException{
        System.out.println("Podaj id uzytkownika");
        int id;
        while (!MainApp.scanner.hasNextInt()){
            System.out.println("Podaj liczbe calkowita");
            MainApp.scanner.next();
        }
        id = MainApp.scanner.nextInt();

        Users user = Users.loadUserById(conn, id);
        if(user == null){
            System.out.println("Nie ma uzytkownika o takim id");
            return;
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getUser_group_id());

    }

    private static void printUserByEmail(Connection conn)throws SQLException{
        System.out.println("Podaj email uzytkownika");
        String email = MainApp.scanner.next();

        Users user = Users.loadUserByEmail(conn, email);
        if(user == null){
            System.out.println("Nie ma uzytkownika o takim emailu");
            return;
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getUser_group_id());

    }

    public static void printAllUsers(Connection conn)throws SQLException{
        System.out.println("Wyswietlanie uzytkownikow");

        Users[] users = Users.loadAllUsers(conn);

        if(users == null){
            System.out.println("Brak uzytkownikow");
            System.out.println("----------");
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

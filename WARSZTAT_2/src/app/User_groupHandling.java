package app;

import classes.User_group;

import java.sql.Connection;
import java.sql.SQLException;

public class User_groupHandling {
    public static void addUser_group(Connection conn)throws SQLException {
        System.out.println("Dodawanie grupy");

        System.out.println("Podaj nazwe grupy");
        String name = MainApp.scanner.next();

        User_group user_group = new User_group(name);

        user_group.saveToDB(conn);

        System.out.println("----------");
    }

    public static void editUser_group(Connection conn)throws SQLException{
        System.out.println("Edytowanie grupy uzytkownikow");

        System.out.println("Podaj id grupy");
        int id;
        while (!MainApp.scanner.hasNextInt()){
            System.out.println("Podaj liczbe calkowita");
            MainApp.scanner.next();
        }
        id = MainApp.scanner.nextInt();

        User_group user_group = User_group.loadUser_groupById(conn, id);

        if(user_group == null){
            System.out.println("Nie ma grupy o takim id");
            System.out.println("----------");
            return;
        }

        System.out.println("Podaj nowa nazwe grupy");
        String name = MainApp.scanner.next();
        user_group.setName(name);
        user_group.saveToDB(conn);

        System.out.println("----------");
    }

    public static void deleteUser_group(Connection conn)throws SQLException{
        System.out.println("Usuwanie grupy");

        System.out.println("Podaj id grupy");

        int id;
        while (!MainApp.scanner.hasNextInt()){
            System.out.println("Podaj liczbe calkowita");
            MainApp.scanner.next();
        }
        id = MainApp.scanner.nextInt();

        User_group user_group = User_group.loadUser_groupById(conn, id);
        if(user_group == null) {
            System.out.println("Nie ma grupy o takim id");
        }else{
            user_group.delete(conn);
            System.out.println("Usunieto grupe");
        }

        System.out.println("----------");
    }

    public static void printUser_group(Connection conn)throws SQLException{
        System.out.println("wyswietlanie grupy");

        System.out.println("Podaj id grupy");

        int id;
        while (!MainApp.scanner.hasNextInt()){
            System.out.println("Podaj liczbe calkowita");
            MainApp.scanner.next();
        }
        id = MainApp.scanner.nextInt();

        User_group user_group = User_group.loadUser_groupById(conn, id);
        if(user_group == null){
            System.out.println("Nie ma grupy o takim id");
            System.out.println("----------");
            return;
        }

        System.out.println(user_group.getId());
        System.out.println(user_group.getName());

        System.out.println("----------");
    }

    public static void printAllUser_group(Connection conn)throws SQLException{
        System.out.println("Wyswietlanie grup");

        User_group[] user_groups = User_group.loadAllUser_groups(conn);

        if(user_groups == null){
            System.out.println("Brak uzytkownikow");
            System.out.println("----------");
        }else{
            for(int i = 0; i < user_groups.length; i++){
                System.out.println(user_groups[i].getId());
                System.out.println(user_groups[i].getName());
                System.out.println("----------");
            }
        }
    }
}

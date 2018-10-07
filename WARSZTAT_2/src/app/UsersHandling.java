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
                    System.out.println("Podaj do ktorej grupy nalezy(user_group_id)");
                    while(!scan.hasNextInt()){
                        System.out.println("Podaj wartosc calkowita");
                        System.out.println("Podaj do ktorej grupy nalezy(user_group_id)");
                    }
                    user_group_id = scan.nextInt();
                    user.setUser_group_id(user_group_id);
                    break;
                case "email":
                    email = scan.next();
                    user.setEmail(email);
                    break;
                default:
                    added = true;
                    break;
            }
        }while(added == false);
        scan.close();
    }
    public static void printUserById(Connection conn)throws SQLException{
        
    }
    public static void printAllUser(){

    }
}

package app;

import classes.User_group;

import java.sql.*;
import java.util.Scanner;

public class MainApp {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    try(
        Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/warsztat2?useTimezone=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8",
        "root", "coderslab");
//        Statement stm = conn.createStatement();
//        PreparedStatement pstm = conn.prepareStatement();
    ){
        boolean repeat = true;
        System.out.println("Witam w programie");

        do{
            Print.printClasses();
            String classChoice = scanner.next();

            switch(classChoice){
                case "exercise":
                    break;
                case "solution":
                    break;
                case "user_group":
                    break;
                case "users":
                    Print.ptintUsersOptions();
                    String userAction = scanner.next();
                    switch (userAction){
                        //todo czy dziala w nietypowych sytuacjach
                        case "quit":
                            break;
                        case "add":
                            //dziala
                            UsersHandling.addUser(conn);
                            break;
                        case "edit":
                            //todo osobne metody
                            UsersHandling.editUser(conn);
                            break;
                        case "delete":
                            //dziala
                            UsersHandling.deleteUser(conn);
                            break;
                        case "print":
                            //dziala
                            UsersHandling.printUser(conn);
                            break;
                        case "printAll":
                            //dziala
                            UsersHandling.printAllUsers(conn);
                            break;
                    }

                    break;
                case "quit":
                    repeat = false;
                    break;
                default:
                    System.out.println("Nie obslugiwane wyrazenie");
                    break;
            }

        }while(repeat);
        scanner.close();

        System.out.println("Koniec");

    }catch(SQLException e){
        e.printStackTrace();
    } }
}

package app;

import classes.User_group;

import java.sql.*;
import java.util.Scanner;

public class MainApp {
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
            Scanner scanner = new Scanner(System.in);
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
                        case "quit":
                            break;
                        case "add":
                            UsersHandling.addUser(conn);
                            break;
                        case "edit":
                            UsersHandling.editUser(conn);
                            break;
                        case "delete":
                            UsersHandling.deleteUser(conn);
                            break;
                        case "print":
                            UsersHandling.printUser(conn);
                            break;
                        case "printAll":
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

            scanner.close();
        }while(repeat);

        System.out.println("Koniec");

    }catch(SQLException e){
        e.printStackTrace();
    } }
}

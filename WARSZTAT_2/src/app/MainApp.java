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
                    Print.printSolution();
                    String exerciseAction = scanner.next();
                    switch (exerciseAction){
                        //todo all
                        case "quit":
                            break;
                        case "add":
                            ExerciseHandling.addExercise(conn);
                            break;
                        case "edit":
                            break;
                        case "delete":
                            break;
                        case "print":
                            break;
                        case "printAll":
                            break;
                    }

                    break;
                case "solution":
                    Print.printSolution();
                    String solutionAction = scanner.next();
                    switch (solutionAction){
                        //todo all
                        case "quit":
                            break;
                        case "add":
                            break;
                        case "edit":
                            break;
                        case "delete":
                            break;
                        case "print":
                            break;
                        case "printAll":
                            break;
                    }

                    break;
                case "user_group":
                    Print.printUser_groupOptions();
                    String user_groupAction = scanner.next();
                    switch (user_groupAction){
                        //todo cz dziala w nietypowych sytuacjach
                        case "quit":
                            break;
                        case "add":
                            //dziala
                            User_groupHandling.addUser_group(conn);
                            break;
                        case "edit":
                            //todo dodac metode do zmiany zawartosci
                            User_groupHandling.editUser_group(conn);
                            break;
                        case "delete":
                            //dziala
                            User_groupHandling.deleteUser_group(conn);
                            break;
                        case "print":
                            //dziala
                            User_groupHandling.printUser_group(conn);
                            break;
                        case "printAll":
                            //dziala
                            User_groupHandling.printAllUser_group(conn);
                            break;
                    }

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

package app;

import classes.Solution;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        System.out.println(dateFormat.format(calendar.getTime()));

        java.util.Date date = new Date(Calendar.getInstance().getTimeInMillis());
        System.out.println(dateFormat.format(date.getTime()));

        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        cal.add(Calendar.YEAR, 1);
        System.out.println(dateFormat.format(cal.getTime()));
        cal.setTime(date);
        System.out.println(dateFormat.format(cal.getTime()));

        do{
            Print.printClasses();
            String classChoice = scanner.next();

            switch(classChoice){
                case "quit":
                    repeat = false;
                    break;
                case "exercise":
                    Print.printExerciseOptions();
                    String exerciseAction = scanner.next();
                    switch (exerciseAction){
                        //todo czy dziala w szegulnych przypadkach
                        case "quit":
                            break;
                        case "add":
                            //dziala
                            ExerciseHandling.addExercise(conn);
                            break;
                        case "edit":
                            //todo zrobic metody do edycji atrybutow
                            ExerciseHandling.editExercise(conn);
                            break;
                        case "delete":
                            //dziala
                            ExerciseHandling.deleteExercise(conn);
                            break;
                        case "print":
                            //dziala
                            ExerciseHandling.printExercise(conn);
                            break;
                        case "printAll":
                            //dziala
                            ExerciseHandling.printAllExercise(conn);
                            break;
                    }

                    break;
                case "solution":
                    Print.printSolutionOptions();
                    String solutionAction = scanner.next();
                    switch (solutionAction){
                        //todo all
                        case "quit":
                            break;
                        case "add":
                            SolutionHandling.addSolution(conn);
                            break;
                        case "edit":
                            //todo all
                            break;
                        case "delete":
                            SolutionHandling.deleteSolution(conn);
                            break;
                        case "print":
                            SolutionHandling.printSolution(conn);
                            break;
                        case "printAll":
                            SolutionHandling.printAllSolution(conn);
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

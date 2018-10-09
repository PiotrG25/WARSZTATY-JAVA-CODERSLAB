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
                        case "quit":
                            break;
                        case "add":
                            ExerciseHandling.addExercise(conn);
                            break;
                        case "edit":
                            ExerciseHandling.editExercise(conn);
                            break;
                        case "delete":
                            ExerciseHandling.deleteExercise(conn);
                            break;
                        case "print":
                            ExerciseHandling.printExercise(conn);
                            break;
                        case "printAll":
                            ExerciseHandling.printAllExercise(conn);
                            break;
                    }

                    break;
                case "solution":
                    Print.printSolutionOptions();
                    String solutionAction = scanner.next();
                    switch (solutionAction){
                        case "quit":
                            break;
                        case "add":
                            SolutionHandling.addSolution(conn);
                            break;
                        case "edit":
                            SolutionHandling.editSolution(conn);
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
                        case "quit":
                            break;
                        case "add":
                            User_groupHandling.addUser_group(conn);
                            break;
                        case "edit":
                            User_groupHandling.editUser_group(conn);
                            break;
                        case "delete":
                            User_groupHandling.deleteUser_group(conn);
                            break;
                        case "print":
                            User_groupHandling.printUser_group(conn);
                            break;
                        case "printAll":
                            User_groupHandling.printAllUser_group(conn);
                            break;
                    }

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

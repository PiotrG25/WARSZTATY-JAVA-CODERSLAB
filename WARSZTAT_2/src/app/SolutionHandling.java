package app;

import classes.Solution;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

public class SolutionHandling {
    public static void addSolution(Connection conn)throws SQLException {
        System.out.println("Dodawanie rozwiazania");

        System.out.println("Podaj rozwiazanie");
        System.out.println("Zakoncz XYZ");

        StringBuilder description = new StringBuilder();
        while(MainApp.scanner.hasNext()){
            String word = MainApp.scanner.next();
            if(word.equals("XYZ")){
                break;
            }else{
                description.append(word + " ");
            }
        }

        System.out.println("Podaj id zadania");
        int exercise_id;
        while(!MainApp.scanner.hasNextInt()){
            System.out.println("Podaj liczbe calkowita");
            MainApp.scanner.next();
        }
        exercise_id = MainApp.scanner.nextInt();

        System.out.println("Podaj id uzytkownika");
        int users_id;
        while(!MainApp.scanner.hasNextInt()){
            System.out.println("Podaj liczbe calkowita");
            MainApp.scanner.next();
        }
        users_id = MainApp.scanner.nextInt();

        Solution solution = new Solution(Calendar.getInstance(), Calendar.getInstance(), description.toString(), exercise_id, users_id);

        String adding = solution.saveToDB(conn);

        while(adding.equals("exercise")){
            System.out.println("Nie ma zadania o takim id");
            while(!MainApp.scanner.hasNextInt()){
                System.out.println("Podaj liczbe calkowita");
                MainApp.scanner.next();
            }
            exercise_id = MainApp.scanner.nextInt();
            solution.setExercise_id(exercise_id);
            adding = solution.saveToDB(conn);
        }

        while(adding.equals("users")){
            System.out.println("Nie ma uzytkownika o takim id");
            while(!MainApp.scanner.hasNextInt()){
                System.out.println("Podaj liczbe calkowita");
                MainApp.scanner.next();
            }
            users_id = MainApp.scanner.nextInt();
            solution.setUsers_is(users_id);
            adding = solution.saveToDB(conn);
        }

        System.out.println("----------");
    }
}

package app;

import classes.Exercise;

import java.sql.Connection;
import java.sql.SQLException;

public class ExerciseHandling {
    public static void addExercise(Connection conn)throws SQLException{
        System.out.println("dodawanie zadania");

        System.out.println("Podaj tytul zadania");
        String title = MainApp.scanner.next();

        System.out.println("Podaj tresc zadania");
        String description = MainApp.scanner.nextLine();

        Exercise exercise = new Exercise(title, description);

        boolean added = false;
        do{
            String adding = exercise.saveToDB(conn);

            switch (adding){
                case "title":
                    System.out.println("Brak tytulu");
                    System.out.println("podaj tytul");
                    exercise.setTitle(MainApp.scanner.next());
                    break;
                case "description":
                    System.out.println("Brakuje opisu");
                    System.out.println("Podaj opis");
                    exercise.setDescription(MainApp.scanner.nextLine());
                    break;
                default:
                    added = true;
                    break;
            }
        }while(added == false);

        System.out.println("----------");
    }

    public static void editExercise(Connection conn)throws SQLException{
        
    }

    public static void deleteExercise(Connection conn)throws SQLException{

    }

    public static void printExercise(Connection conn)throws SQLException{

    }

    public static void printAllExercise(Connection conn)throws SQLException{

    }
}

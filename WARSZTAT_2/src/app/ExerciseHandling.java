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
        System.out.println("Zakoncz podajac XYZ");
        StringBuilder description = new StringBuilder();

        while(MainApp.scanner.hasNext()){
            String word = MainApp.scanner.next();
            if(word.equals("XYZ")){
                break;
            }else{
                description.append(word + " ");
            }
        }

        Exercise exercise = new Exercise(title, description.toString());

        exercise.saveToDB(conn);

        System.out.println("----------");
    }

    public static void editExercise(Connection conn)throws SQLException{
        System.out.println("Edytowanie zadania");

        System.out.println("Podaj id zadania");
        int id;
        while (!MainApp.scanner.hasNextInt()){
            System.out.println("To nie jest liczba calkowita");
            MainApp.scanner.next();
        }
        id = MainApp.scanner.nextInt();

        Exercise exercise = Exercise.loadExerciseById(conn, id);
        while (exercise == null){
            System.out.println("Nie ma zadania o takim id");
            while (!MainApp.scanner.hasNextInt()){
                System.out.println("To nie jest liczba calkowita");
                MainApp.scanner.next();
            }
            id = MainApp.scanner.nextInt();
            exercise = Exercise.loadExerciseById(conn, id);
        }

        boolean repeatChoice = true;

        do{
            System.out.println("Podaj ktora wartosc chcesz edytowac");
            System.out.println("[quit] - nic");
            System.out.println("[title] - tytul zadania");
            System.out.println("[description] - opis zadania");

            String toEdit = MainApp.scanner.next();

            switch (toEdit){
                case "quit":
                    repeatChoice = false;
                    break;
                case "title":
                    System.out.println("Podaj nowy tytul");
                    exercise.setTitle(MainApp.scanner.next());
                    break;
                case "description":
                    System.out.println("Podaj nowy opis");
                    System.out.println("Zakoncz podajac XYZ");
                    StringBuilder description = new StringBuilder();

                    while(MainApp.scanner.hasNext()){
                        String word = MainApp.scanner.next();
                        if(word.equals("XYZ")){
                            break;
                        }else{
                            description.append(word + " ");
                        }
                    }

                    exercise.setDescription(description.toString());
                    break;
                default:
                    System.out.println("Nie obslugiwane wyrazenie");
                    break;
            }
        }while(repeatChoice);

        exercise.saveToDB(conn);

        System.out.println("----------");
    }

    public static void deleteExercise(Connection conn)throws SQLException{
        System.out.println("Usuwanie zadania");

        System.out.println("Podaj id zadania");
        int id;
        while (!MainApp.scanner.hasNextInt()){
            System.out.println("To nie jest liczba calkowita");
            MainApp.scanner.next();
        }
        id = MainApp.scanner.nextInt();

        Exercise exercise = Exercise.loadExerciseById(conn, id);
        if(exercise == null) {
            System.out.println("Nie ma zadania o takim id");
        }else{
            exercise.delete(conn);
            System.out.println("Usunieto zadanie");
        }

        System.out.println("----------");
    }

    public static void printExercise(Connection conn)throws SQLException{
        System.out.println("wyswietlanie zadania");

        System.out.println("Podaj id zadania");

        int id;
        while (!MainApp.scanner.hasNextInt()){
            System.out.println("To nie jest liczba calkowita");
            MainApp.scanner.next();
        }
        id = MainApp.scanner.nextInt();

        Exercise exercise = Exercise.loadExerciseById(conn, id);
        while (exercise == null){
            System.out.println("Nie ma zadania o takim id");
            while (!MainApp.scanner.hasNextInt()){
                System.out.println("To nie jest liczba calkowita");
                MainApp.scanner.next();
            }
            id = MainApp.scanner.nextInt();
            exercise = Exercise.loadExerciseById(conn, id);
        }

        System.out.println(exercise.getId());
        System.out.println(exercise.getTitle());
        System.out.println(exercise.getDescription());

        System.out.println("----------");
    }

    public static void printAllExercise(Connection conn)throws SQLException{
        System.out.println("wyswietlanie zadan");

        Exercise[] exercises = Exercise.loadAllExercises(conn);

        if(exercises == null){
            System.out.println("Brak zadan");
        }else{
            for(Exercise e : exercises){
                System.out.println(e.getId());
                System.out.println(e.getTitle());
                System.out.println(e.getDescription());
                System.out.println("----------");
            }
        }
    }
}

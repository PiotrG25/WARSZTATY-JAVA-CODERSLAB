package app;

import classes.Solution;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SolutionHandling {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
            solution.setCreated(Calendar.getInstance());
            solution.setUpdated(Calendar.getInstance());
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
            solution.setCreated(Calendar.getInstance());
            solution.setUpdated(Calendar.getInstance());
            adding = solution.saveToDB(conn);
        }

        System.out.println("----------");
    }

    public static void editSolution(Connection conn)throws SQLException{
        //todo
        //todo
        //todo
    }

    public static void deleteSolution(Connection conn)throws SQLException{
        System.out.println("Usuwanie rozwiazania");

        System.out.println("Podaj id zadania");
        int id;
        while(!MainApp.scanner.hasNextInt()){
            System.out.println("Podaj liczbe calkowita");
            MainApp.scanner.next();
        }
        id = MainApp.scanner.nextInt();

        Solution solution = Solution.loadSolutionById(conn, id);
        if(solution == null){
            System.out.println("Nie ma rozwiazania o takim id");
        }else{
            solution.delete(conn);
            System.out.println("Usunieto rozwiazanie");
        }

        System.out.println("----------");
    }

    public static void printSolution(Connection conn)throws SQLException{
        System.out.println("Wyswietlanie rozwiazania");

        System.out.println("Podaj id rozwiazania");
        int id;
        while(!MainApp.scanner.hasNextInt()){
            System.out.println("Podaj liczbe calkowita");
            MainApp.scanner.next();
        }
        id = MainApp.scanner.nextInt();

        Solution solution = Solution.loadSolutionById(conn, id);

        if(solution == null){
            System.out.println("Brak rozwiazania o takim id");
        }else{
            System.out.println(solution.getId());
            System.out.println(dateFormat.format(solution.getCreated().getTime()));
            System.out.println(dateFormat.format(solution.getUpdated().getTime()));
            System.out.println(solution.getDescription());
            System.out.println(solution.getExercise_id());
            System.out.println(solution.getUsers_id());
        }

        System.out.println("----------");
    }

    public static void printAllSolution(Connection conn)throws SQLException{
        System.out.println("Wyswietlanie rozwiazan");

        Solution[] solutions = Solution.loadAllSolutions(conn);

        if(solutions == null){
            System.out.println("Brak rozwiazan");
        }else{
            for(Solution s : solutions){
                System.out.println(s.getId());
                System.out.println(dateFormat.format(s.getCreated().getTime()));
                System.out.println(dateFormat.format(s.getUpdated().getTime()));
                System.out.println(s.getDescription());
                System.out.println(s.getExercise_id());
                System.out.println(s.getUsers_id());
                System.out.println("----------");
            }
        }
    }
}

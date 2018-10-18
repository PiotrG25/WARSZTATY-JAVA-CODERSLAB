package app;

import java.sql.*;
import java.util.Scanner;

public class MainApp {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    try(
        Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/warsztat2?useTimezone=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true",
        "root", "coderslab");
//        Statement stm = conn.createStatement();
//        PreparedStatement pstm = conn.prepareStatement();
    ){
        boolean repeat = true;
        System.out.println("Witam w programie");

        do{
            Print.printClasses();
            String classChoice = scanner.next();

            //todo tytul moze zawierac wiecej niz jedno slowo :( poprawić wszędzie
            //todo solution wyswietla 02:00:00, poprawić wszystkie w solution
            //todo zapomnialem zrobic setUpdated przy edycji solution
            //todo posortowac po id (nie sortownosc szczegulnie widoczna przy duzej ilosci recordow)

            //from mentor
            //todo zmienić nazwę pakietu classes na nazwę, która sugeryje do czego one służą
            //todo zmienić nazwę klasy Users na klasę User, klasa ma sugerować co reprezentuje a nie do jakiej tabeli się podłączyła
            //todo zmienić tablicę na listę w metodach loadAll... ale dalej zwracać tablicę
            //todo rozmiar metody powinien być między 20-30 linii, szczegulnie w saveToDB, zwiększyć czytelność kodu
            //todo zapoznać się z SOLID
            //todo nie zwracać String-ów przy błędzie saveToDB
            //todo nie rozbijać update-a na osobne, wszystkie klasy
            //todo Przenieść BCrypt poza pakiet classes i zrobić żeby działało, może maven
            //todo nie ma konwencji nazywania klas po tabelach z kturymi się łączą
            //todo podzielić program na podprogramy obsługujące każdą klasę albo trzymałą w module
            //todo statyczny scanner to fatalny pomysł, ewentualnie udostępniać przez metody statyczne
            //todo Print powinna być klasą prywatną klasy która ją wywołuje

            //from konkurencja
            //todo zrobić osobną klasę zajmującą się walidacją elementów
            //todo dodać regex na email i hasło

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

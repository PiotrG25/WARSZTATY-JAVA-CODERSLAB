package app;

import java.sql.*;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        try(
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/warsztat2?useTimezone=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8",
            "root", "coderslab");
//            Statement stm = conn.createStatement();
//            PreparedStatement pstm = conn.prepareStatement();
        ){
            Scanner scanner = new Scanner(System.in);
            boolean repeat = true;
            System.out.println("Witam w programie");

            do{

                soutClasses();
                String classChoice = scanner.next();
                if(classChoice.equals("quit")){
                    repeat = false;
                    continue;
                }
                switch(classChoice){
                    case "exercise":
                        break;
                    case "solution":
                        break;
                    case "user_group":
                        break;
                    case "users":
                        break;
                    default:
                        System.out.println("Nie znane wyrazenie");
                        break;
                }
            }while(repeat);
            scanner.close();

            System.out.println("Koniec");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    static void soutClasses(){
        System.out.println(
                "Wybierz klase na ktorej chcesz operowac\n" +
                "[exercise][solution][user_group][users]\n" +
                "[quit - zakonczenie programu]");
    }
    static void soutOptions(String add, String edit, String delete){
        System.out.printf(
                "Wpisz jaka akcje chcesz wykonac\n" +
                "[add - %s]\n" +
                "[edit - %s]\n" +
                "[delete - %s]\n" +
                "[quit - zakonczenie programu]\n"
                , add, edit, delete);
    }
    static void  options(String add, String view){
        System.out.printf(
                "[add - %s]\n" +
                "[view - %s]\n" +
                "[quit - zakonczenie programu]\n"
                , add, view);
    }
}

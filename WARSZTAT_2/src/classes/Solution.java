package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    private int id;
    private String created;
    private String updated;
    private String description;
    private int exercise_id;
    private int users_id;

    public Solution(String created, String updated, String description, int exercise_id, int users_id){
        setCreated(created);
        setUpdated(updated);
        this.description = description;
        setExercise_id(exercise_id);
        setUsers_is(users_id);
    }

    public String saveToDB(Connection conn) throws SQLException {
        if(created == null){
            System.err.println("Brak daty stworzenia");
            return "created";
        }
        if(updated == null){
            System.err.println("Brak daty aktualizacji");
            return "updated";
        }
        if(description == null){
            System.err.println("Brak opisu");
            return "description";
        }
        if(exercise_id == 0){
            System.err.println("Brak id zadania");
            return "exercise_id";
        }
        if(users_id == 0){
            System.err.println("Brak id uzytkownika");
            return "users_id";
        }
        Statement stm = conn.createStatement();

        //check if exercise_id existance
        String check = "SELECT id FROM exercise;";
        ResultSet rs = stm.executeQuery(check);

        boolean exeists = false;
        while(rs.next()){
            if(rs.getInt("id") == exercise_id){
                exeists = true;
                break;
            }}
        rs.close();

        if(exeists == false){
            System.err.println("Nie ma zadania o takim id");
            return "group";
        }

        //check if users_id existance
        check = "SELECT id FROM users;";
        rs = stm.executeQuery(check);

        exeists = false;
        while(rs.next()){
            if(rs.getInt("id") == users_id){
                exeists = true;
                break;
            }}
        rs.close();

        if(exeists == false){
            System.err.println("Nie ma uzytkownika o takim id");
            return "group";
        }

//        todo all of this
//        if(id == 0){
//            String insert = "INSERT INTO users (username, email, password, user_group_id) VALUES (?, ?, ?, ?);";
//            PreparedStatement pstm = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
//
//            pstm.setString(1, username);
//            pstm.setString(2, email);
//            pstm.setString(3, password);
//            pstm.setInt(4, user_group_id);
//
//            pstm.executeUpdate();
//
//            rs = pstm.getGeneratedKeys();
//
//            this.id = rs.getInt(1);
//
//            rs.close();
//        }else{
////            todo podzielic edycje na osobne metody do kazdej zmiennej
//            String update = "UPDATE users SET username = ?, email = ?, password = ?, user_group_id = ?;";
//            PreparedStatement pstm2 = conn.prepareStatement(update);
//
//            pstm2.setString(1, username);
//            pstm2.setString(2, email);
//            pstm2.setString(3, password);
//            pstm2.setInt(4, user_group_id);
//
//            pstm2.executeUpdate();
//        }
        return "0";
    }
    public static Solution loadSolutionById(Connection conn, int id) throws SQLException {
//        todo: test: czy metoda nie zwrocila nulla
//        todo: czy obiekt ma szystkie dane takie same jak w recordzie
//        todo:
        return null;
    }
    public static Solution[] loadAllSolutions(Connection conn) throws SQLException {
//        todo: test: czy ne zwraca nulla
//        todo: czy dlugosc tablicy jest taka sama jak ilosc rekordow w tablicy
//        todo: czy argumenty sie zgadzaja(sprawdzic przynajmniej jeden)
//        todo:
        return null;
    }
    public void delete(Connection conn) throws SQLException {
//        todo: usunac obiekt ktory jest w bazie danych(id != 0)
//        todo: jezeli go tam nie ma nic nie robid
//        todo: gdy usuniemy obiekt zmieniamy jego id na 0
//        todo:
    }
    public static Solution loadAllByUserId(Connection conn, int id) throws SQLException {
//        todo: pobranie wszystkich rozwiazan uzytkownika
//        todo:
        return null;
    }
    public static Solution[] loadAllByExerciseId(Connection conn, int id) throws SQLException {
//        todo: pobranie wszystkich rozwiazan danego zadania
//        todo:
        return null;
    }

    public Solution setCreated(String created){
        if(isDateTime(created)){
            this.created = created;
        }else{
            System.err.println("Niewłaściwy format czasu, możesz skożystać z metody toDateTime");
            return null;
        }
        return this;
    }
    public Solution setUpdated(String updated){
        if(isDateTime(updated)){
            this.updated = updated;
        }else{
            System.err.println("Niewłaściwy format czasu, możesz skożystać z metody toDateTime");
            return null;
        }
        return this;
    }
    public Solution setDescription(String description){
        this.description = description;
        return this;
    }
    public Solution setExercise_id(int exercise_id){
//        todo: czy istnieje takie id
//        todo: takie zadanie
//        todo:
        this.exercise_id = exercise_id;
        return this;
    }
    public Solution setUsers_is(int users_id){
//        todo: czy istnieje takie id
//        todo: taki uzytkownik
//        todo:
        this.users_id = users_id;
        return this;
    }

    public int getId(){
        return id;
    }
    public String getCreated(){
        return created;
    }
    public String getUpdated(){
        return updated;
    }
    public String getDescription(){
        return description;
    }
    public int getExercise_id(){
        return exercise_id;
    }
    public int getUsers_id(){
        return users_id;
    }

    public static boolean isDateTime(String date) {
//        'YYYY-MM-DD hh:mm:ss'
//        Metoda sprawdza poprawnosc formatu a nie daty
//        todo: czy dziala?

        Pattern pattern = Pattern.compile(
                "[0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}\\:[0-9]{2}:[0-9]{2}");

        Matcher matcher = pattern.matcher(date);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static String toDateTime(int YY, int MM, int DD, int hh, int mm, int ss){
//        todo: czy dziala?
        String date = "";

        if(YY < 1000) date += "0";
        if(YY < 100) date += "0";
        if(YY < 10) date += "0";

        date += YY;
        date += "-";

        if(MM < 10) date += "0";

        date += MM;
        date += "-";

        if(DD < 10) date += "0";

        date += DD;
        date += " ";

        if(hh < 10) date += "0";

        date += hh;
        date += ":";

        if(mm < 10) date += "0";

        date += mm;
        date += ":";

        if(ss < 10) date += "0";

        date += ss;

        return date;
    }
}
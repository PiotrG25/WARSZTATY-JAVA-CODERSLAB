package classes;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    private int id;
    private Calendar created;
    private Calendar updated;
    private String description;
    private int exercise_id;
    private int users_id;
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Solution(Calendar created, Calendar updated, String description, int exercise_id, int users_id) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }

    public String saveToDB(Connection conn) throws SQLException {
        if (created == null) {
            System.err.println("Brak daty stworzenia");
            return "created";
        }
        if (updated == null) {
            System.err.println("Brak daty aktualizacji");
            return "updated";
        }
        if (description == null) {
            System.err.println("Brak opisu");
            return "description";
        }
        if (exercise_id == 0) {
            System.err.println("Brak id zadania");
            return "exercise_id";
        }
        if (users_id == 0) {
            System.err.println("Brak id uzytkownika");
            return "users_id";
        }
        Statement stm = conn.createStatement();

        //check if exercise_id existance
        String check = "SELECT id FROM exercise;";
        ResultSet rs = stm.executeQuery(check);

        boolean exeists = false;
        while (rs.next()) {
            if (rs.getInt("id") == exercise_id) {
                exeists = true;
                break;
            }
        }
        rs.close();

        if (exeists == false) {
            System.err.println("Nie ma zadania o takim id");
            return "exercise";
        }

        //check if users_id existance
        check = "SELECT id FROM users;";
        rs = stm.executeQuery(check);

        exeists = false;
        while (rs.next()) {
            if (rs.getInt("id") == users_id) {
                exeists = true;
                break;
            }
        }
        rs.close();

        if (exeists == false) {
            System.err.println("Nie ma uzytkownika o takim id");
            return "users";
        }
        //Koniec walidacji

        if (id == 0) {
            String insert = "INSERT INTO solution (created, updated, description, exercise_id, users_id) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement pstm = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            pstm.setDate(1, (java.sql.Date) (created.getTime()));
            pstm.setDate(2, (Date) (updated.getTime()));
            pstm.setString(3, description);
            pstm.setInt(4, exercise_id);
            pstm.setInt(5, users_id);

            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();

            this.id = rs.getInt(1);

            rs.close();
        } else {
//            todo podzielic edycje na osobne metody do kazdej zmiennej
            ;
        }
        return "0";
    }

    public static Solution loadSolutionById(Connection conn, int id) throws SQLException {
        String select = "SELECT * FROM solutions WHERE id=?;";
        PreparedStatement pstm = conn.prepareStatement(select);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            Calendar created = Calendar.getInstance();
            created.setTime(rs.getDate("created"));
            Calendar updated = Calendar.getInstance();
            updated.setTime(rs.getDate("updated"));
            String description = rs.getString("description");
            int exercise_id = rs.getInt("exercise_id");
            int users_id = rs.getInt("users_id");

            Solution solution = new Solution(created, updated, description, exercise_id, users_id);

            rs.close();
            return solution;
        }
        rs.close();
        System.err.println("Brak rozwiazania o takim id");
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

    public Solution setCreated(Calendar created) {
        this.created = created;
        return this;
    }

    public Solution setUpdated(Calendar updated) {
        this.updated = updated;
        return this;
    }

    public Solution setDescription(String description) {
        this.description = description;
        return this;
    }

    public Solution setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
        return this;
    }

    public Solution setUsers_is(int users_id) {
        this.users_id = users_id;
        return this;
    }

    public int getId() {
        return id;
    }

    public Calendar getCreated() {
        return created;
    }

    public Calendar getUpdated() {
        return updated;
    }

    public String getDescription() {
        return description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public int getUsers_id() {
        return users_id;
    }
}
//    public static boolean isDateTime(String date) {
////        'YYYY-MM-DD hh:mm:ss'
////        Metoda sprawdza poprawnosc formatu a nie daty
////        todo: czy dziala?
//
//        Pattern pattern = Pattern.compile(
//                "[0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}\\:[0-9]{2}:[0-9]{2}");
//
//        Matcher matcher = pattern.matcher(date);
//
//        if (matcher.matches()) {
//            return true;
//        }
//        return false;
//    }
//
//    public static String toDateTime(int YY, int MM, int DD, int hh, int mm, int ss){
////        todo: czy dziala?
//        String date = "";
//
//        if(YY < 1000) date += "0";
//        if(YY < 100) date += "0";
//        if(YY < 10) date += "0";
//
//        date += YY;
//        date += "-";
//
//        if(MM < 10) date += "0";
//
//        date += MM;
//        date += "-";
//
//        if(DD < 10) date += "0";
//
//        date += DD;
//        date += " ";
//
//        if(hh < 10) date += "0";
//
//        date += hh;
//        date += ":";
//
//        if(mm < 10) date += "0";
//
//        date += mm;
//        date += ":";
//
//        if(ss < 10) date += "0";
//
//        date += ss;
//
//        return date;
//    }

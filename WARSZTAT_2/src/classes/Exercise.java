package classes;

import java.sql.*;
import java.util.Arrays;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class Exercise {
    private int id;
    private String title;
    private String description;

    public Exercise(String title, String description){
        this.title = title;
        this.description = description;
    }

    public String saveToDB(Connection conn) throws SQLException {

        if(title == null){
            System.err.println("brakuje tytulu");
            return "title";
        }
        if(description == null){
            System.err.println("brakuje opisu");
            return "description";
        }

        if(id == 0){
            String insert = "INSERT INTO exercise (title, description) VALUES (?, ?);";
            PreparedStatement pstm = conn.prepareStatement(insert, RETURN_GENERATED_KEYS);

            pstm.setString(1, title);
            pstm.setString(2, description);

            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            rs.close();
        }else{
            //todo metoda zmiany rekordu w tabeli
            ;
        }
        return "0";
    }
    public static Exercise loadExerciseById(Connection conn, int id) throws SQLException {
        String select = "SELECT * FROM exercise WHERE id=?;";
        PreparedStatement pstm = conn.prepareStatement(select);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();

        if(rs.next()){
            String title = rs.getString("title");
            String description = rs.getString("description");

            Exercise exercise = new Exercise(title, description);
            exercise.id = id;
            rs.close();
            return exercise;
        }
        rs.close();
        System.out.println("Nie ma zadania o takim id");
        return null;
    }
    public static Exercise[] loadAllExercises(Connection conn) throws SQLException {
        String selectIds = "SELECT id FROM exercise;";
        ResultSet rs = (conn.createStatement()).executeQuery(selectIds);
        Exercise[] exercises = new Exercise[1];

        while(rs.next()){
            exercises[exercises.length - 1] = loadExerciseById(conn, rs.getInt("id"));
            exercises = Arrays.copyOf(exercises, exercises.length + 1);
        }
        rs.close();
        exercises = Arrays.copyOf(exercises, exercises.length - 1);

        if(exercises.length == 0){
            System.out.println("Brak zadan");
            return null;
        }else{
            return exercises;
        }
    }

    public void delete(Connection conn) throws SQLException {
        if(id != 0){
            String delete = "DELETE FROM exercise WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(delete);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            id = 0;
        }
//        todo: usunac obiekt ktory jest w bazie danych(id != 0)
//        todo: jezeli go tam nie ma nic nie robid
//        todo: gdy usuniemy obiekt zmieniamy jego id na 0
//        todo:
    }

    public Exercise setTitle(String title){
        this.title = title;
        return this;
    }
    public Exercise setDescription(String description){
        this.description = description;
        return this;
    }

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
}

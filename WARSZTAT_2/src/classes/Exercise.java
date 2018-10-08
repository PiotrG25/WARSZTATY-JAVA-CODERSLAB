package classes;

import java.sql.*;

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
            PreparedStatement pstm = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

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
//        todo: test: czy metoda nie zwrocila nulla
//        todo: czy obiekt ma szystkie dane takie same jak w recordzie
//        todo:
        return null;
    }
    public static Exercise[] loadAllExercises(Connection conn) throws SQLException {
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

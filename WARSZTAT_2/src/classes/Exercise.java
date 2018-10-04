package classes;

import java.sql.Connection;
import java.sql.SQLException;

public class Exercise {
    private int id;
    private String title;
    private String description;

    public Exercise(String title, String description){
        this.title = title;
        this.description = description;
    }

    public void saveToDB(Connection conn) throws SQLException {
        //todo: czy id == 0
        //todo: czy gdy id != 0 chcesz zmienic zawartosc
        //todo: czy wszystkie atrybuty sa wypelnione
        //todo: czy nie ma obiektu juz w taleli
        //todo: ewentualnie zmienic istniejacy obiekt
        //todo: czy prepare statement zwraca jakies wyjatki
        //todo: pobrac id z bazy i przypisac do obiektu
        //todo:
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

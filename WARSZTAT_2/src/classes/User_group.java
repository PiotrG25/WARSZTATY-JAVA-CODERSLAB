package classes;

import java.sql.Connection;
import java.sql.SQLException;

public class User_group {
    private int id;
    private String name;

    public User_group(String name){
        this.name = name;
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
    public static User_group loadUser_groupById(Connection conn, int id) throws SQLException {
//        todo: test: czy metoda nie zwrocila nulla
//        todo: czy obiekt ma szystkie dane takie same jak w recordzie
//        todo:
        return null;
    }
    public static User_group[] loadAllUser_groups(Connection conn) throws SQLException {
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

    public User_group setName(String name){
        this.name = name;
        return this;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}

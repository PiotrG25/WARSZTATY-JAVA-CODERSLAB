package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

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
        String selectById = "SELECT * FROM user_group WHERE id = ?";
        PreparedStatement pstm = conn.prepareStatement(selectById);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();

        if(rs.next()){
            String name = rs.getString("name");

            User_group user_group = new User_group(name);
            user_group.id = id;

            rs.close();
            return user_group;
        }
        rs.close();
        System.err.println("Brak grupy o takim id");
        return null;
    }

    public static User_group[] loadAllUser_groups(Connection conn) throws SQLException {
        String selectIds = "SELECT id FROM user_group";
        ResultSet rs = (conn.createStatement()).executeQuery(selectIds);
        User_group[] ug = new User_group[1];

        while(rs.next()){
            ug[ug.length - 1] = loadUser_groupById(conn, rs.getInt("id"));
            ug = Arrays.copyOf(ug, ug.length + 1);
        }
        rs.close();
        ug = Arrays.copyOf(ug, ug.length - 1);

        if(ug.length == 0){
            System.err.println("Brak grup");
            return null;
        }else{
            return ug;
        }
//        todo: test: czy ne zwraca nulla
//        todo: czy dlugosc tablicy jest taka sama jak ilosc rekordow w tablicy
//        todo: czy argumenty sie zgadzaja(sprawdzic przynajmniej jeden)
//        todo:
    }

    public void delete(Connection conn) throws SQLException {
        if(this.id != 0){
            String delete = "DELETE FROM user_group WHERE id=?";
            PreparedStatement pstm = conn.prepareStatement(delete);
            pstm.setInt(1, this.id);
            pstm.executeUpdate();
            this.id = 0;
        }
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

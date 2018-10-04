package classes;

import java.sql.Connection;
import java.sql.SQLException;
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
    public static Solution[] loadAllByExercise(Connection conn, int id) throws SQLException {
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
    public static boolean isDateTime(String date){
//        'YYYY-MM-DD hh:mm:ss'
//        Metoda sprawdza poprawnosc formatu a nie daty
//        todo: czy dziala?

        Pattern pattern = Pattern.compile(
        "[0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}\\:[0-9]{2}:[0-9]{2}");

        Matcher matcher = pattern.matcher(date);

        if(matcher.matches()){
            return true;
        }
        return false;
/*        boolean yes = true;

        int[] digitIndex = {0,1,2,3,5,6,8,9,11,12,14,15,17,18};
        int[] minusIndex = {4,7};
        int[] pointsIndex = {13,16};
        int spaceIndex = 10;

        if(date.length() > 19) yes = false;

        for(int i : digitIndex){
            if(!Character.isDigit(date.charAt(i)))
                yes = false;
        }

        for(int i : minusIndex){
            if(date.charAt(i) != '-') yes = false;
        }

        for(int i : pointsIndex){
            if(date.charAt(i) != ':') yes = false;
        }

        if(date.charAt(spaceIndex) != ' ') yes = false;

        return yes;*/
    }
}
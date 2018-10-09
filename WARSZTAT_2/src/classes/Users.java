package classes;

import java.sql.*;
import java.util.Arrays;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class Users {
    private int id;
    private String username;
    private String email;
    private String password;
    private int user_group_id;

    public Users(String username, String email, String password, int user_group_id){
        this.id = 0;
        this.username = username;
        this.email = email;
        this.setPassword(password);
        this.user_group_id = user_group_id;
    }

    public Users(){}

    public String saveToDB(Connection conn) throws SQLException {
        if(!this.isAllSet()){
            System.err.println("Brakuje kilku argumentów");
            return "args";
        }
        Statement stm = conn.createStatement();

        //check user_group_id existance
        String check = "SELECT id FROM user_group;";
        ResultSet rs = stm.executeQuery(check);
        boolean existsGroupId = false;
        while(rs.next()){
            if(rs.getInt("id") == user_group_id){
                existsGroupId = true;
                break;
        }}
        rs.close();
        if(existsGroupId == false){
            System.err.println("Nie ma grupy o takim id");
            return "group";
        }

        //check email Uniquness
        check = "SELECT email, id FROM users;";
        rs = stm.executeQuery(check);
        boolean isUnique = true;
        while(rs.next()){
            if(rs.getString("email").equals(email)){
                isUnique = false;
                if(rs.getInt("id") == id){
                    isUnique = true;
                }
                break;
            }
        }
        rs.close();
        if(isUnique == false){
            System.err.println("Taki email już istnieje");
            return "email";
        }
        //Koniec walidacji

        if(id == 0){
            String insert = "INSERT INTO users (username, email, password, user_group_id) VALUES (?, ?, ?, ?);";
            PreparedStatement pstm = conn.prepareStatement(insert, RETURN_GENERATED_KEYS);

            pstm.setString(1, username);
            pstm.setString(2, email);
            pstm.setString(3, password);
            pstm.setInt(4, user_group_id);

            pstm.executeUpdate();
            rs = pstm.getGeneratedKeys();
            rs.next();
            this.id = rs.getInt(1);
            rs.close();
        }else{
//            todo podzielic edycje na osobne metody do kazdej zmiennej
            String update = "UPDATE users SET username = ?, email = ?, password = ?, user_group_id = ?;";
            PreparedStatement pstm2 = conn.prepareStatement(update);

            pstm2.setString(1, username);
            pstm2.setString(2, email);
            pstm2.setString(3, password);
            pstm2.setInt(4, user_group_id);

            pstm2.executeUpdate();
        }
        return "0";
    }

//        todo: test: czy metoda nie zwrocila nulla
//        todo: czy obiekt ma szystkie dane takie same jak w recordzie
//        todo:
    public static Users loadUserById(Connection conn, int id) throws SQLException {
        String selectById = "SELECT * FROM users WHERE id = ?;";
        PreparedStatement pstm = conn.prepareStatement(selectById);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();

        if(rs.next()){
            String name = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int user_group_id = rs.getInt("user_group_id");

            Users u = new Users(name, email, password, user_group_id);
            u.id = id;
            rs.close();
            return u;
        }
        rs.close();
        System.err.println("Brak urzytkownika o takim id");
        return null;
    }

    public static Users loadUserByEmail(Connection conn, String email) throws SQLException {
        String selectByEmail = "SELECT * FROM users WHERE email = ?;";
        PreparedStatement pstm = conn.prepareStatement(selectByEmail);
        pstm.setString(1, email);
        ResultSet rs = pstm.executeQuery();

        if(rs.next()){
            String name = rs.getString("username");
            String password = rs.getString("password");
            int user_group_id = rs.getInt("user_group_id");

            Users u = new Users(name, email, password, user_group_id);
            u.id = rs.getInt("id");
            rs.close();
            return u;
        }
        rs.close();
        System.err.println("Brak urzytkownika o takim emailu");
        return null;
    }

    public static Users[] loadAllUsers(Connection conn) throws SQLException {
        String selectIds = "SELECT id FROM users;";
        ResultSet rs = (conn.createStatement()).executeQuery(selectIds);
        Users[] u = new Users[1];

        while(rs.next()){
            u[u.length - 1] = loadUserById(conn, rs.getInt("id"));
            u = Arrays.copyOf(u, u.length + 1);
        }
        rs.close();
        u = Arrays.copyOf(u, u.length - 1);

        if(u.length == 0){
            System.err.println("Brak uzytkownikow");
            return null;
        }else{
            return u;
        }
    }

    public void delete(Connection conn) throws SQLException{
        if(id != 0){
            String delete = "DELETE FROM users WHERE id = ?;";
            PreparedStatement pstm = conn.prepareStatement(delete);
            pstm.setInt(1, this.id);
            pstm.executeUpdate();
            id = 0;
        }
    }

    public static Users[] loadAllByGroupId(Connection conn, int id) throws SQLException {
//        todo: pobranie wszystkich uzytkownikow z grupy
//        todo:
        return null;
    }

    public Users setUserName(String username){
        this.username = username;
        return this;
    }
    public Users setEmail(String email){
        this.email = email;
        return this;
    }
    public Users setPassword(String password){
        String hashed;
        hashed = BCrypt.hashpw(password, classes.BCrypt.gensalt());
        this.password = hashed;
        return this;
    }
    public Users setUser_group_id(int user_group_id){
        this.user_group_id = user_group_id;
        return this;
    }

    public int getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public int getUser_group_id(){
        return user_group_id;
    }

    private boolean isAllSet(){
        boolean yes = true;
        if(this.username == null) yes = false;
        if(this.email == null) yes = false;
        if(this.password == null) yes = false;
        if(this.user_group_id == 0) yes = false;
        return yes;
    }
}

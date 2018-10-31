package spring.beans;

import other.BCrypt;

import java.sql.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class User {
    private int id;
    private String name;
    private String password;
    private String email;

    public User(String name, String password, String email, boolean hashPassword) {
        this.name = name;
        if(hashPassword){
            setPassword(password);
        }else{
            this.password = password;
        }
        this.email = email;
    }

    public static User loadUserByName(Connection conn, String name)throws SQLException{
        String select = "SELECT * FROM users WHERE name=?;";
        PreparedStatement pstm = conn.prepareStatement(select);
        pstm.setString(1, name);
        ResultSet rs = pstm.executeQuery();

        if(rs.next()){
            User user = new User(rs.getString(2), rs.getString(3), rs.getString(4), false);
            user.setId(rs.getInt(1));
            return user;
        }else{
            return null;
        }
    }

    public String saveToDb(Connection conn)throws SQLException {
        if(returnIdWhenNameFound(conn) != id){
            return "name";
        }
        if(returnIdWhenEmailFound(conn) != id){
            return "email";
        }

        if(id == 0){
            insertToDb(conn);
        }else{
            updateToDb(conn);
        }
        return "ok";
    }
    private int returnIdWhenNameFound(Connection conn)throws SQLException{
        String select = "SELECT name, id FROM users;";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(select);
        while(rs.next()){
            if(rs.getString(1).equals(name)){
                return rs.getInt(2);
            }
        }
        return 0;
    }
    private int returnIdWhenEmailFound(Connection conn)throws SQLException{
        String select = "SELECT email, id FROM users;";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(select);
        while(rs.next()){
            if(rs.getString(1).equals(email)){
                return rs.getInt(2);
            }
        }
        return 0;
    }
    private void insertToDb(Connection conn)throws SQLException{
        String insert = "INSERT INTO users(name, password, email) VALUES (?, ?, ?);";
        PreparedStatement pstm = conn.prepareStatement(insert, RETURN_GENERATED_KEYS);

        pstm.setString(1, name);
        pstm.setString(2, password);
        pstm.setString(3, email);

        pstm.executeUpdate();
        ResultSet rs = pstm.getGeneratedKeys();
        rs.next();
        this.id = rs.getInt(1);
    }
    private void updateToDb(Connection conn)throws SQLException{
        String update = "UPDATE users SET name=?, password=?, email=? WHERE id=?;";
        PreparedStatement pstm = conn.prepareStatement(update);

        pstm.setString(1, name);
        pstm.setString(2, password);
        pstm.setString(3, email);
        pstm.setInt(4, id);

        pstm.executeUpdate();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public User setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        return this;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

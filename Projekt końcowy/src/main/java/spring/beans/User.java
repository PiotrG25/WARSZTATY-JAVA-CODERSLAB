package spring.beans;

import other.BCrypt;

import java.sql.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class User {
    private int id;
    private String name;
    private String password;
    private String email;

    public User(String name, String password, String email) {
        this.name = name;
        setPassword(password);
        this.email = email;
    }

    public String saveToDb(Connection conn)throws SQLException {
        if(!isNameUnique(conn)){
            return "name";
        }
        if(!isEmailUnique(conn)){
            return "email";
        }

        if(id == 0){
            insertToDb(conn);
        }else{
            updateToDb(conn);
        }
        return "ok";
    }
    private boolean isNameUnique(Connection conn)throws SQLException{
        String select = "SELECT name FROM users;";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(select);
        boolean isUnique = true;
        while(rs.next()){
            if(rs.getString(1).equals(name)){
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }
    private boolean isEmailUnique(Connection conn)throws SQLException{
        String select = "SELECT email FROM users;";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(select);
        boolean isUnique = true;
        while(rs.next()){
            if(rs.getString(1).equals(email)){
                isUnique = false;
                break;
            }
        }
        return isUnique;
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

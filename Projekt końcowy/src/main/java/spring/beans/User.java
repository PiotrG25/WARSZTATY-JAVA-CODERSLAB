package spring.beans;

import other.BCrypt;

import java.sql.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class User {
    //User long id, String name, String password, String email;
    //users: id BIGINT(20), name VARCHAR(255), password VARCHAR(255), email VARCHAR(255)

    private long id;
    private String name;
    private String password;
    private String email;

    public User(String name, String password, String email, boolean hashPassword) {
        this.name = name;
        this.email = email;

        //When we load user from dataBase we don't need to hash already hashed password
        if(hashPassword){
            setPassword(password);
        }else{
            this.password = password;
        }
    }

    public static User loadUserByName(Connection conn, String name)throws SQLException{
        String select = "SELECT * FROM users WHERE name=?;";
        PreparedStatement pstm = conn.prepareStatement(select);
        pstm.setString(1, name);
        ResultSet rs = pstm.executeQuery();

        if(rs.next()){
            User user = new User(rs.getString(2), rs.getString(3), rs.getString(4), false);
            user.setId(rs.getLong(1));
            return user;
        }else{
            return null;
        }
    }

    public String saveToDb(Connection conn)throws SQLException{
        //Validation is correct either when we insert new user and update existing user
        if(returnIdWhenNameFound(conn) != id){
            //Error when there is user with that name
            return "name";
        }
        if(returnIdWhenEmailFound(conn) != id){
            //Error when there is user with that email
            return "email";
        }

        if(id == 0){
            insertToDb(conn);
        }else{
            updateToDb(conn);
        }
        //return something
        return "ok";
    }
    private long returnIdWhenNameFound(Connection conn)throws SQLException{
        String select = "SELECT name, id FROM users;";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(select);
        while(rs.next()){
            if(rs.getString(1).equals(name)){
                return rs.getLong(2);
            }
        }
        return 0;
    }
    private long returnIdWhenEmailFound(Connection conn)throws SQLException{
        String select = "SELECT email, id FROM users;";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(select);
        while(rs.next()){
            if(rs.getString(1).equals(email)){
                return rs.getLong(2);
            }
        }
        return 0;
    }
    private void insertToDb(Connection conn)throws SQLException{
        String insert = "INSERT INTO users (name, password, email) VALUES (?, ?, ?);";
        PreparedStatement pstm = conn.prepareStatement(insert, RETURN_GENERATED_KEYS);

        pstm.setString(1, name);
        pstm.setString(2, password);
        pstm.setString(3, email);

        pstm.executeUpdate();
        ResultSet rs = pstm.getGeneratedKeys();
        rs.next();
        this.id = rs.getLong(1);
    }
    private void updateToDb(Connection conn)throws SQLException{
        String update = "UPDATE users SET name=?, password=?, email=? WHERE id=?;";
        PreparedStatement pstm = conn.prepareStatement(update);

        pstm.setString(1, name);
        pstm.setString(2, password);
        pstm.setString(3, email);
        pstm.setLong(4, id);

        pstm.executeUpdate();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
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
    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

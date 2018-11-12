package pl.coderslab.entity;

import other.BCrypt;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Entity
@Table(name = "users")
public class User {
    //User long id, String name, String password, String email;
    //users: id BIGINT(20), name VARCHAR(255), password VARCHAR(255), email VARCHAR(255)

    private long id;
    private String name;
    private String password;
    private String email;

    public User(){}

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @OneToMany(mappedBy = "user")
    private List<Game> games = new ArrayList<>();

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


    public void hashPassword(){this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());}

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
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

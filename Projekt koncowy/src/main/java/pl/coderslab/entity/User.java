package pl.coderslab.entity;

import pl.coderslab.beans.BCrypt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    //User long id, String name, String password, String email;
    //users: id BIGINT(20), name VARCHAR(255), password VARCHAR(255), email VARCHAR(255)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private String email;
    private Integer enabled;

    public User(){}

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.enabled = 1;
    }

    @OneToMany(mappedBy = "user")
    private List<Game> games = new ArrayList<>();

    public void hashPassword(){
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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

    public Boolean getEnabled(){
        return enabled == 1;
    }
    public void setEnabled(Boolean enabled){
        this.enabled = enabled ? 1 : 0;
    }
}

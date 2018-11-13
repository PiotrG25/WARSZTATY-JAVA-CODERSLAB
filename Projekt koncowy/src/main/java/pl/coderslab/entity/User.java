package pl.coderslab.entity;

import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.beans.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{8,20}$", message = "nazwa powinna zawierać 8-20 znaków, od a-z, A-Z lub 0-9")
    private String name;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$", message = "Hasło powinno zawierać 8-20 znaków, małą literę, dużą literę i cyfrę")
    private String password;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$", message = "niewłaściwy email")
    private String email;

    public User(){}

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
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
}

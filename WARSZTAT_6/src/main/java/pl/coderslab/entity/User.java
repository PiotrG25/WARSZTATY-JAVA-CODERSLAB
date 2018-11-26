package pl.coderslab.entity;

import pl.coderslab.other.BCrypt;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    /*Użytkownicy: dodawanie, modyfikacja niekluczowych informacji o sobie
    , usuwanie swojego konta. Użytkownik ma być identyfikowany po emailu (nie może się powtarzać)*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private String email;

    public User(){}

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }


    public void hashPassword(){
        this.password = BCrypt.hashpw(this.getPassword(), BCrypt.gensalt());
    }


    public Long getId() {
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

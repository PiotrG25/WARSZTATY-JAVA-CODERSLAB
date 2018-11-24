package pl.coderslab.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
public class Article {
    /*Wpisy: Każdy użytkownik może stworzyć nieograniczoną liczbę wpisów. Maksymalna długość wpisu to 140 znaków.*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank @Pattern(regexp = ".{0,140}")
    private String description;

    private Long user_id;
    private LocalDateTime date;


    public Article(){}

    public Article(String description, Long user_id, LocalDateTime date) {
        this.description = description;
        this.user_id = user_id;
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

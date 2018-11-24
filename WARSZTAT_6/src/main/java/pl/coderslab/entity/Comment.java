package pl.coderslab.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    /*Komentarze: pod każdym wpisem inni użytkownicy
     mają mieć możliwość wpisywania komentarzy. Maksymalna długość komentarza to 60 znaków.*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank @Pattern(regexp = ".{0,60}")
    private String description;

    private Long article_id;
    private Long user_id;
    private LocalDateTime date;


    public Comment(){}

    public Comment(String description, Long article_id, Long user_id, LocalDateTime date) {
        this.description = description;
        this.article_id = article_id;
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

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
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

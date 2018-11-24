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
}

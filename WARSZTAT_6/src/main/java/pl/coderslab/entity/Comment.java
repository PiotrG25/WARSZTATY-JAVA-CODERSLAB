package pl.coderslab.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
public class Comment {
    /*Komentarze: pod każdym wpisem inni użytkownicy
     mają mieć możliwość wpisywania komentarzy. Maksymalna długość komentarza to 60 znaków.*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank @Pattern(regexp = ".{0,60}")
    private String description;

    private Long user_id;

    private LocalDateTime date;
}

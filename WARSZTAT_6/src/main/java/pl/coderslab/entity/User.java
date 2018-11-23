package pl.coderslab.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {
    /*Użytkownicy: dodawanie, modyfikacja niekluczowych informacji o sobie
    , usuwanie swojego konta. Użytkownik ma być identyfikowany po emailu (nie może się powtarzać)*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$")
    private String password;

    @NotNull @NotEmpty @Email
    private String email;
}

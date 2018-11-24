package pl.coderslab.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class Message {
    /*Wiadomości: Każdy użytkownik może wysłać innemu użytkownikowi wiadomość*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank @Pattern(regexp = ".{0,60}")
    private String description;

    private Long fromUser_id;
    private Long toUser_id;
    private LocalDateTime date;
}

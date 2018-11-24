package pl.coderslab.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
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


    public Message(){}

    public Message(String description, Long fromUser_id, Long toUser_id, LocalDateTime date) {
        this.description = description;
        this.fromUser_id = fromUser_id;
        this.toUser_id = toUser_id;
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

    public Long getFromUser_id() {
        return fromUser_id;
    }

    public void setFromUser_id(Long fromUser_id) {
        this.fromUser_id = fromUser_id;
    }

    public Long getToUser_id() {
        return toUser_id;
    }

    public void setToUser_id(Long toUser_id) {
        this.toUser_id = toUser_id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

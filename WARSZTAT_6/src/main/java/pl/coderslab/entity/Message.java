package pl.coderslab.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    /*Wiadomości: Każdy użytkownik może wysłać innemu użytkownikowi wiadomość*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "fromUser_id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "toUser_id")
    private User toUser;

    private LocalDateTime date;


    public Message(){}

    public Message(String description, User fromUser, User toUser, LocalDateTime date) {
        this.description = description;
        this.fromUser = fromUser;
        this.toUser = toUser;
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

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

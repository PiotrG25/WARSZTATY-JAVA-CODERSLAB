package pl.coderslab.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class MessageDto {

    @NotNull @NotBlank @Pattern(regexp = ".{0,60}")
    private String description;

    private Long fromUser_id;
    private Long toUser_id;
    private LocalDateTime date;


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

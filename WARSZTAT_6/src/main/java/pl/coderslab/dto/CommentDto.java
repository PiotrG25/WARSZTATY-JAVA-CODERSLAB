package pl.coderslab.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CommentDto {

    @NotNull @NotBlank @Pattern(regexp = ".{0,60}")
    private String description;

}

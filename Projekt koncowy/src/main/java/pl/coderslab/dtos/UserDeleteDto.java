package pl.coderslab.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDeleteDto {

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$", message = "Hasło powinno zawierać 8-20 znaków małą literę, dużą literę i cyfrę")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

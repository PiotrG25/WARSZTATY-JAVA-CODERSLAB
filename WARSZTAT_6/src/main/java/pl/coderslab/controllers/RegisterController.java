package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.dto.UserDto;

@Controller
public class RegisterController {
    /*Strona ma pobierać email i hasło.

    Jeżeli takiego adresu email nie ma jeszcze w systemie (tabeli w bazie)
    , to rejestrujemy użytkownika i logujemy (przekierowanie na stronę główną).

    Jeżeli taki adres email jest, to przekierowujemy do strony tworzenia użytkownika
    (ta sama strona) i wyświetlamy komunikat o zajętym adresie email.*/


    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("userDto", new UserDto());
        return "register";
    }
}

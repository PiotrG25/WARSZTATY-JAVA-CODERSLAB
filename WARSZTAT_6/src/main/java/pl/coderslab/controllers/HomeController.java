package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    /*Strona wyświetlająca wszystkie Tweety jakie znajdują się w systemie
    ( od najnowszego do najstarszego). Nad nimi ma być widoczny formularz do stworzenia nowego wpisu.*/

    @RequestMapping("/")
    public String home(){
        return "home";
    }
}

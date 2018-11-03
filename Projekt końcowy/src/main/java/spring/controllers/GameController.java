package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.beans.RandomMachine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class GameController {

    //todo header loggedIn i loggedOut
    //todo footer
    //todo sprawdzić najpopularniejsze błędy na kture podatne są formularze
    //todo Ostylować formularze
    //todo JS login
    //todo JS register
    //todo podzielić css
    //todo Strona użytkownika
    //todo userController
    //todo widok statystyk
    //todo TOP 10
    //todo zmiana hasła
    //todo zmiana maila
    //todo usunięcie konta
    //todo JS ustawiający grę wg kodu
    //todo JS odsyłający na server
    //todo GameController

    @PostMapping("/game")
    public String postGame(HttpServletRequest request, HttpServletResponse response){


return null;
    }

    @GetMapping("/game")
    public String getGame(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "redirect:/login";
        }

        String level = request.getParameter("level");

        if(level == null || level.isEmpty()){
            return "header";
        }

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(level);
        if(!matcher.matches()){
            return "header";
        }

        int levelInt = Integer.parseInt(level);
        if(levelInt < 2 || levelInt > 5){
            return "header";
        }

        int [][] tab = new RandomMachine(levelInt*levelInt).getTab();
        request.setAttribute("length", levelInt);
        request.setAttribute("tab", tab);
        return "game";
    }
}

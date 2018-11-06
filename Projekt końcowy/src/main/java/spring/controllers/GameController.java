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

    //todo Klasa Games
    //todo games.leadByUser
    //todo games.countByUser na ilość wygranych
    //todo JS zamienić punkty i ruchy na "pozostało ruchów: "
    //todo tutorial z jednym guzikiem
    //todo stan tutorialu przechowywać w ciasteczkach
    //todo UserController
    //todo widok statystyk
    //todo TOP 10
    //todo zmiana hasła
    //todo zmiana maila
    //todo usunięcie konta
    //todo sprawdzić najpopularniejsze błędy na które podatne są formularze
    //todo JS login
    //todo JS register
    //todo Strona użytkownika
    //todo JS odsyłający na server
    //todo podzielić css
    //todo naprawic css'a
    //todo ostylować formulaże
    //todo header loggedOut ładniej
    //todo footer

    @PostMapping("/game")
    public String postGame(HttpServletRequest request, HttpServletResponse response){
        //todo
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

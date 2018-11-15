package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.beans.RandomMachine;
import pl.coderslab.entity.Game;
import pl.coderslab.entity.User;
import pl.coderslab.repository.GameRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class GameController {

    //umożliwić zmianę przeglądanego poziomu (osobny)JS plus ukryty formularz
    //I ustawiać tylko jedną listę w zależności od poziomu
    //todo statystyki user
    //bestByTime
    //bestByMoves
    //Onlevel 2,3,4,5
    //todo sprawdzić najpopularniejsze błędy na które podatne są formularze
    //todo JS login
    //todo JS register
    //todo footer

    @Autowired
    GameRepository gameRepository;

    @PostMapping("/game")
    public String postGame(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "redirect:/main";
        }

        Integer level = (Integer)session.getAttribute("level");

        String moves = request.getParameter("moves");
        String time = request.getParameter("time");

        if(moves == null || moves.isEmpty() || time == null || time.isEmpty()){
            return "redirect:/main";
        }

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher movesMatcher = pattern.matcher(moves);
        Matcher timeMatcher = pattern.matcher(time);

        if(!movesMatcher.matches() || !timeMatcher.matches()){
            return "redirect:/main";
        }

        long movesLong = Long.parseLong(moves);
        long timeLong = Long.parseLong(time);

        User user = (User)session.getAttribute("user");
        System.out.println(user.getId());
        Game game = new Game(level, movesLong, timeLong, user);

        gameRepository.save(game);

        return "redirect:/main";
    }

    @GetMapping("/game")
    public String getGame(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "redirect:/main";
        }

        String level = request.getParameter("level");

        if(level == null || level.isEmpty()){
            return "redirect:/main";
        }

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(level);
        if(!matcher.matches()){
            return "redirect:/main";
        }
        int levelInt = Integer.parseInt(level);
        if(levelInt < 2 || levelInt > 5){
            return "redirect:/main";
        }

        session.setAttribute("level", levelInt);

        int [][] tab = new RandomMachine(levelInt*levelInt).getTab();
        request.setAttribute("length", levelInt);
        request.setAttribute("tab", tab);
        return "game";
    }
}

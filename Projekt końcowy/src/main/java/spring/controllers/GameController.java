package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.beans.DbUtil;
import spring.beans.Game;
import spring.beans.RandomMachine;
import spring.beans.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class GameController {

    //todo Cookies in EL
    //todo HIBERNATE
    //todo GameController
    //zwiększyć czytelność kodu
    //todo zwiększyć czytelność RandomMachine
    //todo zrobić LOG wg wzorca singletonu
    //todo UserController
    //umożliwić zmianę przeglądanego poziomu (osobny)JS plus ukryty formularz
    //I ustawiać tylko jedną listę w zależności od poziomu
    //zmiana hasła
    //zmiana maila
    //usunięcie konta active/inactive
    //todo statystyki user
    //w skrucie
    //bestByTime
    //bestByMoves
    //Onlevel
    //todo sprawdzić najpopularniejsze błędy na które podatne są formularze
    //todo JS login
    //todo JS register
    //todo podzielić css
    //todo naprawic css'a
    //todo ostylować formularze
    //todo footer

    @PostMapping("/game")
    public String postGame(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "redirect:/main";
        }

        Cookie[] cookies = request.getCookies();
        Cookie levelCookie = null;
        for(Cookie c : cookies){
            if(c.getName().equals("level")){
                levelCookie = c;
            }
        }

        if(levelCookie == null){
            return "redirect:/main";
        }
        String level = levelCookie.getValue();
        levelCookie.setMaxAge(0);
        response.addCookie(levelCookie);

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

        String moves = request.getParameter("moves");
        String time = request.getParameter("time");

        if(moves == null || moves.isEmpty() || time == null || time.isEmpty()){
            //todo jakiś błąd here
            return "redirect:/main";
        }

        Matcher movesMatcher = pattern.matcher(moves);
        Matcher timeMatcher = pattern.matcher(time);

        if(!movesMatcher.matches() || !timeMatcher.matches()){
            return "redirect:/main";
        }

        int movesInt = Integer.parseInt(moves);
        long timeLong = Long.parseLong(time);

        User user = (User)session.getAttribute("user");
        System.out.println(user.getId());
        Game game = new Game(user.getId(), levelInt, movesInt, timeLong);

        try(Connection conn = DbUtil.getConn()){
            game.saveToDb(conn);
        }catch(SQLException e){
            e.printStackTrace();
        }

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

        Cookie levelCookie = new Cookie("level", level);
        levelCookie.setPath("/");
        levelCookie.setMaxAge(60 * 15);
        response.addCookie(levelCookie);

        int [][] tab = new RandomMachine(levelInt*levelInt).getTab();
        request.setAttribute("length", levelInt);
        request.setAttribute("tab", tab);
        return "game";
    }
}

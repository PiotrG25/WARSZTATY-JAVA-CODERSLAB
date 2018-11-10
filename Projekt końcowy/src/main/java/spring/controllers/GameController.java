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

    //todo naprawić błąd z wysyłaniem formulaża do PostGame za pomocą JS
    //todo Cookies in EL
    //todo zamienić baze danych na hibernetową
    //todo usunąć wylogowywanie przy wygranej
    //todo Klasa Games
    //load10BestByMoves
    //load10BestByTime
    //OnLevel
    //todo games.countByUserOnLevel na ilość wygranych
    //przekazac level do postGame
    //todo zwiększyć czytelność RandomMachine
    //todo zrobić LOG wg wzorca singletonu
    //todo tutorial z jednym guzikiem
    //todo stan tutorialu przechowywać w ciasteczkach
    //todo Strona użytkownika
    //todo UserController
    //todo widok statystyk
    //todo zmiana hasła
    //todo zmiana maila
    //todo usunięcie konta active/inactive
    //todo sprawdzić najpopularniejsze błędy na które podatne są formularze
    //todo JS login
    //todo JS register
    //todo podzielić css
    //todo naprawic css'a
    //todo ostylować formulaże
    //todo footer

    @PostMapping("/game")
    @ResponseBody
    public String postGame(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "redirect:/main1";
        }

        Cookie[] cookies = request.getCookies();
        Cookie levelCookie = null;
        for(Cookie c : cookies){
            if(c.getName().equals("level")){
                levelCookie = c;
            }
        }

        if(levelCookie == null){
            return "redirect:/main2";
        }
        String level = levelCookie.getValue();
        levelCookie.setMaxAge(0);
        response.addCookie(levelCookie);

        if(level == null || level.isEmpty()){
            return "redirect:/main3";
        }

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(level);
        if(!matcher.matches()){
            return "redirect:/main4";
        }

        int levelInt = Integer.parseInt(level);
        if(levelInt < 2 || levelInt > 5){
            return "redirect:/main5";
        }

        String moves = request.getParameter("moves");
        String time = request.getParameter("time");

        if(moves == null || moves.isEmpty() || time == null || time.isEmpty()){
            //todo jakiś błąd here
            return "redirect:/main6";
        }

        Matcher movesMatcher = pattern.matcher(moves);
        Matcher timeMatcher = pattern.matcher(time);

        if(!movesMatcher.matches() || !timeMatcher.matches()){
            return "redirect:/main7";
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

        return "redirect:/logout8";
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

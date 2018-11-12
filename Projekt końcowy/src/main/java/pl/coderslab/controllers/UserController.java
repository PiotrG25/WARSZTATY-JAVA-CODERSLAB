package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.beans.DbUtil;
import pl.coderslab.entity.Game;
import pl.coderslab.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class UserController {

    @PostMapping("/user")
    public String postUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "redirect:/main";
        }
        //todo
        //todo
        //todo
        return null;
    }
    @GetMapping("/user")
    public String getUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "redirect:/main";
        }

        try(Connection conn = DbUtil.getConn()){
            long userId = ((User)session.getAttribute("user")).getId();

            int winCount = Game.countAllGamesByUserId(conn, userId);
            long movesCount = Game.countAllMovesByUserId(conn, userId);
            long timeCount = Game.countAllTimeByUserId(conn, userId);

            request.setAttribute("winCount", winCount);
            request.setAttribute("movesCount", movesCount);
            request.setAttribute("timeCount", timeCount);

            request.setAttribute("gamesByMoves", Game.load10BestMovesByUserIdOnLevel(conn, userId, 2));
            request.setAttribute("gamesByTime", Game.load10BestTimeByUserIdOnLevel(conn, userId, 2));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "user";
    }
}

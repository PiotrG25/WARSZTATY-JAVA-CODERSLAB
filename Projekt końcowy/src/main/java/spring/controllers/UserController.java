package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.beans.DbUtil;
import spring.beans.Game;
import spring.beans.User;

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
            User user = (User)session.getAttribute("user");
            int count = Game.countAllByUser(conn, user);
            request.setAttribute("count", count);
            request.setAttribute("gamesByMoves", Game.load10BestByMovesOnLevel(conn, user, 2));
            request.setAttribute("gamesByTime", Game.load10BestByTimeOnLevel(conn, user, 2));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "user";
    }
}

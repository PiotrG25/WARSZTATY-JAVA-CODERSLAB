package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.beans.DbUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class MainController {

    //todo
    //todo
    //todo

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("user", "user1");
        session.setMaxInactiveInterval(10);
        return "redirect:/main";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String mainView(HttpServletRequest request, HttpServletResponse response){
        HttpSession sess = request.getSession();
        if(sess.getAttribute("user") == null || sess.getAttribute("user").equals("")){
            return "login";
        }

        String level = request.getParameter("level");
        if(level != null && !level.isEmpty()){
            request.setAttribute("level", Integer.parseInt(level));
            return "game";
        }else{
            return "header";
        }
    }

    @RequestMapping("/print")
    @ResponseBody
    public String print(){
        StringBuilder sb = new StringBuilder();
        try(Connection conn = DbUtil.getConn()){
            String query = "SELECT * FROM users;";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            rs.next();
            return rs.getString(2);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return "NnUuLlLl";
    }
}

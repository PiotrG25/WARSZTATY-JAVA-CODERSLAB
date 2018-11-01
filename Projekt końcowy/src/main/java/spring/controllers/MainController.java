package spring.controllers;

import org.springframework.stereotype.Controller;
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

    //todo Strona użytkownika
    //todo
    //todo
    //todo
    //todo

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/main";
    }

    @RequestMapping("/main")
    public String mainView(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "redirect:/login";
        }

        String level = request.getParameter("level");

        if(level == null || level.isEmpty()){
            return "header";
        }
        for(char c : level.toCharArray()){
            if(!Character.isDigit(c)){
                return "header";
            }
        }
        int levelInt = Integer.parseInt(level);
        if(levelInt < 1 || levelInt > 5){
            return "header";
        }

        request.setAttribute("level", levelInt);
        return "game";

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

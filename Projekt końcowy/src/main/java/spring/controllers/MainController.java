package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.beans.FindCookie;
import spring.beans.FindSession;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.ImagingOpException;
import java.io.IOException;

@Controller
public class MainController {

    //todo
    //todo
    //todo

    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response)throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("user", "user1");
        session.setMaxInactiveInterval(5);
        response.sendRedirect("/main");
    }

    @RequestMapping("/main")
    public String mainView(HttpServletRequest request, HttpServletResponse response){
        String level = request.getParameter("level");
        if(level != null && !level.isEmpty()){
            request.setAttribute("level", Integer.parseInt(level));
            return "game";
        }else{
            return "header";
        }
    }
}

package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    //todo
    //todo
    //todo

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("user", "user1");
        session.setMaxInactiveInterval(5);
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
}

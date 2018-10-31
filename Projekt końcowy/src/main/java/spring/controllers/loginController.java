package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class loginController {

    @PostMapping("/login")
    public String postLogin(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("user", "user1");
        session.setMaxInactiveInterval(60 * 5);
        return "redirect:/main";
    }
    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }
}

package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class registerController {

    @PostMapping("/register")
    public String postRegister(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("user", "user1");
        session.setMaxInactiveInterval(10);
        return "redirect:/main";
    }
    @GetMapping("/register")
    public String getRegister(HttpServletRequest request, HttpServletResponse response){
        return "register";
    }
}

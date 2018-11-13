package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @RequestMapping("/main")
    public String mainAction(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "redirect:/login";
        }
        return "main";
    }
}

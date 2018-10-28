package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @RequestMapping("/main")
    public String mainView(HttpServletRequest request, HttpServletResponse response){
        String level = request.getParameter("level");
        if(level != null && !level.isEmpty()){
            request.setAttribute("level", level);
            return "game";
        }else{
            return "header";
        }
    }
}

package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @PostMapping("/main")
    public void login(HttpServletRequest request, HttpServletResponse response)throws IOException {
        HttpSession sess = request.getSession();
        sess.setAttribute("user", "user1");
        sess.setMaxInactiveInterval(5);
        response.sendRedirect("/main");
    }

    @GetMapping("/main")
    public String mainView(HttpServletRequest request, HttpServletResponse response){
        FindSession.orRedirect(request, response);

        String level = request.getParameter("level");
        if(level != null && !level.isEmpty()){
            request.setAttribute("level", level);
            return "game";
        }else{
            return "header";
        }
    }
}

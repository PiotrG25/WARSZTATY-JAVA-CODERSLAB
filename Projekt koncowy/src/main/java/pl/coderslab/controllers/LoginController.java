package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.beans.BCrypt;
import pl.coderslab.beans.CheckValidity;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserRepository userDao;

    @PostMapping("/login")
    public String postLogin(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if(name == null || name.isEmpty() || password == null || password.isEmpty()){
            request.setAttribute("arguments", true);
        }else if(!CheckValidity.isNameValid(name) || !CheckValidity.isPasswordValid(password)){
            request.setAttribute("error", true);
        }else{

            User user = userDao.findUserByName(name);

            if(user == null || !BCrypt.checkpw(password, user.getPassword())){
                request.setAttribute("error", true);
            }else{
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(60 * 15);
                request.setAttribute("success", true);
            }
        }
        return "login";
    }

    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "login";
        }else{
            return "redirect:/main";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/main";
    }
}

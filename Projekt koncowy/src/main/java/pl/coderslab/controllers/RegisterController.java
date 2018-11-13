package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.beans.CheckValidity;
import pl.coderslab.entity.User;
import pl.coderslab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String postRegister(@ModelAttribute User user, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            return "redirect:/main";
        }

        String name = user.getName();
        String password = user.getPassword();
        String confirmPassword = request.getParameter("confirmPassword");
        String email = user.getEmail();

        if(name == null || name.isEmpty() || password == null || password.isEmpty() ||
        confirmPassword == null || confirmPassword.isEmpty() || email == null || email.isEmpty()) {
            request.setAttribute("arguments", true);
        }else if(!CheckValidity.isNameValid(name) || !CheckValidity.isPasswordValid(password)) {
            request.setAttribute("pattern", true);
        }else if(!CheckValidity.isEmailValid(email)){
            request.setAttribute("emailPattern", true);
        }else if(!password.equals(confirmPassword)){
            request.setAttribute("differentPassword", true);
        }else{

            user.hashPassword();
            String effect = userService.saveToDb(user);

            if(effect.equals("name")){
                request.setAttribute("nameNotUnique", true);
            }else if(effect.equals("email")){
                request.setAttribute("emailNotUnique", true);
            }else{//if(effect.equals("ok"))

                request.setAttribute("success", true);
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(60 * 5);
            }
        }
        return "register";
    }
    @GetMapping("/register")
    public String getRegister(HttpServletRequest request, HttpServletResponse response, Model model){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            model.addAttribute("user", new User());
            return "register";
        }else{
            return "redirect:/main";
        }
    }
}

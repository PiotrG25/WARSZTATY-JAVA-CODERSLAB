package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.beans.CheckValidity;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.Validator;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;
    @Autowired
    Validator validator;

    @PostMapping("/register")
    public String postRegister(HttpServletRequest request, HttpServletResponse response, @Valid User user, BindingResult result){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            return "redirect:/main";
        }

        if(result.hasErrors()){
            return "register";
        }

        String password = user.getPassword();
        String password2 = request.getParameter("password2");

        if(password2 == null || password2.isEmpty()){
            request.setAttribute("arguments",true);
        }else if(!password.equals(password2)){
            request.setAttribute("differentPassword", true);
        }else{

            user.hashPassword();
            String effect = userService.saveToDb(user);

            if(isSuccesOrSetError(request, effect)){
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

    private boolean isSuccesOrSetError(HttpServletRequest request, String effect){
        if(effect.equals("name")){
            request.setAttribute("nameNotUnique", true);
            return false;
        }else if(effect.equals("email")){
            request.setAttribute("emailNotUnique", true);
            return false;
        }else{//if(effect.equals("ok"))
            request.setAttribute("success", true);
            return true;
        }
    }
}

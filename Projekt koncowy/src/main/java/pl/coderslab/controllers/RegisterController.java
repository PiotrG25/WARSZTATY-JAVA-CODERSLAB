package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.beans.CheckValidity;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userDao;

    @PostMapping("/register")
    public String postRegister(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User user){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            return "redirect:/main";
        }

        String name = user.getName();
        String password = user.getPassword();
        String password2 = request.getParameter("password2");
        String email = user.getEmail();

        if(name == null || name.isEmpty() || password == null || password.isEmpty() ||
        password2 == null || password2.isEmpty() || email == null || email.isEmpty()){
            request.setAttribute("arguments",true);
        }else if(isErrorAndSetIt(request, name, email, password, password2)){
        }else{

            user.hashPassword();
            userDao.save(user);
            String effect = "ok";

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

    private boolean isErrorAndSetIt(HttpServletRequest request, String name, String email, String password, String password2){
        boolean isError = false;
        if(!CheckValidity.isNameValid(name)){
            request.setAttribute("name", true);
            isError = true;
        }
        if(!CheckValidity.isEmailValid(email)){
            request.setAttribute("email", true);
            isError = true;
        }
        if(!CheckValidity.isPasswordValid(password)){
            request.setAttribute("password", true);
            isError = true;
        }
        if(!password.equals(password2)){
            request.setAttribute("differentPassword", true);
            isError = true;
        }
        return isError;
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

package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.beans.CheckValidity;
import pl.coderslab.dtos.UserDto;
import pl.coderslab.entity.User;
import pl.coderslab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String postRegister(@Valid UserDto userDto, BindingResult result, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            return "redirect:/main";
        }

        if(result.hasErrors()){
            return "register";
        }

        if(!userDto.getPassword().equals(userDto.getConfirmPassword())){
            request.setAttribute("differentPassword", true);
        }else{

            User user = userDto.getUser();
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
            model.addAttribute("userDto", new UserDto());
            return "register";
        }else{
            return "redirect:/main";
        }
    }
}

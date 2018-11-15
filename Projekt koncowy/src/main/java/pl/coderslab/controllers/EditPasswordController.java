package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.dtos.UserEditPasswordDto;
import pl.coderslab.entity.User;
import pl.coderslab.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class EditPasswordController {

    @Autowired
    UserService userService;

    @PostMapping("/editPassword")
    public String postEditPassword(@ModelAttribute @Valid UserEditPasswordDto userEditPasswordDto,
                                   BindingResult result, HttpSession session, Model model)
    {
        if(session.getAttribute("user") == null){
            return "redirect:/main";
        }
        if(result.hasErrors()){
            return "editPassword";
        }

        if(!userEditPasswordDto.getNewPassword().equals(userEditPasswordDto.getConfirmNewPassword())){
            model.addAttribute("differentPassword", true);
            return "editPassword";
        }
        User user = (User) session.getAttribute("user");
        user.setPassword(userEditPasswordDto.getNewPassword());
        user.hashPassword();
        userService.saveToDb(user);

        return "redirect:/main";
    }

    @GetMapping("/editPassword")
    public String getEditPassword(HttpSession session, Model model){
        if(session.getAttribute("user") == null){
            return "redirect:/main";
        }
        model.addAttribute("userEditPasswordDto", new UserEditPasswordDto());
        return "editPassword";
    }
}

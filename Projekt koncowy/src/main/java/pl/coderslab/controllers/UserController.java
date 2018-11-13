package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.beans.BCrypt;
import pl.coderslab.beans.CheckValidity;
import pl.coderslab.entity.User;
import pl.coderslab.repository.GameRepository;
import pl.coderslab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    GameRepository gameRepository;

    @PostMapping("/editEmail")
    public String postEditEmail(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "redirect:/main";
        }

        User user = (User)session.getAttribute("user");
        String newEmail = request.getParameter("newEmail");
        String password = user.getPassword();
        String confirmPassword = request.getParameter("confirmPassword");

        if(newEmail == null || newEmail.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()){
            request.setAttribute("arguments", true);
        }else if(!CheckValidity.isEmailValid(newEmail)){
            request.setAttribute("emailPattern", true);
        }else if(!BCrypt.checkpw(confirmPassword, password)){
            request.setAttribute("password", true);
        }else{
            String oldEmail = user.getEmail();
            user.setEmail(newEmail);
            String effect = userService.saveToDb(user);

            if(effect.equals("email")){
                request.setAttribute("email", true);
                user.setEmail(oldEmail);
            }else{
                request.setAttribute("success", true);
            }
        }
        return "editEmail";
    }

    @GetMapping("/editEmail")
    public String getEditEmail(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "redirect:/main";
        }
        return "editEmail";
    }

    @PostMapping("/user")
    public String postUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "redirect:/main";
        }
        //todo
        //todo
        //todo
        return null;
    }
    @GetMapping("/user")
    public String getUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return "redirect:/main";
        }
        long userId = ((User)session.getAttribute("user")).getId();

        Integer winCount = gameRepository.countAllGamesByUserId(userId);
        Long movesCount = gameRepository.countAllMovesByUserId(userId);
        Long timeCount = gameRepository.countAllTimeByUserId(userId);

        movesCount = movesCount == null ? 0 : movesCount;
        timeCount = timeCount == null ? 0 : timeCount;

        request.setAttribute("winCount", winCount);
        request.setAttribute("movesCount", movesCount);
        request.setAttribute("timeCount", timeCount);

        request.setAttribute("gamesByMoves", gameRepository.load10BestMovesByUserIdOnLevel(userId, 2));
        request.setAttribute("gamesByTime", gameRepository.load10BestTimeByUserIdOnLevel(userId, 2));
        return "user";
    }
}

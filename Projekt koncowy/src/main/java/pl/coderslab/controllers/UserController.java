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
import pl.coderslab.services.GameService;
import pl.coderslab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    GameService gameService;

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

        Integer winCount = gameService.countAllGamesByUserId(userId);
        Long movesCount = gameService.countAllMovesByUserId(userId);
        Long timeCount = gameService.countAllTimeByUserId(userId);

        movesCount = movesCount == null ? 0 : movesCount;
        timeCount = timeCount == null ? 0 : timeCount;

        request.setAttribute("winCount", winCount);
        request.setAttribute("movesCount", movesCount);
        request.setAttribute("timeCount", timeCount);

        request.setAttribute("gamesByMoves2", gameService.load10BestMovesByUserIdOnLevel(userId, 2));
        request.setAttribute("gamesByTime2", gameService.load10BestTimeByUserIdOnLevel(userId, 2));
        request.setAttribute("gamesByMoves3", gameService.load10BestMovesByUserIdOnLevel(userId, 3));
        request.setAttribute("gamesByTime3", gameService.load10BestTimeByUserIdOnLevel(userId, 3));
        request.setAttribute("gamesByMoves4", gameService.load10BestMovesByUserIdOnLevel(userId, 4));
        request.setAttribute("gamesByTime4", gameService.load10BestTimeByUserIdOnLevel(userId, 4));
        request.setAttribute("gamesByMoves5", gameService.load10BestMovesByUserIdOnLevel(userId, 5));
        request.setAttribute("gamesByTime5", gameService.load10BestTimeByUserIdOnLevel(userId, 5));
        return "user";
    }
}

package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.repository.GameRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    GameRepository gameRepository;

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

package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.dao.GameDao;
import pl.coderslab.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    GameDao gameDao;

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

        int winCount = gameDao.countAllGamesByUserId(userId);
        long movesCount = gameDao.countAllMovesByUserId(userId);
        long timeCount = gameDao.countAllTimeByUserId(userId);

        request.setAttribute("winCount", winCount);
        request.setAttribute("movesCount", movesCount);
        request.setAttribute("timeCount", timeCount);

        request.setAttribute("gamesByMoves", gameDao.load10BestMovesByUserIdOnLevel(userId, 2));
        request.setAttribute("gamesByTime", gameDao.load10BestTimeByUserIdOnLevel(userId, 2));
        return "user";
    }
}

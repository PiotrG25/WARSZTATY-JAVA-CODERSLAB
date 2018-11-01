package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import other.BCrypt;
import spring.beans.CheckValidity;
import spring.beans.DbUtil;
import spring.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class loginController {

    @PostMapping("/login")
    //todo wyczyścić kod
    public String postLogin(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if(name == null || name.isEmpty() || password == null || password.isEmpty()){
            request.setAttribute("arguments", true);
        }else if(!CheckValidity.isNameValid(name) || !CheckValidity.isPasswordValid(password)) {
            request.setAttribute("error", true);
        }else{

            try(Connection conn = DbUtil.getConn()){
                User user = User.loadUserByName(conn, name);

                if(user == null || !BCrypt.checkpw(password, user.getPassword())){
                    request.setAttribute("error", true);
                }else{
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(60 * 5);
                    request.setAttribute("success", true);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return "login";
    }
    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }
}

package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.beans.CheckValidity;
import spring.beans.DbUtil;
import spring.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class registerController {

    @PostMapping("/register")
    public String postRegister(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email");

        if(name == null || name.isEmpty() || password == null || password.isEmpty() ||
        password2 == null || password2.isEmpty() || email == null || email.isEmpty()){
            request.setAttribute("arguments",true);
        }else if(isErrorAndSetIt(request, name, email, password, password2)){
        }else{
            try(Connection conn = DbUtil.getConn()){
                User user = new User(name, password, email, true);
                String effect = user.saveToDb(conn);
                if(isSuccesOrSetError(request, effect)){
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(60 * 5);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return "register";
    }
    @GetMapping("/register")
    public String getRegister(HttpServletRequest request, HttpServletResponse response){
        return "register";
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
            request.setAttribute("diferentPassword", true);
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

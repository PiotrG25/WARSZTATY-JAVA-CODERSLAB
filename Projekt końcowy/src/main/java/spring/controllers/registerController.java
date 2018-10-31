package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.beans.DbUtil;
import spring.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            return "register";
        }

        if(setIfIsAnyError(request, name, email, password, password2)){
            return "register";
        }

        try(Connection conn = DbUtil.getConn()){
            User user = new User(name, password, email);
            String effect = user.saveToDb(conn);
            setEffectToDb(request, effect);
        }catch (SQLException e){
            e.printStackTrace();
        }

        session.setAttribute("user", "user1");
        session.setMaxInactiveInterval(60 * 5);
        return "register";
    }
    @GetMapping("/register")
    public String getRegister(HttpServletRequest request, HttpServletResponse response){
        return "register";
    }

    private boolean setIfIsAnyError(HttpServletRequest request, String name, String email, String password, String password2){
        boolean error = false;
        if(!isNameValid(name)){
            request.setAttribute("name", true);
            error = true;
        }
        if(!isEmailValid(email)){
            request.setAttribute("email", true);
            error = true;
        }
        if(!isPasswordValid(password)){
            request.setAttribute("password", true);
            error = true;
        }
        if(!password.equals(password2)){
            request.setAttribute("diferentPassword", true);
            error = true;
        }
        return error;
    }
    private void setEffectToDb(HttpServletRequest request, String effect){
        if(effect.equals("name")){
            request.setAttribute("nameNotUnique", true);
        }
        if(effect.equals("email")){
            request.setAttribute("emailNotUnique", true);
        }
        if(effect.equals("ok")){
            request.setAttribute("success", true);
        }
    }

    private boolean isNameValid(String name){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{8,20}$");
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
    private boolean isEmailValid(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
    private boolean isPasswordValid(String password){
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$");
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
}

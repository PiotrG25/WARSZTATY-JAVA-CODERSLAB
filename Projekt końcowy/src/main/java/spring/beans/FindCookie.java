package spring.beans;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FindCookie {
    public static void orRedirect(HttpServletRequest request, HttpServletResponse response){
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                response.sendRedirect("/login.jsp");
            }
            boolean logged = false;
            for (Cookie c : cookies) {
                if (c.getName().equals("logged")) {
                    logged = true;
                    break;
                }
            }
            if (logged == false) {
                response.sendRedirect("/login.jsp");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

package spring.beans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FindSession {
    public static void orRedirect(HttpServletRequest request, HttpServletResponse response){
        try {//nie działa
            HttpSession session = request.getSession();
            if(session.getAttribute("user") == null || session.getAttribute("user").equals("")) {
                response.sendRedirect("/login.jsp");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

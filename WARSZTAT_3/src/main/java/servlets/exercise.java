package servlets;

import classes.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "exercise", urlPatterns = "/exercise")
public class exercise extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");

        switch (type){
            case "add":
                break;
            case "edit":
                break;
            case "delete":
                break;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/warsztat2?useTimezone=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8",
                        "root", "coderslab");
        ){
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/warsztat2?useTimezone=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8",
                        "root", "coderslab");
        ){
            Exercise[] exercises = Exercise.loadAllExercises(conn);
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            request.setAttribute("exercises", exercises);
            getServletContext().getRequestDispatcher("/exercise.jsp").forward(request, response);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

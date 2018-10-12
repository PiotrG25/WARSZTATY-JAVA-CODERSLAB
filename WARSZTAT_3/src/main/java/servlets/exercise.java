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
            response.getWriter().append(
                    "<a href='/main'>Strona główna</a><br/>" +
                    "<a href='/addExercise'>dodanie zadania</a><br/>" +
                    "<a href='/editExercise'>edycja zadania</a><br/>" +
                    "<a href='/deleteExercise'>usuniecie zadania</a><br/>"
                    );
            response.getWriter().append("" +
                    "<table>" +
                    "   <tr>" +
                    "       <td>id</td>" +
                    "       <td>title</td>" +
                    "       <td>description</td>" +
                    "   </tr>"
            );
            for(Exercise e : exercises){
                response.getWriter().append("" +
                    "   <tr>" +
                    "       <td>" + e.getId() + "</td>" +
                    "       <td>" + e.getTitle() + "</td>" +
                    "       <td>" + e.getDescription() + "</td>" +
                    "   </tr>"
                );
            }
            response.getWriter().append(
                    "</table>"
            );
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

package servlets;

import classes.User_group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "user_group", urlPatterns = "/user_group")
public class user_group extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");

        String id = request.getParameter("id");
        String name = request.getParameter("name");

        if(type == null || type.isEmpty()){
            response.sendRedirect("/user_group");
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

            boolean doAdd = (name != null && !name.isEmpty());
            boolean doDelete = (id != null && !id.isEmpty());
            boolean doEdit = (doAdd && doDelete);

            if(type.equals("add") && doAdd){
                User_group user_group = new User_group(name);
                user_group.saveToDB(conn);
            }else if(type.equals("edit") && doEdit){
                User_group user_group = User_group.loadUser_groupById(conn, Integer.parseInt(id));
                if(user_group != null){
                    user_group.setName(name);
                    user_group.saveToDB(conn);
                }
            }else if(type.equals("delete") && doDelete){
                User_group user_group = User_group.loadUser_groupById(conn, Integer.parseInt(id));
                if(user_group != null){
                    user_group.delete(conn);
                }
            }

            response.sendRedirect("/user_group");

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
            User_group[] user_groups = User_group.loadAllUser_groups(conn);
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            request.setAttribute("groups", user_groups);
            getServletContext().getRequestDispatcher("/user_group.jsp").forward(request, response);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

package pl.coderslab.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public static Connection getConn() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/lastproject?" +
                "useUnicode=yes&useTimezone=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true",
                "root", "coderslab"
        );
    }
}

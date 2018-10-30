package spring.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/lastproject?" +
                "useUnicode=yes&useTimezone=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true",
                "root", "coderslab"
        );
    }
}

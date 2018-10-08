package app;

import classes.User_group;

import java.sql.Connection;
import java.sql.SQLException;

public class User_groupHandling {
    public static void addUser_group(Connection conn)throws SQLException {
        System.out.println("Dodawanie grupy");

        System.out.println("Podaj nazwe grupy");
        String name = MainApp.scanner.next();

        User_group user_group = new User_group(name);

        boolean added = false;

        do{
            String adding = user_group.saveToDB(conn);
            if(adding.equals("name")){
                System.out.println("Brakuje zanwy grupy");
                name = MainApp.scanner.next();
            }else{
                added = true;
            }
        }while(added == false);

        System.out.println("----------");
    }
}

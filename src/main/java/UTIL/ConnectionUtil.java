package UTIL;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    static  String url="jdbc:mysql://localhost:3437/socialmedia";
    static String user = "root";
    static String password= "";

    private static Connection connection = null;

    /**
     * @return active connection to the database
     */

    public static Connection getConnection() {
        if(connection == null){
            try {
                connection = DriverManager.getConnection(url,user,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }

    /**
    public static void resetTestDatabase() throws SQLException {
//        if there is no connection, use the getConnection method to set it up
        if(connection == null){
            getConnection();
        }else {
//            otherwise, recreate the tables without setting up a new connection
            try {
                FileReader sqlReader = new FileReader("src/main/resources/SocialMedia.sql");
              //  connection.(connection, sqlReader);
            } catch (SQLException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

     */
}

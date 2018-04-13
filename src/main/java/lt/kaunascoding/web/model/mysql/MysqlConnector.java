package lt.kaunascoding.web.model.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnector {

    public Connection Connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:D:\\Programs\\Projects\\springTest\\src\\main\\resources\\static\\webdata.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
        }
        return conn;
    }
    /*
    public Connection Connect() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Class.forName("com.mysql.jdbc.Driver");
        }catch (SQLException o){

        } catch (ClassNotFoundException e){

        }
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://192.168.100.100:3306/webpage?useUnicode=yes&characterEncoding=UTF-8","root", "root");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        if (connection != null) {
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }
    */
}

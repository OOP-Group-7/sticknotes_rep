package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mydbConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/stickynotesdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection1() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Ensure the MySQL JDBC Driver is available
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

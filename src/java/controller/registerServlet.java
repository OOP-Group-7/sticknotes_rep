package controller;

//import config.mydbConnection;
import java.sql.*;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class registerServlet implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Retrieve form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");

        Connection conn = null;
        PreparedStatement stmt = null;
        ModelAndView modelAndView = new ModelAndView();

        try {
     String URL = "jdbc:mysql://localhost:3306/stickynotesdb";
     String USER = "root";
     String PASSWORD = "";
    
    Class.forName("com.mysql.jdbc.Driver");
            // Establish a database connection
    conn=DriverManager.getConnection(URL, USER, PASSWORD);
            
            if (conn == null) {
                System.err.println("Failed to establish a database connection.");
               
            }
           
            // SQL query to insert data without username and password
            String sql = "INSERT INTO users (Names, email, gender, role_id) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, gender);
            stmt.setInt(4, 1); // Assuming role_id for new users is 1

            // Execute the SQL query
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            // Check if registration was successful
            if (rowsAffected > 0) {
                modelAndView.setViewName("registerSuccess");
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        

            request.getRequestDispatcher("registerForm.htm").include(request, response);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return modelAndView;
    }
}

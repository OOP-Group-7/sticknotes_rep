package controller;

import config.mydbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class loginController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mydbConnection.getConnection1();
            
            // Check if user is a super admin (role_id = 2)
            String sqlAdmin = "SELECT * FROM users WHERE username = ? AND password = ? AND role_id = 2";
            stmt = conn.prepareStatement(sqlAdmin);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // If user is a super admin, redirect to admin dashboard
                 int userId = rs.getInt("userid");

                // Store userId in session
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                ModelAndView modelAndView = new ModelAndView("adminDashboard");
                
                modelAndView.addObject("username", username);
                modelAndView.addObject("userId", rs.getInt("userid"));  // Assuming 'id' is the user ID column
                return modelAndView;
            }

            // Check if user is a normal user (role_id = 1)
            String sqlUser = "SELECT * FROM users WHERE username = ? AND password = ? AND role_id = 1";
            stmt = conn.prepareStatement(sqlUser);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                 int userId = rs.getInt("userid");

                // Store userId in session
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                // If user is a normal user, redirect to user dashboard
                ModelAndView modelAndView = new ModelAndView("dashboard");
                modelAndView.addObject("username", username);
                  // Passing user ID to the dashboard
                return modelAndView;
            }

            // If credentials are invalid, redirect back to the login form with an error message
            ModelAndView modelAndView = new ModelAndView("loginForm");
            modelAndView.addObject("error", "Invalid credentials or you do not have the required permissions.");
            return modelAndView;

        } catch (Exception e) {
            e.printStackTrace();
            // Handle database errors
            ModelAndView modelAndView = new ModelAndView("errorPage");
            modelAndView.addObject("message", "Database connection error.");
            return modelAndView;
        } finally {
            // Close resources
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}

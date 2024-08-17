package controller;

import config.mydbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class controller1 implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Initialize variables
        int userId = 0;
        String title = null;
        String content = null;
        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
        
        try {
            // Retrieve session
            HttpSession session = request.getSession(false);

            // Check if session exists
            if (session != null) {
                // Get userId from session
                Integer sessionUserId = (Integer) session.getAttribute("userId");

                if (sessionUserId != null) {
                    userId = sessionUserId; // Assign userId from session
                } else {
                    // Handle case where userId is not present in session
                    throw new Exception("User not logged in or session expired.");
                }
            } else {
                // Handle case where session is not present
                throw new Exception("Session not found.");
            }

            // Get form parameters
            title = request.getParameter("title");
            content = request.getParameter("content");

            // Validate parameters
            if (title == null || content == null || title.isEmpty() || content.isEmpty()) {
                throw new Exception("Title or content cannot be empty.");
            }

            // Database connection and insertion
            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                conn = mydbConnection.getConnection1();
                String sql = "INSERT INTO stickynotes (userid, title, content, created_at) VALUES (?, ?, ?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, userId);
                stmt.setString(2, title);
                stmt.setString(3, content);
                stmt.setTimestamp(4, createdAt);

                stmt.executeUpdate();

                // Redirect to the dashboard after successful insertion
                return new ModelAndView("redirect:dashboard.htm");

            } catch (Exception e) {
                e.printStackTrace();
                ModelAndView modelAndView = new ModelAndView("errorPage");
                modelAndView.addObject("message", "Failed to create sticky note: " + e.getMessage());
                return modelAndView;
            } finally {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            ModelAndView modelAndView = new ModelAndView("errorPage");
            modelAndView.addObject("message", "Error processing request: " + e.getMessage());
            return modelAndView;
        }
    }
}

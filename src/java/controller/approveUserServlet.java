package controller;

import config.DBConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import model.userModel;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.List;

//@WebServlet("/approveUserServlet")
public class approveUserServlet extends HttpServlet {

    private JdbcTemplate jdbcTemplate;
    userModel s=new userModel();
    List data;
    @Override
    public void init() throws ServletException {
        DBConnection conn = new DBConnection();
        this.jdbcTemplate = new JdbcTemplate(conn.Connect());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user ID from the request
      

        // Query to select user data by ID
        String sql = "SELECT * FROM users WHERE role_id=? AND status=?";
        data = jdbcTemplate.queryForList(sql, s.getRole_id(1),s.getStatus("active"));

        // Check if user data was found
        if (data != null && !data.isEmpty()) {
            // Set the user data as a request attribute
            request.setAttribute("secondList", data);

            // Forward the request to the JSP page for display
            request.getRequestDispatcher("/viewUserList.htm").forward(request, response);
        } else {
            // If no user found, redirect back to the user list with an error message
            request.setAttribute("error", "No user found with the provided ID.");
            request.getRequestDispatcher("/userList.jsp").forward(request, response);
        }
    }
}

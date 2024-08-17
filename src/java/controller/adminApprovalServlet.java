package controller;
import config.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import javax.servlet.annotation.WebServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class adminApprovalServlet {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/adminApprovalServlet")
    public ModelAndView approveUser(@RequestParam("userId") int userId,
                                    @RequestParam("email") String email,
                                    @RequestParam("username") String username,
                                    @RequestParam("password") String password) {
        // Send email with provided username and password
        sendEmail(email, username, password);

        // Update the user's credentials in the database
        updateUserCredentials(userId, username, password);

        // Redirect to the admin dashboard or appropriate page
        return new ModelAndView("redirect:/adminDashboard");
    }

    private void sendEmail(String to, String username, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your Account Credentials");
        message.setText("Dear User,\n\nYour account has been approved. Here are your login credentials:\n\n"
                + "Username: " + username + "\nPassword: " + password + "\n\nPlease change your password after logging in.");
        mailSender.send(message);
    }

    private void updateUserCredentials(int userId, String username, String password) {
        String sql = "UPDATE users SET username = ?, password = ? WHERE userid = ?";
        jdbcTemplate.update(sql, username, password, userId);
    }
}

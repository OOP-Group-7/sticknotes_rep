package controller;

import config.DBConnection;
import model.userModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class userController {

    DBConnection conn = new DBConnection();
    JdbcTemplate jdbctemp = new JdbcTemplate(conn.Connect());
    ModelAndView mv = new ModelAndView();
    List datas;
    
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "approveForm.htm", method = RequestMethod.GET)
    public ModelAndView Lists1(@ModelAttribute("userModel") userModel id) {
        String sql = "select * from users where userid=?";
        datas = this.jdbctemp.queryForList(sql, id.getUserid());

        if (!datas.isEmpty()) {
            mv.addObject("userData", datas.get(0));
        } else {
            mv.addObject("error", "No user found with the provided ID.");
        }

        mv.setViewName("approveForm");
        return mv;
    }
    
    @RequestMapping(value = "approveForm.htm", method = RequestMethod.POST)
    public ModelAndView approveUser(@ModelAttribute("userModel") userModel user) {
        // Extract user details
        int userId = user.getUserid();
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();

        // Update the user's credentials in the database
        updateUserCredentials(userId, username, password);

        // Send email with provided username and password
        sendEmail(email, username, password);

        return new ModelAndView("redirect:/adminDashboard"); // Redirect to the admin dashboard or appropriate page
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
        jdbctemp.update(sql, username, password, userId);
    }
}


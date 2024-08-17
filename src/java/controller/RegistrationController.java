package controller;

import config.DBConnection;
import config.mydbConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import model.userModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.swing.JOptionPane;
import model.noteModel;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
public class RegistrationController {
    DBConnection conn=new DBConnection();
    JdbcTemplate jdbctemp=new JdbcTemplate(conn.Connect());
    ModelAndView mv=new ModelAndView();
    List datas;
@RequestMapping(value="register.htm",method=RequestMethod.GET)
public ModelAndView addUser(){
    mv.addObject(new userModel());
    mv.setViewName("register");
    return mv;
}
@RequestMapping(value="register.htm" , method=RequestMethod.POST)
public ModelAndView addUser(userModel s){
    String sql="insert into users (Names,email,gender,username,password,role_id,status) values(?,?,?,?,?,?,?)";
    this.jdbctemp.update(sql,s.getNames(),s.getEmail(),s.getGender(),s.getUsername(),s.getPassword(),s.getRole_id(1),s.getStatus());
    return new ModelAndView("redirect:/registerSuccess.htm");
    
}
@RequestMapping("viewUserRegistered.htm")
public ModelAndView Lists(userModel s){
    String sql="select * from users where role_id=?";
    datas=this.jdbctemp.queryForList(sql,s.getRole_id(1));
    System.out.println(datas);
    mv.addObject("userList", datas);
    mv.setViewName("viewUserRegistered");
    return mv;
}

@RequestMapping("delete.htm" )
public ModelAndView deleteUser(@RequestParam("id") int id){
    String sql="delete from users where userid=?";
    this.jdbctemp.update(sql,id);
    return new ModelAndView("redirect:/manageUser.htm");
    
}
@RequestMapping("deleteNote.htm" )
public ModelAndView deleteNote(@RequestParam("id") int id){
    String sql="delete from stickynotes where id=?";
    this.jdbctemp.update(sql,id);
    return new ModelAndView("redirect:/viewNote.htm");
    
}

   @RequestMapping(value="register1.htm",method=RequestMethod.GET)
public ModelAndView approveUser(){
    mv.addObject(new userModel());
    mv.setViewName("register1");
    return mv;
}
@RequestMapping(value="register1.htm" , method=RequestMethod.POST)
public ModelAndView approveUser(userModel s){
    String sql="insert into users (Names,email,gender,username,password,role_id) values(?,?,?,?,?,?)";
    this.jdbctemp.update(sql,s.getNames(),s.getEmail(),s.getGender(),s.getUsername(),s.getPassword(),s.getRole_id(1));
    return new ModelAndView("redirect:/registerSuccess.htm");
    
}

@RequestMapping(value="approveForm.htm" , method=RequestMethod.GET)
public ModelAndView updateUser(@RequestParam("id") int id){
    String sql="select * from users where userid=?";
    datas=this.jdbctemp.queryForList(sql,id);
    mv.addObject("userData", datas);
    mv.setViewName("approveForm");
    return mv;
}

@RequestMapping(value="approveForm.htm" , method=RequestMethod.POST)
public ModelAndView approveData(userModel s){
    String sql="update users set email=?,username=?,password=?,status=? where userid=?";
    this.jdbctemp.update(sql,s.getEmail(),s.getUsername(),s.getPassword(),s.getStatus(),s.getUserid());
    
    return new ModelAndView("redirect:/adminDashboard.htm");
    
}

 @RequestMapping(value="limitForm.htm" , method=RequestMethod.GET)
public ModelAndView setLimit(@RequestParam("id") int id){
    String sql="select * from users where userid=?";
    datas=this.jdbctemp.queryForList(sql,id);
    mv.addObject("limitation", datas);
    mv.setViewName("limitForm");
    return mv;
}
    @RequestMapping(value="limitForm.htm" , method=RequestMethod.POST)
    public ModelAndView setStickyNoteLimit(userModel s) {
        String sql = "UPDATE users SET note_limit=? WHERE userid=?";
        this.jdbctemp.update(sql, s.getNote_limit(),s.getUserid());

        return new ModelAndView("redirect:/adminDashboard.htm");
    }
    
@RequestMapping(value = "/dashboard.htm", method = RequestMethod.GET)
public ModelAndView showDashboard(HttpServletRequest request,userModel s) {
    HttpSession session = request.getSession(false);
    if (session == null) {
        return new ModelAndView("errorPage", "message", "Session not found.");
    }

     int userId = (int) session.getAttribute("userId");
        
        String sql = "SELECT * FROM stickynotes WHERE userid = ?";
        List<Map<String, Object>> datas = this.jdbctemp.queryForList(sql, userId);
        System.out.println(datas);

    ModelAndView modelAndView = new ModelAndView("dashboard");
    modelAndView.addObject("noteList", datas);
    modelAndView.addObject("userId", userId);
    return modelAndView;
}

   @PostMapping("/dashboard.htm")
    public ModelAndView createStickyNote(HttpServletRequest request, noteModel c) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ModelAndView("errorPage", "message", "Session not found.");
        }

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return new ModelAndView("errorPage", "message", "User not logged in or session expired.");
        }

        // Check the sticky note limit
        String countSql = "SELECT COUNT(*) FROM stickynotes WHERE userid = ?";
        int stickyNoteCount = jdbctemp.queryForObject(countSql, new Object[]{userId}, Integer.class);

        String limitSql = "SELECT note_limit FROM users WHERE userid = ?";
        int stickyNoteLimit = jdbctemp.queryForObject(limitSql, new Object[]{userId}, Integer.class);

        if (stickyNoteCount >= stickyNoteLimit) {
            
            return new ModelAndView("errorPage1", "message", "You have reached your sticky note limit.");
        }

        // If limit not reached, insert the new sticky note
        String insertSql = "INSERT INTO stickynotes (userid, title, content, created_at) VALUES (?, ?, ?, ?)";
        jdbctemp.update(insertSql, userId, c.getTitle(), c.getContent(), c.getCreated_at());

        return new ModelAndView("redirect:/dashboard.htm");
    }
    
   @RequestMapping("viewNote.htm")
    public ModelAndView listNotes(HttpSession session) {
        // Get the logged-in user's ID from the session
        int userId = (int) session.getAttribute("userId");
        
        String sql = "SELECT * FROM stickynotes WHERE userid = ?";
        List<Map<String, Object>> datas = this.jdbctemp.queryForList(sql, userId);
        System.out.println(datas);

        ModelAndView mv = new ModelAndView();
        mv.addObject("noteList", datas);
        mv.setViewName("viewNote");

        return mv;
    }
    
    


@RequestMapping(value="editNote.htm" , method=RequestMethod.POST)
public ModelAndView editNote(noteModel s){
    Timestamp updated_at = Timestamp.valueOf(LocalDateTime.now());
    String sql="update stickynotes set title=?,content=?,update_at=? where id=?";
    this.jdbctemp.update(sql,s.getTitle(),s.getContent(),updated_at,s.getId());
    
    return new ModelAndView("redirect:/viewNote.htm");
    
}

 @RequestMapping(value="editNote.htm" , method=RequestMethod.GET)
public ModelAndView updateNote(@RequestParam("id") int id){
    String sql="select * from stickynotes where id=?";
    datas=this.jdbctemp.queryForList(sql,id);
    mv.addObject("noteContent", datas);
    mv.setViewName("editNote");
    return mv;
}
}


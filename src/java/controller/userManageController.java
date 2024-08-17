/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.swing.JOptionPane;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class userManageController {
    DBConnection conn=new DBConnection();
    JdbcTemplate jdbctemp=new JdbcTemplate(conn.Connect());
    ModelAndView mv=new ModelAndView();
    List dt;
    
    
public ModelAndView ListsUser(userModel d){
    String sql="select * from users where role_id=? AND status=?";
    dt=this.jdbctemp.queryForList(sql,d.getRole_id(1),d.getStatus("active"));
    
    mv.addObject("secondList", dt);
    mv.setViewName("ViewUserList");
    return mv;
}
}

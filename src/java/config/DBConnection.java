/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DBConnection {
    public DriverManagerDataSource Connect(){
        DriverManagerDataSource dt=new DriverManagerDataSource();
        dt.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dt.setUrl("jdbc:mysql://localhost:3306/stickynotesdb");
        dt.setUsername("root");
        dt.setPassword("");
        
        return dt;
    }
}

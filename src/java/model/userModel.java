package model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class userModel {
    private int userid;
    private String Names;
    private String email;
    private String gender;
    private String username;
    private String password;
    private int role_id;
    private String status;
    private int note_limit;
    public static int getRole_id(int id){
       return id; 
    }
    public static String getStatus(String str){
       return str; 
    }
}

package model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class noteModel {
    private int id;
    private int userid;
    private String title;
    private String content;
    private String created_at;
    private String updated_at;
   ;
   
}

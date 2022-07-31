package com.huang.pojo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int u_id;
    private String u_name;
    private String u_password;
    private String u_email;
    private String u_sex;
    private String u_phone;
    private String u_contactInfo;
    private String u_school;
    private String u_major;
    private String u_personalInfo;
    private String u_identity;


}

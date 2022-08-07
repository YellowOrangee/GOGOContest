package com.huang.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private int n_id;
    private String n_name;
    private String n_type;
    private String n_content;
    private String n_status;
}

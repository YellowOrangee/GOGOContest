package com.huang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

    private int r_id;
    private int r_vid;
    private String r_name;
    private Date r_time;
    private String r_content;

}

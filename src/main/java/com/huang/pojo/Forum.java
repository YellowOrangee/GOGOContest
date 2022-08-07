package com.huang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forum {

    private int f_id;
    private String f_title;
    private String f_content;
    private Date f_time;
    private int f_replayTime;
    private int f_clickTime;
}

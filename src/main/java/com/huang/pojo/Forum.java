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
    private String t_captain;
    private String t_name;
    private String t_ctime;
    private String t_demand;
    private int t_count;
    private String t_contact;
    private String t_public;
    private String t_status;
    private String t_type;
    private String t_gname;
    private int f_replayTime;
    private int f_clickTime;
}

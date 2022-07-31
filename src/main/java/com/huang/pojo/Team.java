package com.huang.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    private int tm_id;
    private String tm_captain;
    private Date tm_ctime;
    private String tm_demand;
    private int count;
    private String tm_status;
    private String tm_type;

}

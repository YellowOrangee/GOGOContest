package com.huang.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForumVo {

    private String sTime;
    private String eTime;
    private String level;  // 级别
    private String demand; // 要求(学历,参赛经历等)
    private String gName; // 要求(队伍参赛名)
}

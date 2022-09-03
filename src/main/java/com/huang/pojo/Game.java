package com.huang.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private Integer g_id;  //大赛id
    private String g_title;  //大赛名称
    private String g_body;  //大赛概要
    private String g_sponsor;  //大赛主办方
    private String g_type;  //大赛类型（工科/商科/...）
    private String g_level;  //大赛级别（区/校级...）
    private String g_link;  //大赛链接
    private String g_sTime;  //大赛开始时间
    private String g_eTime;  //大赛结束时间
    private String g_signup_sTime;  //大赛报名开始时间
    private String g_signup_eTime;  //大赛报名结束时间
    private String g_image;   //图片
    private String g_file;  //文件
    private MultipartFile file;   //文件流
    private Integer uid;  //上传该比赛用户id
}

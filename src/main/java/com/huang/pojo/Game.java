package com.huang.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private int g_id;
    private String g_title;
    private String g_body;
    private String g_type;
    private String g_link;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String g_sTime;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String g_eTime;
    private int uid;

//    public Game() {
//    }
//
//    public Game(int g_id, String g_title, String g_body, String g_type, String g_link, Date g_sTime, Date g_eTime, int uid) {
//        this.g_id = g_id;
//        this.g_title = g_title;
//        this.g_body = g_body;
//        this.g_type = g_type;
//        this.g_link = g_link;
//        this.g_sTime = g_sTime;
//        this.g_eTime = g_eTime;
//        this.uid = uid;
//    }
//
//    public Game(String g_title, String g_body, String g_type, String g_link, Date g_sTime, Date g_eTime, int uid) {
//        this.g_title = g_title;
//        this.g_body = g_body;
//        this.g_type = g_type;
//        this.g_link = g_link;
//        this.g_sTime = g_sTime;
//        this.g_eTime = g_eTime;
//        this.uid = uid;
//    }
//
//    public int getG_id() {
//        return g_id;
//    }
//
//    public void setG_id(int g_id) {
//        this.g_id = g_id;
//    }
//
//    public String getG_title() {
//        return g_title;
//    }
//
//    public void setG_title(String g_title) {
//        this.g_title = g_title;
//    }
//
//    public String getG_body() {
//        return g_body;
//    }
//
//    public void setG_body(String g_body) {
//        this.g_body = g_body;
//    }
//
//    public String getG_type() {
//        return g_type;
//    }
//
//    public void setG_type(String g_type) {
//        this.g_type = g_type;
//    }
//
//    public String getG_link() {
//        return g_link;
//    }
//
//    public void setG_link(String g_link) {
//        this.g_link = g_link;
//    }
//
//    public Date getG_sTime() {
//        return g_sTime;
//    }
//
//    public void setG_sTime(Date g_sTime) {
//        this.g_sTime = g_sTime;
//    }
//
//    public Date getG_eTime() {
//        return g_eTime;
//    }
//
//    public void setG_eTime(Date g_eTime) {
//        this.g_eTime = g_eTime;
//    }
//
//    public int getUid() {
//        return uid;
//    }
//
//    public void setUid(int uid) {
//        this.uid = uid;
//    }
//
//    @Override
//    public String toString() {
//        return "Game{" +
//                "g_id=" + g_id +
//                ", g_title='" + g_title + '\'' +
//                ", g_body='" + g_body + '\'' +
//                ", g_type='" + g_type + '\'' +
//                ", g_link='" + g_link + '\'' +
//                ", g_sTime=" + g_sTime +
//                ", g_eTime=" + g_eTime +
//                ", uid=" + uid +
//                '}';
//    }
}

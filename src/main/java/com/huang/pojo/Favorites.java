package com.huang.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorites {
    private int fa_id;  //id
    private int fa_uid;  //用户id
    private int fa_gid;  //比赛id
    private String fa_gtitle;  //比赛标题
    private String fa_gbody;  //比赛详情
    private String fa_time;  //收藏时间
}

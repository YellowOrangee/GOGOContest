package com.huang.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorites {

    private int fa_id;
    private int fa_uid;
    private int fa_gid;
    private String fa_time;
}

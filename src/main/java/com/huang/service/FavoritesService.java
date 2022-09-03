package com.huang.service;
import com.huang.pojo.Favorites;

import java.util.List;

public interface FavoritesService {
    int collect(Favorites favorites);   //收藏

    int judgeCollect(Integer gid,Integer uid);//判断比赛是否被该用户收藏

    int cancel(Integer fa_uid, Integer fa_gid);    //取消收藏
    int countcollect(Integer fa_gid);   //收藏次数
    List<Favorites> mycollect(Integer fa_uid);   //用户收藏夹
    List<Favorites> whocollect(Integer fa_gid);   //都有谁收藏了此比赛
}

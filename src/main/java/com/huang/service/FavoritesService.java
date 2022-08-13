package com.huang.service;


import com.huang.pojo.Favorites;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoritesService {

    //收藏
    int addCollect(Favorites favorites);

    Integer judgeCollect(int gid,int uid);


    //取消收藏
    int cancelCollect(Integer fa_uid, Integer fa_gid);
    //收藏次数
    int collectionTimes(Integer fa_gid);
    //用户收藏夹
    List<Favorites> myCollect(Integer fa_uid);
    //都有谁收藏了此比赛
    List<Favorites> whoCollect(Integer fa_gid);
}

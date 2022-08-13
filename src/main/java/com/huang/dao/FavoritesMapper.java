package com.huang.dao;

import com.huang.pojo.Favorites;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoritesMapper {
    //收藏
    int addCollect(Favorites favorites);

//    根据用户id与大赛id 判断该用户是否已收藏本大赛信息
    Integer judgeCollect(@Param("gid") int gid,@Param("uid") int uid);


    //取消收藏
    int cancelCollect(@Param("fa_uid") Integer fa_uid, @Param("fa_gid") Integer fa_gid);
    //收藏次数
    int collectionTimes(@Param("fa_gid") Integer fa_gid);
    //用户收藏夹
    List<Favorites> myCollect(@Param("fa_uid") Integer fa_uid);
    //都有谁收藏了此比赛
    List<Favorites> whoCollect(@Param("fa_gid") Integer fa_gid);
}

package com.huang.dao;

import com.huang.pojo.Favorites;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoritesMapper {
    int collect(Favorites favorites);   //收藏
    int cancel(
            @Param("fa_uid")
                    Integer fa_uid,
            @Param("fa_gid")
                    Integer fa_gid
    );

    //判断用户是否已收藏该大赛信息
    int judgeCollect(@Param("gid") Integer gid,@Param("uid") Integer uid);
    //取消收藏
    int countcollect(Integer fa_gid);   //收藏次数
    List<Favorites> mycollect(Integer fa_uid);   //用户收藏的比赛
    List<Favorites> whocollect(Integer fa_gid);   //都有谁收藏了此比赛
}

package com.huang.dao;

import com.huang.pojo.Game;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GameMapper {

    int creatGame(Game game);

//    删除大赛信息
    int deleteGame(Integer g_id);

//    通过gid查询uid
    int queryUidByGid(@Param("g_id") Integer id);

//    查询管理员id
    int queryAdIdByGid(@Param("g_id") Integer g_id);

    Game queryGameByGid(@Param("g_id") Integer g_id);

    int updateGame(Game game);

    List<Game> showAllGame();
}

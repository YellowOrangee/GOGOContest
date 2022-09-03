package com.huang.dao;

import com.huang.pojo.Game;
import com.huang.vo.GameConditionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GameMapper {
    int creatGame(Game game);

    List<Game> showCollectGame(Integer g_id);
    //    删除大赛信息
    int deleteGame(@Param("g_id") Integer g_id);

    int updateGame(Game game);

    //    通过gid查询uid
    int queryUidByGid(@Param("g_id") Integer id);

    //    查询管理员id
    int queryAdIdByGid(@Param("g_id") Integer g_id);

    Game queryGameByGid(@Param("g_id") Integer g_id);

    List<Game> showAllGame();

    List<Game> queryGameBySearch(@Param("name") String name);   //通过大赛名模糊查询

    List<Game> queryGameByCondition(GameConditionVo gameConditionVo);   //通过下拉表单条件查询

    //通过uid查询gid，查询用户发布过的比赛
    List<Integer> queryGidByUid(Integer uid);

    List<Game> queryNameAndSponsor (@Param("keyWords") String keyWords);

    List<Game> getGidByNameAndSponsor ();
}

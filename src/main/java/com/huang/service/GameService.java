package com.huang.service;

import com.github.pagehelper.PageInfo;
import com.huang.pojo.Game;
import com.huang.vo.GameConditionVo;

import java.util.List;
public interface GameService {
    int creatGame(Game game);

    int deleteGame(Integer g_id);

    Game queryGameByGid(Integer g_id);

    int queryAdIdByGid(Integer g_id);

    int updateGame(Game game);

    int queryUidByGid( Integer id);

    PageInfo<Game> showAllGameS(Integer pageNum);

    PageInfo<Game> queryGameBySearch(String name,Integer pageNum);  //通过大赛名模糊查询


    PageInfo<Game> queryGameByCondition(GameConditionVo gameConditionVo,Integer pageNum);

    List<Integer> queryGidByUid(Integer uid);

    List<Game> queryNameAndSponsor (String keyWords);

}

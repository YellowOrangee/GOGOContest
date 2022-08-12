package com.huang.service;

import com.huang.pojo.Game;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GameService {
    int creatGame(Game game);

    int deleteGame(Integer g_id);

    Game queryGameByGid(Integer g_id);

    int queryAdIdByGid(Integer g_id);

    int updateGame(Game game);

    int queryUidByGid( Integer id);

    List<Game> showAllGame();
}

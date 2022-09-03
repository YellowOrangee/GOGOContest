package com.huang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huang.dao.GameMapper;
import com.huang.pojo.Game;
import com.huang.vo.GameConditionVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class GameServiceImpl implements GameService {
    private GameMapper gameMapper;

    public void setGameMapper(GameMapper gameMapper) {
        this.gameMapper = gameMapper;
    }

    @Override
    public int creatGame(Game game) {
        return gameMapper.creatGame(game);
    }

    @Override
    public int deleteGame(Integer g_id) {
        return gameMapper.deleteGame(g_id);
    }

    @Override
    public PageInfo<Game> showAllGameS(Integer pageNum) {
        PageHelper.startPage(pageNum,4);
        List<Game> games = gameMapper.showAllGame();
        PageInfo page=new PageInfo(games,5);
        return page;
    }


    @Override
    public int queryAdIdByGid(Integer g_id) {
        return gameMapper.queryAdIdByGid(g_id);
    }

    @Override
    public int updateGame(Game game) {
        return this.gameMapper.updateGame(game);
    }

    @Override
    public Game queryGameByGid(Integer g_id) {
        return gameMapper.queryGameByGid(g_id);
    }

    @Override
    public int queryUidByGid(Integer id) {
        return gameMapper.queryUidByGid(id);
    }

    @Override
    public PageInfo<Game> queryGameBySearch(String name,Integer pageNum) {
        PageHelper.startPage(pageNum,4);

        List<Game> games = gameMapper.queryGameBySearch(name);

        PageInfo page=new PageInfo(games,5);


        return page;
    }

    @Override
    public PageInfo<Game> queryGameByCondition(GameConditionVo gameConditionVo,Integer pageNum) {
        PageHelper.startPage(pageNum,4);

        List<Game> games = gameMapper.queryGameByCondition(gameConditionVo);

        PageInfo page=new PageInfo(games,5);
        return page;
    }

    @Override
    public List<Integer> queryGidByUid(Integer uid) {
        return gameMapper.queryGidByUid(uid);
    }

    @Override
    public List<Game> queryNameAndSponsor(String keyWords) {
        return this.gameMapper.queryNameAndSponsor(keyWords);
    }

}

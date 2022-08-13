package com.huang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huang.dao.GameMapper;
import com.huang.pojo.Forum;
import com.huang.pojo.Game;
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
    public Game queryGameByGid(Integer g_id) {
        return this.gameMapper.queryGameByGid(g_id);
    }

    @Override
    public int queryAdIdByGid(Integer g_id) {
        return this.gameMapper.queryAdIdByGid(g_id);
    }

    @Override
    public int updateGame(Game game) {
        return gameMapper.updateGame(game);
    }

    @Override
    public int queryUidByGid(Integer id) {
        return gameMapper.queryUidByGid(id);
    }

    @Override
    public PageInfo<Game> showAllGameS(Integer pageNum) {
        //        开启分页功能
        PageHelper.startPage(pageNum,4);
//        获取员工信息
        List<Game> list= gameMapper.showAllGame();
//        获取分页相关数据
        PageInfo<Game> page= new PageInfo<>(list,5);

        return page;
    }


}

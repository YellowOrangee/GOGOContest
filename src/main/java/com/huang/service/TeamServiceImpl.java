package com.huang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huang.dao.TeamMapper;
import com.huang.pojo.Team;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class TeamServiceImpl implements TeamService{


    private TeamMapper teamMapper;


    public void setTeamMapper(TeamMapper teamMapper) {
        this.teamMapper = teamMapper;
    }

    @Override
    public int addTeam(Map<String, Object> map) {
        return this.teamMapper.addTeam(map);
    }

    @Override
    public int addMember(Map<String, Object> map) {
        return teamMapper.addMember(map);
    }

    @Override
    public String judgeIdentity(Map<String, Object> map) {
        return this.teamMapper.judgeIdentity(map);
    }

    @Override
    public Team showTeamById(int id) {
        return this.teamMapper.showTeamById(id);
    }

    @Override
    public int updateTeam(Team team) {
        return this.teamMapper.updateTeam(team);
    }

    @Override
    public int disbandTeamS(int id) {
        return this.teamMapper.disbandTeam(id);
    }

    @Override
    public PageInfo<Team> showAllTeam(Integer pageNum) {
//        开启分页功能
        PageHelper.startPage(pageNum,4);
//        获取队伍信息
        List<Team> list =teamMapper.showAllTeam();
//        获取分页相关数据 navigatePages为导航页数量 1 2 (3) 4 5
        PageInfo<Team> page = new PageInfo<>(list,5);
        return page;
    }
}

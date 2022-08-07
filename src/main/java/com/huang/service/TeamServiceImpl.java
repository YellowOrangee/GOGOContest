package com.huang.service;

import com.huang.dao.TeamMapper;
import com.huang.pojo.Team;

import java.util.Map;

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
    public int disbandTeamS(int id) {
        return this.teamMapper.disbandTeam(id);
    }
}

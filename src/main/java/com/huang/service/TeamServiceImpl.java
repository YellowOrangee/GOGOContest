package com.huang.service;

import com.huang.dao.TeamMapper;

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
}

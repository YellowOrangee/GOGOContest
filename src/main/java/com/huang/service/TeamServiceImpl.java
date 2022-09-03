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
    public int addMember(Map<String, Object> map) {
        return teamMapper.addMember(map);
    }

    @Override
    public int judgeFull(Integer tid) {
        return this.teamMapper.judgeFull(tid);
    }

    @Override
    public int judgeIdentity(int fid) {
        return this.teamMapper.judgeIdentity(fid);
    }


    @Override
    public int disbandTeam(int id) {
        return this.teamMapper.disbandTeam(id);
    }

    @Override
    public int deleteAllMembers(int id) {
        return this.teamMapper.deleteAllMembers(id);
    }

    @Override
    public int quiteTeam (Map<String, Object> map) {
        return this.teamMapper.quiteTeam(map);
    }

}

package com.huang.service;

import com.github.pagehelper.PageInfo;
import com.huang.pojo.Team;

import java.util.Map;

public interface TeamService {

    //    创建队伍
    int addTeam(Map<String,Object> map);

    //    添加队员
    int addMember(Map<String,Object> map);

    //    根据队伍ID查询队长名
    String judgeIdentity(Map<String,Object> map);

    //    展示队伍信息
    Team showTeamById(int id);

    //    提交修改
    int updateTeam(Team team);

    //    解散队伍
    int disbandTeamS(int id);

    PageInfo<Team> showAllTeam(Integer pageNum);

}

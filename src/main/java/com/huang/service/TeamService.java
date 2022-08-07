package com.huang.service;

import com.huang.pojo.Team;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TeamService {

    //    创建队伍
    int addTeam(Map<String,Object> map);

    //    初始化成员表(添加新建队伍的Id和队长名)
    int addMember(Map<String,Object> map);

    //    根据队伍ID查询队长名
    String judgeIdentity(Map<String,Object> map);

    //    展示队伍信息
    Team showTeamById(int id);

//    解散队伍
    int disbandTeamS(int id);

}

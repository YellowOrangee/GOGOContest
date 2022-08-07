package com.huang.dao;

import com.huang.pojo.Team;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TeamMapper {

    //    创建队伍
    int addTeam(Map<String,Object> map);

    //    添加成员
    int addMember(Map<String,Object> map);

    //    判断修改队伍请求发起人是否为该队伍队员
    String judgeIdentity(Map<String,Object> map);

    //    显示指定队伍信息(用于修改队伍信息)
    Team showTeamById(@Param("id") int id);

//    解散队伍
    int disbandTeam(@Param("id") int id);
}

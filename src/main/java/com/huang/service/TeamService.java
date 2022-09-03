package com.huang.service;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TeamService {

    //    添加成员
    int addMember(Map<String,Object> map);

    //判断队伍是否满员
    int judgeFull( Integer tid);

    //    判断修改队伍请求发起人是否为该队伍队员
    int judgeIdentity( int fid);

    //    解散队伍
    int disbandTeam(int id);

    //    解散队伍同时清除所有成员
    int deleteAllMembers(int id);

    //退出队伍
    int quiteTeam(  Map<String, Object> map);

}

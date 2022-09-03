package com.huang.dao;

import com.huang.pojo.Team;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeamMapper {

    //    添加成员
    int addMember(Map<String,Object> map);

    //查询队伍队员
    int judgeFull(@Param("fid") Integer fid);

    //    判断修改队伍请求发起人是否为该队伍队长
    int judgeIdentity(@Param("fid") int fid);

    //    解散队伍
    int disbandTeam(@Param("id") int id);

    //    解散队伍同时清除所有成员
    int deleteAllMembers(@Param("id") int id);

    //退出队伍
    int quiteTeam( Map<String, Object> map);

}

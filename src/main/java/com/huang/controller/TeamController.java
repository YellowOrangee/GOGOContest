package com.huang.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.huang.pojo.Forum;

import com.huang.service.TeamService;
import com.huang.service.teamForumService;
import com.huang.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;


@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    @Qualifier("TeamServiceImpl")
    private TeamService teamService;

    @Autowired
    @Qualifier("teamForumServiceImpl")
    private teamForumService teamForumService;

    //    添加小队成员
    @RequestMapping(value="/addMembers",method = RequestMethod.GET)
    public Map<String,Object> addMembers(String memberName,Integer fid){

//        设置返回数据
        Map<String,Object> result=new HashMap();

//        通过队伍id查询队伍信息
        Forum forum = teamForumService.queryForumById(fid);
        int count = forum.getT_count();
        String captain = forum.getT_captain();
        String name = forum.getT_name();

//        获取队伍最大人数(发布招募信息时设置的队伍需求人数为队员数,不包括队长)
        int maxNum = teamService.judgeFull(fid);
        System.out.println(maxNum);

        if((count-1)>=maxNum){
//        封装参数
            Map<String,Object> map=new HashMap<>();
            map.put("memberName",memberName);
            map.put("tid",fid);
            map.put("name",name);
            map.put("captain",captain);

            int i = teamService.addMember(map);
            if(i>0){
                result.put("state",200);
                result.put("success",true);
            }else{
                result.put("state",500);
                result.put("msg","服务器繁忙,请稍后再试");
            }
        }else{
            result.put("state",403);
            result.put("msg","该队伍已满员,无法再添加队员!");
        }
        return result;
    }


    //  1.判断请求发起者是否为队长,非队长无资格修改队伍信息
    @RequestMapping(value="/judgeIdentity",method = RequestMethod.POST)
    public Map<String, Object> judgeIdentity (int fid , HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {  //传入队伍ID

//        存储返回结果
        Map<String, Object> result = new HashMap<>();

        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int uid = Integer.parseInt(verify.getClaim("id").asString());

        int captainId = teamService.judgeIdentity(fid);

//        判断是否为队长
        if(captainId==uid){
            result.put("state",200);
            result.put("msg","身份验证成功!");
        }else{
            result.put("state",403); //拒绝该请求
            result.put("msg","抱歉,只有队长才有此权限!");
        }
        return result;
    }

    //    解散队伍 传入被解散队伍的ID(需要验证队长身份)
    @RequestMapping("/disbandTeam")
    public  Map<String,Object> updateTeam(int id){
        Map<String,Object> result=new HashMap<>();
        try {
            teamService.disbandTeam(id);
            teamService.deleteAllMembers(id);
            result.put("state",200);
            result.put("msg","解散队伍成功");
        }catch (Exception e) {
            e.printStackTrace();
            result.put("state", 500);
            result.put("msg", "操作失败,请稍后再试");
        }
        return result;
    }


//    退出队伍
    @RequestMapping(value ="/quitTeam",method = RequestMethod.POST)
    public Map<String, Object> quitTeam (Integer fid,HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int mid = Integer.parseInt(verify.getClaim("id").asString());

        map.put("mid",mid);
        map.put("fid",fid);
        try {
            teamService.quiteTeam(map);
            result.put("state",200);
            result.put("msg","退队成功!");
        }catch (Exception e){
            e.printStackTrace();
            result.put("state",500);
            result.put("msg","操作失败,请稍后再试~");
        }
        return result;
    }

}


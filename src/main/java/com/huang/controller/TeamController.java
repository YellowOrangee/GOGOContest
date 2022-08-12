package com.huang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.huang.pojo.Team;
import com.huang.pojo.User;
import com.huang.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    @Qualifier("TeamServiceImpl")
    private TeamService teamService;



    //    创建队伍
    @RequestMapping(value="/creatTeam",method = RequestMethod.POST)
    public Map<String,Object> addTeam(String name,String demand, int count, String type,String tPublic,HttpSession session){

//        封装参数
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> result=new HashMap();

//        获取系统时间
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String time = dateFormat.format(date);


//        获取当前用户的姓名(队长名)
        User user = (User) session.getAttribute("currentUser");
        String captain = user.getU_name();

        map.put("name",name);  //队伍名
        map.put("captain",captain); //队长名
        map.put("demand",demand); //需求描述
        map.put("count",count); //队伍人数
        map.put("ctime",time); //创建时间
        map.put("type",type); //队伍类型(参赛类型等)
        map.put("tPublic",tPublic); //队伍是否公开

//        添加队伍
        int i = teamService.addTeam(map);
        if(i>0){

            result.put("success",true);
        }else{
            result.put("success",false);
        }
        return result;
    }



    //    添加小队成员                   待完善!!!!!!!
    @RequestMapping(value="/addMembers",method = RequestMethod.GET)
    public Map<String,Object> addMembers(String memberName,Integer tid){

//        设置返回数据
        Map<String,Object> result=new HashMap();

//        封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("memberName",memberName);
        map.put("tid",tid);

        int i = teamService.addMember(map);
        if(i>0){
            result.put("success",true);
        }else{
            result.put("success",false);
        }
        return result;
    }


    //  1.判断请求发起者是否为队长,非队长无资格修改队伍信息
    @RequestMapping(value="/judgeIdentity",method = RequestMethod.POST)
    public Map<String, Object> judgeIdentity (int id , HttpSession session){  //传入队伍ID

//        存储返回结果
        Map<String, Object> result = new HashMap<>();

        Map<String,Object> map = new HashMap<>();
//        判断请求发送者是否为队长(不是队长不能修改队伍信息)
        User user = (User) session.getAttribute("currentUser");
        String name=user.getU_name();



        map.put("id",id);
        map.put("name",name);
        String identity = teamService.judgeIdentity(map);

//        判断是否为队长
        if(identity.equals(name)){
            session.setAttribute("id",id);
            result.put("session",true);  //如果是队长,则可以路由跳转到修改页面
        }else{
            result.put("msg","抱歉,只有队长才有此权限!");
        }
        return result;
    }


    //    展示队伍信息
    @RequestMapping(value="/showTeamById",method= RequestMethod.GET,produces = {"text/plain;charset=UTF-8"})
    public String showTeamInfo(HttpSession session){
        int id = (int) session.getAttribute("id");
        List  list=new ArrayList();
        list.add(teamService.showTeamById(id));
        return JSON.toJSONString(list, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
    }

    //    解散队伍 传入被解散队伍的ID
    @RequestMapping("/disbandTeam")
    public  Map<String,Object> updateTeam(int id){
        Map<String,Object> result=new HashMap<>();

        int i = teamService.disbandTeamS(id);
        if(i>0){
            result.put("success",true);
        }else{
            result.put("success",false);
        }
        return result;
    }


    //    修改队伍信息:在判断用户为该队伍队长身份后,跳转到修改页面(此页面调用setTeamInfo方法展示队伍信息,
//    修改完成后,表单提交到此方法,修改队伍信息)


    @RequestMapping(value ="/updateTeam",method = RequestMethod.POST)
    public Map<String, Object> updateTeam (Team team){
        Map<String, Object> result = new HashMap<>();

        int resultNum = teamService.updateTeam(team);
        if(resultNum>0){
            result.put("success",true);
        }else{
            result.put("success",false);
        }
        return result;
    }

    //    退出队伍
//    @RequestMapping(value ="/quitTeam",method = RequestMethod.POST)
//    public Map<String, Object> quitTeam (Integer id,HttpSession session) {
//        Map<String, Object> result = new HashMap<>();
//
//        User user = (User) session.getAttribute("currentUser");
//        String name=user.getU_name();
//        return result;
//    }

}


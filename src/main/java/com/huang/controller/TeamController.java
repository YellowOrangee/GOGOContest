package com.huang.controller;

import com.huang.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    @Qualifier("TeamServiceImpl")
    private TeamService teamService;


//    创建队伍
    @RequestMapping("/creatTeam")
    public Map addTeam(String captain, String demand, int count, String status, String type){

//        封装参数
        Map<String,Object> map=new HashMap<>();

        Map map1=new HashMap();


//        使用map集合封装参数,key与SQL语句中传入的参数名相同

//        获取系统时间
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String time = dateFormat.format(date);

        map.put("captain",captain);
        map.put("demand",demand);
        map.put("count",count);
        map.put("ctime",time);
        map.put("status",status);
        map.put("type",type);

//        添加队伍
        int i = teamService.addTeam(map);
        if(i>0){
             map1.put("success",true);
        }else{
            map1.put("success",false);
        }
        return map1;
    }

//    添加小队成员
}

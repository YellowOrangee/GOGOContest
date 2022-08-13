package com.huang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.huang.pojo.*;
import com.huang.service.TeamService;
import com.huang.service.teamForumService;
import com.huang.vo.ForumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value="/teamForum")
public class teamForumController {

    @Autowired
    @Qualifier("TeamServiceImpl")
    private TeamService teamService;

    @Autowired
    @Qualifier("teamForumServiceImpl")
    private teamForumService teamForumService;


    //    展示招募贴(完全展示)
    @RequestMapping(value="/showAllForum/page",method = RequestMethod.GET)
    public PageInfo<Forum> showAllForum(Integer pageNum){

        PageInfo<Forum> page = teamForumService.showForumS(pageNum);
        return page;
    }

    //    根据搜索框输入关键词检索招募信息
    @RequestMapping(value="/showForumBySearch/page",method = RequestMethod.GET)
    public PageInfo<Forum> showForumBySearch(String keyWords,Integer pageNum){
        PageInfo<Forum> page = teamForumService.showForumBySearchS(keyWords,pageNum);
        return page;
    }

//    多条件综合查询展示招募信息
    @RequestMapping(value="/showForumByCondition/page",method = RequestMethod.GET)
    public PageInfo<Forum> showForumByCondition(ForumVo forumVo, Integer pageNum){

        PageInfo<Forum> page = teamForumService.showForumByConditionS(forumVo,pageNum);
        return page;
    }


////    发布招募信息(已创建队伍)
////    跳转到发布页面
//    @RequestMapping(value="/toPublishRecruitmentInfo",method= RequestMethod.POST)
//    public Map<String,Object> toPublishRecruitmentInfo(int id , HttpSession session){
////        存储返回结果
//        Map<String, Object> result = new HashMap<>();
//        Map<String,Object> map = new HashMap<>();
////        判断请求发送者是否为队长(不是队长不能发布招募信息)
//        User user = (User) session.getAttribute("currentUser");
//        String name=user.getU_name();
//
//        map.put("id",id);
//        map.put("name",name);
//        String identity = teamService.judgeIdentity(map);
//
////        判断是否为队长
//        if(identity.equals(name)){
//            session.setAttribute("id",id);
//            result.put("session",true);  //如果是队长,则可以路由跳转到发布招募信息页面
//        }else{
//            result.put("msg","抱歉,只有队长才有此权限!");
//        }
//        return result;
//    }


//    展示队伍信息
    @RequestMapping(value="/showTeamByCaptain",method= RequestMethod.GET,produces = {"text/plain;charset=UTF-8"})
    public String showTeamById(HttpSession session){

        User user = (User) session.getAttribute("currentUser");
        String captain = user.getU_name();

        List<Forum> list = teamForumService.showTeamByCaptainS(captain);
        return JSON.toJSONString(list, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
    }


    @RequestMapping(value="/PublishRecruitmentInfo",method= RequestMethod.POST)
    public Map<String,Object> publishRecruitmentInfo(Forum forum){
        Map<String, Object> result = new HashMap<>();

        Date date=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String time = dateFormat.format(date);

        forum.setT_ctime(time);

        int resultNum = this.teamForumService.PublishRecruitmentInfo(forum);
        if(resultNum>0){
            result.put("success",true);

        }else{
            result.put("success",false);
        }
        return result;
    }

//    修改帖子信息(同步修改队伍信息,队伍信息的修改,在帖子表单里进行)
@RequestMapping(value="/updatePublishRecruitmentInfo",method= RequestMethod.POST)
public Map<String,Object> updatePublishRecruitmentInfo(Forum forum){
    Map<String, Object> result = new HashMap<>();

    /*
        不允许修改内容:队长名(转让队长可以在管理队员页面进行),
        扩展:
        在修改队伍人数时,需判断队伍当前人数,修改的队伍人数不得低于当前队伍人数

     */

//    用户提交修改后,发布时间自动更新
    Date date=new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
    String time = dateFormat.format(date);

    forum.setT_ctime(time);

    int resultNum = this.teamForumService.PublishRecruitmentInfo(forum);
    if(resultNum>0){
        result.put("success",true);

    }else{
        result.put("success",false);
    }
    return result;
}

    //    添加帖子浏览次数
    @RequestMapping(value = "/addClickTime", method = RequestMethod.POST)
    public Map<String, Object> addClickTime(int id) {
        Map<String, Object> result = new HashMap<>();
        int resultNum = teamForumService.addClickTimeS(id);
        if (resultNum > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    //    删除招募信息
    @RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
    public Map<String, Object> deleteReply(int id) {

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();


        map.put("fid",id); //传入的是该条评论的ID

        int resultNum = teamForumService.deleteReplyS(map);
        if(resultNum>0){
            result.put("success",true);
        }else{
            result.put("success",false);
        }
        return result;
    }

//   回复帖子
    @RequestMapping(value = "/replyForum", method = RequestMethod.POST)
    public Map<String, Object> replyForum(int fid, String content, HttpSession session) {

        Map<String, Object> result = new HashMap<>();

//        获取当前用户名
        User user = (User) session.getAttribute("currentUser");
        String name = user.getU_name();

//        获取当前系统时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd : hh:mm:ss");
        String time = dateFormat.format(date);

//        封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("fid", fid);  //被回复帖子的ID
        map.put("content", content); //回帖内容
        map.put("name", name); //获取回复人名称
        map.put("time", time); //获取发帖时间

        int resultNum = teamForumService.replyForumS(map);
        if (resultNum > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

//    展示回复(展开帖子后,根据该帖子的ID展示帖子的回复内容)
    @RequestMapping(value = "/showReply/page", method = RequestMethod.GET)
    public PageInfo<Reply> showReply(int fid,Integer pageNum) {
        PageInfo<Reply> page = teamForumService.showReplyS(fid,pageNum);
        return page;
    }





}

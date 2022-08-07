package com.huang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.huang.pojo.Forum;
import com.huang.pojo.Page;
import com.huang.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/forum")
@Transactional
public class ForumController {

    @Autowired
    @Qualifier("ForumServiceImpl")
    ForumService forumService;


//    新建帖子
    @RequestMapping(value="/addForum",method = RequestMethod.POST)
    public Map<String,Object> addForum(String title, String content){
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> map = new HashMap<>();

//        获取当前系统时间
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yy-MM-dd :hh:mm:ss");
        String time=dateFormat.format(date);
        map.put("f_title",title);
        map.put("f_content",content);
        map.put("f_time",time);

        int i = forumService.addForumS(map);
        if(i>0){
            result.put("success",true);
        }else{
            result.put("success",false);

        }
        return result;
    }


//    展示帖子内容
    @RequestMapping(value="/showForum",method = RequestMethod.GET)
    public String showForum(Page page) {

        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Forum> forums = forumService.showForumS(page);
        System.out.println(forums);
        return JSON.toJSONString(forums, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);

    }


    //    添加帖子浏览次数
    @RequestMapping(value="/addClickTime",method = RequestMethod.POST)
    public Map<String,Object> addClickTime(int id) {
        Map<String, Object> result = new HashMap<>();
        int i = forumService.addClickTimeS(id);
        if (i>0){
            result.put("success",true);
        }else{
            result.put("success",false);
        }
        return result;


    }


}

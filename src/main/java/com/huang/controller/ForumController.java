//package com.huang.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.huang.pojo.Forum;
//import com.huang.pojo.Page;
//import com.huang.pojo.Reply;
//import com.huang.pojo.User;
//import com.huang.service.ForumService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@RestController
//@RequestMapping("/forum")
//public class ForumController {
//
//    @Autowired
//    @Qualifier("ForumServiceImpl")
//    ForumService forumService;
//
//
//    //    新建帖子
//    @RequestMapping(value = "/addForum", method = RequestMethod.POST)
//    public Map<String, Object> addForum(String title, String content) {
//        Map<String, Object> result = new HashMap<>();
//        Map<String, Object> map = new HashMap<>();
//
////        获取当前系统时间
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd :hh:mm:ss");
//        String time = dateFormat.format(date);
//        map.put("f_title", title);
//        map.put("f_content", content);
//        map.put("f_time", time);
//
//        int resultNum = forumService.addForumS(map);
//        if (resultNum > 0) {
//            result.put("success", true);
//        } else {
//            result.put("success", false);
//
//        }
//        return result;
//    }
//
//
//    //    展示帖子内容
    /*
         当前页
         private int pageNum;
         每页的数量
         private int pageSize;
         当前页的数量
         private int size;
         //由于startRow和endRow不常用，这里说个具体的用法
         //可以在页面中"显示startRow到endRow 共size条数据"

         当前页面第一个元素在数据库中的行号
         private int startRow;
         当前页面最后一个元素在数据库中的行号
         private int endRow;
         总记录数
         private long total;
         总页数
         private int pages;
         结果集
         private List<T> list;
         第一页
         private int firstPage;
         前一页
         private int prePage;

         是否为第一页
         private boolean isFirstPage = false;
         是否为最后一页
         private boolean isLastPage = false;
         是否有前一页
         private boolean hasPreviousPage = false;
         是否有下一页
         private boolean hasNextPage = false;
         导航页码数
         private int navigatePages;
         所有导航页号
         private int[] navigatepageNums;
         后台分页
     */
//    @RequestMapping(value = "/showForum/page", method = RequestMethod.GET)
//    public PageInfo<Forum> showForum(Integer pageNum) {
//
////        获取帖子分页信息
//        PageInfo<Forum> page = forumService.showForumS(pageNum);
//
//        Forum forum = new Forum();
//        forum.getF_author();
//        forum.getF_content();
//        forum.getF_title();
//        forum.getF_time();
//        forum.getF_replayTime();
//        forum.getF_clickTime();
////        将分页信息放入共享请求域中
//        return page;
//
////        return JSON.toJSONString(page.getList(), SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
//
//    }
//
//
//    //    添加帖子浏览次数
//    @RequestMapping(value = "/addClickTime", method = RequestMethod.POST)
//    public Map<String, Object> addClickTime(int id) {
//        Map<String, Object> result = new HashMap<>();
//        int resultNum = forumService.addClickTimeS(id);
//        if (resultNum > 0) {
//            result.put("success", true);
//        } else {
//            result.put("success", false);
//        }
//        return result;
//    }
//
//    //   回复帖子
//    @RequestMapping(value = "/replyForum", method = RequestMethod.POST)
//    public Map<String, Object> replyForum(int fid, String content, HttpSession session) {
//
//        Map<String, Object> result = new HashMap<>();
//
////        获取当前用户名
//        User user = (User) session.getAttribute("currentUser");
//        String name = user.getU_name();
//
////        获取当前系统时间
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd : hh:mm:ss");
//        String time = dateFormat.format(date);
//
////        封装参数
//        Map<String, Object> map = new HashMap<>();
//        map.put("fid", fid);  //被回复帖子的ID
//        map.put("content", content); //回帖内容
//        map.put("name", name); //获取回复人名称
//        map.put("time", time); //获取发帖时间
//
//        int resultNum = forumService.replyForumS(map);
//        if (resultNum > 0) {
//            result.put("success", true);
//        } else {
//            result.put("success", false);
//        }
//        return result;
//    }
//
////    展示回复(展开帖子后,根据该帖子的ID展示帖子的回复内容)
//    @RequestMapping(value = "/showReply", method = RequestMethod.POST)
//    public String showReply(int fid,Page page) {
//
//        List<Reply> replies = forumService.showReplyS(fid,page);
//        System.out.println(replies);
//
//
//        return JSON.toJSONString(replies,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
//    }
//
//
//    //    删除帖子
//    @RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
//    public Map<String, Object> deleteReply(int id) {
//
//        Map<String, Object> result = new HashMap<>();
//        Map<String, Object> map = new HashMap<>();
//
//
//        map.put("fid",id); //传入的是该条评论的ID
//
//        int resultNum = forumService.deleteReplyS(map);
//        if(resultNum>0){
//            result.put("success",true);
//        }else{
//            result.put("success",false);
//        }
//        return result;
//    }
//}

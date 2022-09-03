package com.huang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageInfo;
import com.huang.pojo.*;
import com.huang.service.MessageService;
import com.huang.service.TeamService;
import com.huang.service.UserService;
import com.huang.service.teamForumService;
import com.huang.utils.JwtUtil;
import com.huang.vo.ForumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("MessageServiceImpl")
    private MessageService messageService;


    //    展示招募贴(完全展示)
    @RequestMapping(value="/showAllForum/page",method = RequestMethod.GET)
    public PageInfo<Forum> showAllForum(Integer pageNum){

        PageInfo<Forum> page = teamForumService.showForumS(pageNum);
        List<Forum> forums = page.getList();
        for(int i=0;i<forums.size();i++){
            Forum forum = forums.get(i);
            String g_image = forum.getT_image();
            forum.setT_image("http://60.205.2.92:8080/gogoContest/"+g_image);
        }
        return page;
    }

    //    根据搜索框输入关键词检索招募信息
    @RequestMapping(value="/showForumBySearch/page",method = RequestMethod.POST)
    public PageInfo<Forum> showForumBySearch(String keyWords,Integer pageNum){
        PageInfo<Forum> page = teamForumService.showForumBySearchS(keyWords,pageNum);
        List<Forum> forums = page.getList();
        for(int i=0;i<forums.size();i++){
            Forum forum = forums.get(i);
            String g_image = forum.getT_image();
            forum.setT_image("http://60.205.2.92:8080/gogoContest/"+g_image);
        }
        return page;
    }

//    多条件综合查询展示招募信息
    @RequestMapping(value="/showForumByCondition/page",method = RequestMethod.GET)
    public PageInfo<Forum> showForumByCondition(ForumVo forumVo, Integer pageNum){

        PageInfo<Forum> page = teamForumService.showForumByConditionS(forumVo,pageNum);
        List<Forum> forums = page.getList();
        for(int i=0;i<forums.size();i++){
            Forum forum = forums.get(i);
            String g_image = forum.getT_image();
            forum.setT_image("http://60.205.2.92:8080/gogoContest/"+g_image);
        }
        return page;
    }



//    展示队伍信息
    @RequestMapping(value="/showTeamByCaptainId",method= RequestMethod.POST,produces = {"text/plain;charset=UTF-8"})
    public String showTeamById(HttpServletRequest request) throws UnsupportedEncodingException {


        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        String id = verify.getClaim("id").asString();
        int uid= Integer.parseInt(id);

        Forum forum;
        List<Forum> list = teamForumService.showTeamByCaptainId(uid);
        for (int i=0;i< list.size();i++) {
            forum=list.get(i);
            System.out.println(forum);
            String t_image = forum.getT_image();
            forum.setT_image("http://60.205.2.92:8080/gogoContest/"+t_image);

        }
        System.out.println(list);
        return JSON.toJSONString(list, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
    }


    //发布招募信息
    @RequestMapping(value="/PublishRecruitmentInfo",method= RequestMethod.POST)
    public Map<String,Object> publishRecruitmentInfo(MultipartFile image,Forum forum,
                                                     HttpServletRequest request) throws IOException {

        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int id = Integer.parseInt(verify.getClaim("id").asString());
        String captain = verify.getClaim("name").asString();

        Map<String, Object> result = new HashMap<>();
        String imageName = null;
        String sqlImPath;

//        获取上传图片
        if (!image.isEmpty()) {
            String realPathI = request.getSession().getServletContext().getRealPath("/");
            System.out.println(realPathI);
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = image.getContentType();
            //获得文件后缀名
            String suffixName = contentType.substring(contentType.indexOf("/") + 1);
            //得到 文件名
            imageName = uuid + "." + suffixName;
            //文件保存路径
            image.transferTo(new File(realPathI + imageName));
        }

        sqlImPath =imageName;
        System.out.println(sqlImPath);

//        获取系统时间
        Date date=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String time = dateFormat.format(date);
        forum.setT_ctime(time);
        forum.setT_captain(captain);
        forum.setU_id(id);
        forum.setT_image(sqlImPath);


        int resultNum = this.teamForumService.PublishRecruitmentInfo(forum);
        if(resultNum>0){
            result.put("state",200);
            result.put("msg","创建成功!");
            result.put("implAddress","http://60.205.2.92:8080/gogoContest/"+imageName);

        }else{
            result.put("state",500);
            result.put("msg","创建失败,请稍后再试");
        }
        return result;
    }

    //根据fid展示帖子信息
    @RequestMapping(value="/queryForumById",method= RequestMethod.POST)
    public String showForumByFid(int fid) {
        Forum forum = teamForumService.queryForumById(fid);
        String t_image = forum.getT_image();
        forum.setT_image("http://60.205.2.92:8080/gogoContest/"+t_image);

        List<Forum> list=new ArrayList<>();
        list.add(forum);
        return  JSON.toJSONString(list,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteMapNullValue);

    }


    //    修改帖子信息
    @RequestMapping(value="/updatePublishRecruitmentInfo",method= RequestMethod.POST)
    public Map<String,Object> updatePublishRecruitmentInfo(MultipartFile image,HttpServletRequest request, Forum forum) throws IOException {
        Map<String, Object> result = new HashMap<>();

    //    判断用户提交队伍需求人数是否少于当前队伍人数
        int count = forum.getT_count();
        int fid = forum.getF_id();

    //    查询队伍现有人数
        int teamNum = teamService.judgeFull(fid);
        if(count<teamNum){
            result.put("state",403);
            result.put("msg","队伍需求人数不得小于当前队伍人数,请修改后重试!");
        }else {

//        获取上传图片
            //保存数据库的路径
            String sqlPath;
            // 项目在容器中实际发布运行的根路径
            String realPath = request.getSession().getServletContext().getRealPath("/");
            System.out.println("服务器路径:"+realPath);
            //定义图片保存名
            String imageName = null;
            if (!image.isEmpty()) {
                //生成uuid作为文件名称
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                //获得文件类型（可以判断如果不是图片，禁止上传）
                String contentType = image.getContentType();
                //获得文件后缀名
                String suffixName = contentType.substring(contentType.indexOf("/") + 1);
                //得到 文件名
                imageName = uuid + "." + suffixName;
                //文件保存路径
                image.transferTo(new File(realPath + imageName));
            }
            //把图片的相对路径保存至数据库
            sqlPath =imageName;
            System.out.println("保存路径:"+sqlPath);


    //    用户提交修改后,发布时间自动更新
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String time = dateFormat.format(date);
            forum.setT_ctime(time);
            forum.setT_image(sqlPath);

            int resultNum = this.teamForumService.updatePublishRecruitmentInfo(forum);
            if (resultNum > 0) {
                result.put("state", 200);
                result.put("msg", "修改成功");
                result.put("imageAddress", "http://60.205.2.92:8080/gogoContest/"+imageName);

            } else {
                result.put("state", 500);
                result.put("msg", "服务器繁忙,请稍后再试~");
            }
        }
        return result;
    }

    //    添加帖子浏览次数   待优化
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

    //   回复帖子
    @RequestMapping(value = "/replyForum", method = RequestMethod.POST)
    public Map<String, Object> replyForum(int fid, String content, HttpServletRequest request) throws UnsupportedEncodingException {

        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int uid = Integer.parseInt(verify.getClaim("id").asString());
        String name = verify.getClaim("name").asString();
        Map<String, Object> result = new HashMap<>();
//        获取当前系统时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd : hh:mm:ss");
        String time = dateFormat.format(date);

//        封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("fid", fid);  //被评论帖子的ID
        map.put("content", content); //评论内容
        map.put("name", name); //获取评论人名称
        map.put("uid", uid); //获取评论人ID
        map.put("time", time); //获取评论时间
        try {
            teamForumService.replyForumS(map);
            result.put("state", 200);
            result.put("msg", "评论成功");

        }catch (Exception e){
            e.printStackTrace();
            result.put("state", 500);
            result.put("msg", "服务器繁忙,请稍后再试~");
        }


        int promulgatorId = teamForumService.queryUidByFid(fid);
        Message message = new Message();
        message.setM_body("您发布的帖子被"+name+"评论了！！");
        message.setM_type("teamForum");
        message.setM_time(time);
        message.setM_uid(promulgatorId);
        message.setM_xid(fid);
        messageService.creatMessage(message);

        return result;
    }


    //    删除评论
    @RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
    public Map<String, Object> deleteReply(int rid ,HttpServletRequest request ) throws UnsupportedEncodingException {

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();

//        判断用户是否为管理员 identity=0则为管理员
        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int uid = Integer.parseInt(verify.getClaim("id").asString());
        int identity = userService.findIdentity(uid);
        System.out.println("identity"+identity);


        //判断用户是否为预删除评论作者
        int returnUid = teamForumService.judgeDelRepIdentity(rid);

        if (returnUid == uid || identity==0) {
            map.put("rid", rid);
            map.put("uid", uid);
            int resultNum = teamForumService.deleteReplyS(map);
            if (resultNum > 0) {
                result.put("state", 200);
                result.put("msg", "删除成功");
            } else {
                result.put("state", 500);
                result.put("msg", "删除失败,请稍后再试");
            }
        }else{
            result.put("state",403);
            result.put("msg","您无权限删除他人评论~");
        }
        return result;
    }

//    展示回复(展开帖子后,根据该帖子的ID展示帖子的回复内容)  帖子包含用户头像!!!!!!!!!!
    @RequestMapping(value = "/showReply/page", method = RequestMethod.GET)
    public PageInfo<Reply> showReply(int fid,Integer pageNum) {
        PageInfo<Reply> page = teamForumService.showReplyS(fid,pageNum);
        List<Reply> list = page.getList();
        System.out.println(list);
        return page;
    }

//    在组队详情页通过点击比赛名跳转到比赛详情页.
    @RequestMapping(value = "/jumpToGameById", method = RequestMethod.POST)
    public String  jumpToGameById(int gid) {

        Game game = teamForumService.jumpToGameById(gid);
        List<Game> list=new ArrayList();

        list.add(game);
        return JSON.toJSONString(list,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }





}

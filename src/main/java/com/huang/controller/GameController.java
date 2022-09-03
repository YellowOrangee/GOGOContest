package com.huang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageInfo;
import com.huang.pojo.Favorites;
import com.huang.pojo.Game;
import com.huang.pojo.Message;
import com.huang.service.*;
import com.huang.utils.JwtUtil;
import com.huang.vo.GameConditionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    @Qualifier("GameServiceImpl")
    GameService gameService;
    @Autowired
    @Qualifier("UserServiceImpl")
    UserService userService;
    @Autowired
    @Qualifier("FavoritesServiceImpl")
    FavoritesService favoritesService;
    @Autowired
    @Qualifier("AdminServiceImpl")
    AdminService adminService;

    @Autowired
    @Qualifier("MessageServiceImpl")
    private MessageService messageService;

    //创建比赛信息
    @RequestMapping(value = "/creatGame",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> creatGame(MultipartFile file , MultipartFile image, Game game, HttpServletRequest request, HttpSession session)throws Exception{

        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int uid = Integer.parseInt(verify.getClaim("id").asString());
        game.setUid(uid);

        Map<String,Object> result=new HashMap<>();
        int resultNum=0;
        String imageName = null;
        String filename = null;

//        获取上传文件
        if (!file.isEmpty()) {
            String realPath = request.getSession().getServletContext().getRealPath("/");
            System.out.println(realPath);
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = file.getContentType();
            //获得文件后缀名
            String suffixName = contentType.substring(contentType.indexOf("/") + 1);
            //得到 文件名
            filename = uuid + "." + suffixName;
            //文件保存路径
            file.transferTo(new File(realPath + filename));
        }
        if (!image.isEmpty()) {  //        获取上传图片
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

        game.setG_image(imageName);
        game.setG_file(filename);

        resultNum = gameService.creatGame(game);
        if(resultNum>0){
            result.put("state",200);
            result.put("msg","创建成功!");
            result.put("implAddress","http://60.205.2.92:8080/gogoContest/"+imageName);
            result.put("fileAddress","http://60.205.2.92:8080/gogoContest/"+filename);

        }else{
            result.put("state",500);
            result.put("msg","创建失败,请稍后再试");
        }
        return result;
    }

    //删除比赛信息（管理员和发布者）
    @RequestMapping(value = "/deleteGame",method = RequestMethod.POST)
    public Map<String,Object> deleteGame(Integer g_id, HttpServletRequest request) throws UnsupportedEncodingException {
        //存储返回信息
        Map<String, Object> result = new HashMap<>();

        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int uid = Integer.parseInt(verify.getClaim("id").asString());

        System.out.println(uid);
        System.out.println(gameService.queryAdIdByGid(g_id));

        if(true) {
//        判断提交修改申请者的身份(是否为管理员)
            if (uid == gameService.queryAdIdByGid(g_id)) {
                if (gameService.deleteGame(g_id) > 0) {
                    result.put("state", 200);
                    result.put("msg", "删除成功");
                } else {
                    result.put("state", 500);
                    result.put("msg", "您的管理员身份有误,请联系工作人员~");
                }
            }
//            判断该用户是否为大赛发布者
            if (uid == gameService.queryUidByGid(g_id)) {
                if (gameService.deleteGame(g_id) > 0) {
                    result.put("state", 200);
                    result.put("msg", "删除成功");
                } else {
                    result.put("state", 500);
                    result.put("msg", "服务器繁忙,请稍后再试~");
                }
            }
        }else {
                    result.put("state", 403);
                    result.put("msg", "抱歉,您不能删除他人发布的信息~");
                }
            return result;
        }

    //修改比赛信息（只有发布者可以修改)
    // 1. 判断提交修改申请用户是否为该比赛信息发布者
    @RequestMapping(value = "/toUpdateGame",method = RequestMethod.POST)
    public Map<String,Object> toUpdateGame(Integer g_id, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
//      存储返回信息
        Map<String,Object> result =new HashMap<>();

//        判断提交修改申请者的身份(是否为管理员)

        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int uid = Integer.parseInt(verify.getClaim("id").asString());
        if(true) {
            //验证管理员身份
            if(adminService.queryAdminId(uid)>0) {
                if (uid == gameService.queryAdIdByGid(g_id)) {
                    session.setAttribute("up_gid", g_id);
                    result.put("state", 200);
                    result.put("msg", "管理员身份认证成功");
                }
            }
            else if (uid == gameService.queryUidByGid(g_id)) {
                session.setAttribute("up_gid", g_id);
                result.put("state", 200);
                result.put("msg", "作者身份认证成功");
            }
            else {
                result.put("state", 403);
                result.put("msg", "抱歉,您无修改权限哦~");
            }
        }

        return result;
    }

    //    2. 若身份验证成功,则跳转到详情页面,展示该比赛信息  用于修改比赛信息
    @RequestMapping(value = "/showGameById",method = RequestMethod.POST)
    public String showGameById(HttpSession session){

        int g_id = (int) session.getAttribute("up_gid");
        Game game = gameService.queryGameByGid(g_id);
        String g_image = game.getG_image();
        game.setG_image("http://60.205.2.92:8080/gogoContest/"+g_image);
        List<Game> list=new ArrayList<>();
        list.add(game);
        return JSON.toJSONString(list,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    @RequestMapping(value = "/updateGame",method = RequestMethod.POST)
    public Map<String,Object> updateGame(MultipartFile file , MultipartFile image,Game game,HttpSession session,HttpServletRequest request)
            throws IOException {

        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int uid = Integer.parseInt(verify.getClaim("id").asString());

        int g_id = (int) session.getAttribute("up_gid");
        game.setG_id(g_id);
        game.setUid(uid);
        Map<String,Object> result =new HashMap<>();

        String imageName = null;
        String filename = null;

//        获取上传文件
        if (!file.isEmpty()) {
            String realPath = request.getSession().getServletContext().getRealPath("/");
            System.out.println(realPath);
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = file.getContentType();
            //获得文件后缀名
            String suffixName = contentType.substring(contentType.indexOf("/") + 1);
            //得到 文件名
            filename = uuid + "." + suffixName;
            //文件保存路径
            file.transferTo(new File(realPath + filename));
        }
        if (!image.isEmpty()) {  //        获取上传图片
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


        game.setG_image(imageName);
        game.setG_file(filename);

        int resultNum = gameService.updateGame(game);
        if(resultNum>0){
            result.put("state",200);
            result.put("msg","修改成功!");
            result.put("implAddress","http://60.205.2.92:8080/gogoContest/"+imageName);
            result.put("fileAddress","http://60.205.2.92:8080/gogoContest/"+filename);


//            可通知收藏者,更改信息

        }else{
            result.put("state",500);
            result.put("msg","修改失败,请稍后再试");
        }
        return result;
    }

    //模糊查询
    //通过搜索框模糊大赛名查询
    @RequestMapping("/queryGameByName/page")
    @ResponseBody
    public String queryGameByName(String name,Integer pageNum){
        PageInfo<Game> list = gameService.queryGameBySearch(name,pageNum);
        List<Game> games = list.getList();
        for(int i=0;i<games.size();i++){
            Game game = games.get(i);
            String g_image = game.getG_image();
            game.setG_image("http://60.205.2.92:8080/gogoContest/"+g_image);

            String g_file = game.getG_file();
            game.setG_file("http://60.205.2.92:8080/gogoContest/"+g_file);
        }
        return JSON.toJSONString(list,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    //条件模糊查询
    @RequestMapping("/queryGameByCondition/page")
    @ResponseBody
    public String queryGameByCondition(GameConditionVo gameConditionVo,Integer pageNum){
        PageInfo<Game> page = gameService.queryGameByCondition(gameConditionVo,pageNum);
        List<Game> games = page.getList();
        for(int i=0;i<games.size();i++){
            Game game = games.get(i);
            String g_image = game.getG_image();
            game.setG_image("http://60.205.2.92:8080/gogoContest/"+g_image);
            String g_file = game.getG_file();
            game.setG_file("http://60.205.2.92:8080/gogoContest/"+g_file);
        }

        return JSON.toJSONString(page,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    @RequestMapping("/showAllGames/page")
    @ResponseBody
    public String showAllGames(HttpServletRequest request,Integer pageNum) throws UnsupportedEncodingException {

        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int u_id = Integer.parseInt(verify.getClaim("id").asString());
        Message message = new Message();

//        新增消息
        List<Favorites> userCollectG = favoritesService.mycollect(u_id);
        for(Favorites f:userCollectG){
            int G_id = f.getFa_gid();
            Game game = gameService.queryGameByGid(G_id);
            String dateEnd = game.getG_signup_eTime();
            String dateStart = game.getG_signup_sTime();
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String nowTime = dateFormat.format(date);


            if(dateEnd.equals(nowTime)){
                message.setM_uid(u_id);
                message.setM_body("今天"+game.getG_title()+"报名将截止！");
                message.setM_type("大赛消息");
                message.setM_time(nowTime);
                message.setM_xid(G_id);
                messageService.creatMessage(message);
            }
            if(dateStart.equals(nowTime)){
                message.setM_uid(u_id);
                message.setM_body("今天"+game.getG_title()+"开始报名!");
                message.setM_type("大赛消息");
                message.setM_time(nowTime);
                message.setM_xid(G_id);
                messageService.creatMessage(message);
            }
        }
        PageInfo<Game> page= gameService.showAllGameS(pageNum);
        List<Game> games = page.getList();
        for(int i=0;i<games.size();i++){
            Game game = games.get(i);
            String g_image = game.getG_image();
            game.setG_image("http://60.205.2.92:8080/gogoContest/"+g_image);
        }

        return JSON.toJSONString(page,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    //展示比赛信息ById  用于展示详细比赛信息给用户
    @RequestMapping("/showGameDetail")
    @ResponseBody
    public String showGameDetail(int gid,HttpSession session){
        //将id放入session方便收藏比赛时可以查找到该比赛
        session.setAttribute("g_id",gid);

        Game game = gameService.queryGameByGid(gid);
        String g_image = game.getG_image();
        game.setG_image("http://60.205.2.92:8080/gogoContest/"+g_image);
        List<Game> list=new ArrayList<>();
        list.add(game);

        return JSON.toJSONString(list);
    }

    //收藏比赛
    @RequestMapping("/addCollect")
    @ResponseBody
    public Map<String,Object> collect(HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,Object> result=new HashMap<>();

        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int fa_uid = Integer.parseInt(verify.getClaim("id").asString());
        int resultNum=0;

        Favorites favorites=new Favorites();
        favorites.setFa_uid(fa_uid);

        int fa_gid = (int) session.getAttribute("g_id");
        Game game = gameService.queryGameByGid(fa_gid);
        String fa_gtitle =  game.getG_title();
        String fa_gbody = game.getG_body();

        favorites.setFa_gid(fa_gid);
        favorites.setFa_gtitle(fa_gtitle);
        favorites.setFa_gbody(fa_gbody);

        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yy-MM-dd");
        String time = dateFormat.format(date);
        System.out.println(time);
        favorites.setFa_time(time);

        resultNum = favoritesService.collect(favorites);


//        /*通过gid查到uid，然后给uid内个人发消息提示*/
//        int promulgatorId = gameService.queryUidByGid(fa_gid);
//        Message message = new Message();
//        message.setM_body("您发布的比赛被"+userService.queryUserById(fa_uid).getU_name()+"收藏了！！");
//        message.setM_type("大赛消息");
//        message.setM_time(time);
//        message.setM_uid(promulgatorId);
//        message.setM_xid(fa_gid);
//        messageService.creatMessage(message);


        if (resultNum > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }


    //    判断该大赛信息是否已被该用户收藏
    @RequestMapping(value = "/judgeCollection",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> judgeCollection(HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException {
        Map<String, Object> result = new HashMap<>();
        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int uid = Integer.parseInt(verify.getClaim("id").asString());

        int gid = (int) session.getAttribute("g_id");
        int resultNum = favoritesService.judgeCollect(gid, uid);

        if(resultNum>0){
            result.put("state",true);
            result.put("msg","已被收藏");

        }else{
            result.put("state",false);
            result.put("msg","未被收藏");
        }
        return result;
    }

    //取消收藏
    @RequestMapping("/cancelCollect")
    @ResponseBody
    public Map<String,Object> cancel(HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,Object> result=new HashMap<>();
        int resultNum=0;
        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int fa_uid = Integer.parseInt(verify.getClaim("id").asString());
        int fa_gid = (int) session.getAttribute("g_id");
        resultNum = favoritesService.cancel(fa_uid,fa_gid);

        if (resultNum > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }

    //显示比赛收藏人数(用于显示收藏量)
    @RequestMapping("/count")
    public int count(int fa_gid){
        int count = favoritesService.countcollect(fa_gid);
        return count;
    }

    //用户收藏夹
    @RequestMapping("/myCollect")
    @ResponseBody
    public String myCollect(HttpServletRequest request) throws UnsupportedEncodingException {
        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int fa_uid = Integer.parseInt(verify.getClaim("id").asString());

        List<Favorites> list = favoritesService.mycollect(fa_uid);

        return JSON.toJSONString(list);
    }

//    创建队伍时选择参赛名时下拉框展示大赛信息
    @RequestMapping("/queryNameAndSponsor")
    @ResponseBody
    public String queryNameAndSponsor(String keyWords) {
        List<Game> games=new ArrayList<>();
        if (keyWords != null || keyWords==" "){

            games = gameService.queryNameAndSponsor(keyWords);
            System.out.println(games);

        }
        return JSON.toJSONString(games);
    }

//    通过大赛名和举办方查询大赛信息,用于在队伍详细信息里通过点击大赛名来跳转到相应的大赛信息上
}

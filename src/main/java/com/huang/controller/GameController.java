package com.huang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.huang.pojo.Admin;
import com.huang.pojo.Favorites;
import com.huang.pojo.Game;
import com.huang.pojo.User;
import com.huang.service.FavoritesService;
import com.huang.service.GameService;
import com.huang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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

    //创建比赛信息
    @RequestMapping(value = "/creatGame")
    public Map<String,Object> creatGame(Game game,HttpSession session){

      Map<String,Object> result=new HashMap<>();
      int resultNum=0;

//
        //        获取系统时间(仅测试,实际是需要用户从前端返回标准的时间)
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yy-MM-dd :hh:dd:ss");
        String time = dateFormat.format(date);
        System.out.println(time);
        game.setG_sTime(time);
        game.setG_eTime(time);

//      判断用户身份
        Admin admin = (Admin) session.getAttribute("currentAdmin");
        if(admin != null){
            int ad_id = admin.getAd_id();
            game.setUid(ad_id);

            resultNum = gameService.creatGame(game);
            if (resultNum > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
            }
        }else {
            //当前用户为普通用户
            User user = (User) session.getAttribute("currentUser");
            int u_id = user.getU_id();
            game.setUid(u_id);

            resultNum = gameService.creatGame(game);
            if (resultNum > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
            }
        }
      return result;
    }

    //删除比赛信息（管理员和发布者）
    @RequestMapping(value = "/deleteGame")
    public Map<String,Object> deleteGame(Integer g_id, HttpSession httpSession) {
        Map<String, Object> result = new HashMap<>();
        int resultNum = 0;

//        如果当前用户是管理员,则直接删除
        Admin admin = (Admin) httpSession.getAttribute("currentAdmin");
        if (admin != null) {
            resultNum = gameService.deleteGame(g_id);
            if (resultNum > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
            }
        } else {
//            当前用户为普通用户,则判断该用户是否为该大赛信息发布者
            User user = (User) httpSession.getAttribute("currentUser");
            int u_id = user.getU_id();

//            如果当前用户为该大赛信息的发布者,则可以删除
            if (gameService.queryUidByGid(g_id) == u_id) {
                resultNum = gameService.deleteGame(g_id);
                if (resultNum > 0) {
                    result.put("success", true);
                } else {
                    result.put("success", false);
                }
            } else {
                result.put("msg", "抱歉,您无权删除他人发布信息!");
            }
        }
        return result;
    }

//    //修改比赛信息（只有发布者可以修改)
//      // 1. 判断提交修改申请用户是否为该比赛信息发布者
//    @RequestMapping("/toUpdateGame")
//    public Map<String,Object> toUpdateGame(Integer g_id,HttpSession session){
////      存储返回信息
//        Map<String,Object> result =new HashMap<>();
////        判断提交修改申请者的身份(是否为管理员)
//        Admin admin = (Admin) session.getAttribute("currentAdmin");
//        if (admin !=null){
//            int ad_id = admin.getAd_id();
//            if(ad_id == gameService.queryAdIdByGid(g_id)){
////                匹配成功则将g_id放入session,方便展示信息
//                session.setAttribute("g_id", g_id);
//                result.put("success", true);
//            } else {
//                result.put("msg", "抱歉,只有本篇大赛信息的作者才有修改权限哦~");
//            }
//        }else {
//            User user = (User) session.getAttribute("currentUser");
//            int u_id = user.getU_id();
////            用户ID是这篇大赛信息的编辑者
//            if (u_id == gameService.queryUidByGid(g_id)) {
//                session.setAttribute("g_id", g_id);
//                result.put("success", true);
////            用户ID不是这篇大赛信息的编辑者
//            } else {
//                result.put("msg", "抱歉,只有本篇大赛信息的作者才有修改权限哦~");
//            }
//        }
//        return result;
//    }
//
////    2. 若身份验证成功,则跳转到修改页面,展示该比赛信息
//     @RequestMapping("/showGameById")
//     public String showGameById(HttpSession session){
//
//         int g_id = (int) session.getAttribute("g_id");
//         Game game = gameService.queryGameByGid(g_id);
//         List<Game> list=new ArrayList<>();
//         list.add(game);
//         return JSON.toJSONString(list,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
//     }

    @RequestMapping("/updateGame")
    public Map<String,Object> updateGame(Game game){
        Map<String,Object> result =new HashMap<>();

        int resultNum = gameService.updateGame(game);
        if(resultNum>0){
            result.put("success",true);
        }else{
            result.put("success",false);
        }
        return result;
    }


    //    2. 若身份验证成功,则跳转到修改页面,展示该比赛信息
     @RequestMapping("/showGameById")
     public String showGameById(Integer gid,HttpSession session){

        session.setAttribute("g_id",gid);
         Game game = gameService.queryGameByGid(gid);
         List<Game> list=new ArrayList<>();
         list.add(game);
         return JSON.toJSONString(list,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
     }


    //大赛信息展示
    @RequestMapping("/showGame/page")
    public String showGame(Integer pageNum){
        PageInfo<Game> list = gameService.showAllGameS(pageNum);
        return JSON.toJSONString(list, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
    }

    //收藏比赛
    @RequestMapping("/addCollect")
    @ResponseBody
    public Map<String,Object> collect(Favorites favorites, HttpSession session){
        Map<String,Object> result=new HashMap<>();
        int resultNum=0;

//        获取当前用户id
        User user = (User) session.getAttribute("currentUser");
        int fa_uid = user.getU_id();
        favorites.setFa_uid(fa_uid);

//        获取当前比赛信息id
        int fa_gid = (int) session.getAttribute("g_id");
        favorites.setFa_gid(fa_gid);

//        获取系统时间
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yy-MM-dd");
        String time = dateFormat.format(date);
        favorites.setFa_time(time);

        resultNum = favoritesService.addCollect(favorites);

        if (resultNum > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }
//    判断用户是否已收藏
    @RequestMapping(value="judgeCollect",method = RequestMethod.GET)
    public Map<String,Object> judgeCollect(HttpSession session){
        Map<String,Object> result=new HashMap<>();
        int gid = (int) session.getAttribute("g_id");

        User user = (User) session.getAttribute("currentUser");

        int uid = user.getU_id();
        Integer resultNum = favoritesService.judgeCollect(gid, uid);
        if(resultNum !=null){
            result.put("success",false);
        }else{
            result.put("success",true);
        }
        return result;
    }

    //取消收藏
    @RequestMapping("/cancelCollect")
    @ResponseBody
    public Map<String,Object> cancel(HttpSession session){
        Map<String,Object> result=new HashMap<>();
        int resultNum=0;
        User user = (User) session.getAttribute("currentUser");
        int fa_uid = user.getU_id();

        int fa_gid = (int) session.getAttribute("g_id");

        resultNum = favoritesService.cancelCollect(fa_uid,fa_gid);

        if (resultNum > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }

    //显示比赛收藏人数(用于显示收藏量)
    @RequestMapping("/collectionTimes")
    public int count(int fa_gid){
        int count = favoritesService.collectionTimes(fa_gid);
        return count;
    }

    //用户收藏夹
    @RequestMapping("/myCollect")
    public String myCollect(HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        int fa_uid = user.getU_id();

        List<Favorites> list = favoritesService.myCollect(fa_uid);
        return JSON.toJSONString(list,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    //展示谁收藏了信息
    @RequestMapping("/whoCollect")
    public String whoCollect(Integer gid){
        List<Favorites> list = favoritesService.whoCollect(gid);
        return JSON.toJSONString(list,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }


}

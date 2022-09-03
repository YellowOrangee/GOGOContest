package com.huang.controller;


import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageInfo;
import com.huang.pojo.Game;
import com.huang.pojo.Message;
import com.huang.service.FavoritesService;
import com.huang.service.GameService;
import com.huang.service.MessageService;
import com.huang.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    @Qualifier("MessageServiceImpl")
    MessageService messageService;

    @Autowired
    @Qualifier("FavoritesServiceImpl")
    FavoritesService favoritesService;
    @Autowired
    @Qualifier("GameServiceImpl")
    GameService gameService;


    //展示消息列表
    @RequestMapping("/showAllMessage/page")
    public String showAllMessage(HttpServletRequest request,Integer pageNum) throws UnsupportedEncodingException {
        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int uid = Integer.parseInt(verify.getClaim("id").asString());
        PageInfo<Message> messages = messageService.showAllMessage(uid,pageNum);
        return JSON.toJSONString(messages);
    }

    //通过点击其他收藏消息可以跳转比赛信息页面，查看是哪条比赛被收藏（类似b站收到点赞功能可以查看发布的视频）
    @RequestMapping("/showGameMessageDetail")
    public String showGameMessageDetail(Integer id) {
        Game game = null;
        Message message = messageService.showMessageById(id);
        if (message.getM_type().equals("game")) {
            game = gameService.queryGameByGid(message.getM_xid());
        }
        return JSON.toJSONString(game);
    }
}

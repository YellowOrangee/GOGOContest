package com.huang.dao;

import com.huang.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {


    int creatMessage(Message message);  //创建消息提醒

    List<Message> showAllMessage(@Param("uid") int uid);   //全部消息提醒

    Message showMessageById(int id);   //查看消息详情

    Message applyToTeam();
}

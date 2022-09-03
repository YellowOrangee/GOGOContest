package com.huang.service;

import com.github.pagehelper.PageInfo;
import com.huang.pojo.Message;

import java.util.List;

public interface MessageService {

    int creatMessage(Message message);  //创建消息提醒
    PageInfo<Message> showAllMessage(int uid,Integer pageNum);   //全部消息提醒
    Message showMessageById(int id);   //查看消息详情
}

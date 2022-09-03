package com.huang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huang.dao.MessageMapper;
import com.huang.pojo.Message;

import java.util.List;

public class MessageServiceImpl implements MessageService{

    private MessageMapper messageMapper;

    public void setMessageMapper(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

        @Override
        public int creatMessage(Message message) {
            return messageMapper.creatMessage(message);
        }

        @Override
        public PageInfo<Message> showAllMessage(int uid,Integer pageNum) {

            PageHelper.startPage(pageNum,4);
            List<Message> games = messageMapper.showAllMessage(uid);
            PageInfo page=new PageInfo(games,5);
            return page;

        }

        @Override
        public Message showMessageById(int id) {
            return messageMapper.showMessageById(id);
        }
}

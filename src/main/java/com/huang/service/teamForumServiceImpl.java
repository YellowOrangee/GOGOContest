package com.huang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huang.dao.TeamMapper;
import com.huang.dao.teamForumMapper;
import com.huang.pojo.Forum;
import com.huang.pojo.Page;
import com.huang.pojo.Reply;

import java.util.List;
import java.util.Map;

public class teamForumServiceImpl implements teamForumService{

    private teamForumMapper teamForumMapper;
    public void setTeamForumMapper(com.huang.dao.teamForumMapper teamForumMapper) {
        this.teamForumMapper = teamForumMapper;
    }

    @Override
    public int PublishRecruitmentInfo(Forum forum) {
        return this.teamForumMapper.PublishRecruitmentInfo(forum);
    }

    @Override
    public PageInfo<Forum> showForumS(Integer pageNum) {
//        开启分页功能
        PageHelper.startPage(pageNum,4);
//        获取员工信息
        List<Forum> list= teamForumMapper.showForum();
//        获取分页相关数据
        PageInfo<Forum> page= new PageInfo<>(list,5);

        return page;
    }

    @Override
    public PageInfo<Forum> showForumByCondition(Integer pageNum) {
        //        开启分页功能
        PageHelper.startPage(pageNum,4);
//        获取员工信息
        List<Forum> list= teamForumMapper.showForumByCondition();
//        获取分页相关数据
        PageInfo<Forum> page= new PageInfo<>(list,5);
        return page;
    }

    @Override
    public PageInfo<Forum> showForumBySearchS(String keyWords,Integer pageNum) {
        //        开启分页功能
        PageHelper.startPage(pageNum,4);
//        获取员工信息
        List<Forum> list= teamForumMapper.showForumBySearch(keyWords);
//        获取分页相关数据
        PageInfo<Forum> page= new PageInfo<>(list,5);
        return page;
    }

    @Override
    public int addClickTimeS(int id) {
        return this.teamForumMapper.addClickTime(id);
    }

    @Override
    public int replyForumS(Map<String, Object> map) {
        return this.teamForumMapper.replyForum(map);
    }

    @Override
    public PageInfo<Reply> showReplyS(int fid, Integer pageNum) {
//        开启分页功能
        PageHelper.startPage(pageNum,4);
//        获取回复信息
        List<Reply> list = this.teamForumMapper.showReply(fid);
//        获取分页相关数据
        PageInfo page = new PageInfo<>(list,5);

        return page;
    }

    @Override
    public int deleteReplyS(Map<String, Object> map) {
        return this.teamForumMapper.deleteReply(map);
    }
}

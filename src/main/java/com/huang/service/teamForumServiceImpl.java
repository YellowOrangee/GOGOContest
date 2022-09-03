package com.huang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huang.dao.teamForumMapper;
import com.huang.pojo.Forum;
import com.huang.pojo.Game;
import com.huang.pojo.Reply;
import com.huang.vo.ForumVo;

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
    public PageInfo<Forum> showForumByConditionS(ForumVo forumVo, Integer pageNum) {
        //        开启分页功能
        PageHelper.startPage(pageNum,4);
//        获取员工信息
        List<Forum> list= teamForumMapper.showForumByCondition(forumVo);
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

    @Override
    public int judgeDelRepIdentity(int rid) {
        return this.teamForumMapper.judgeDelRepIdentity(rid);
    }

    @Override
    public List<Forum> showTeamByCaptainId(int id) {
        return this.teamForumMapper.showTeamByCaptainId(id);
    }

    @Override
    public int queryUidByFid(Integer id) {
        return this.teamForumMapper.queryUidByFid(id);
    }

    @Override
    public Game jumpToGameById(int gid) {
        return this.teamForumMapper.jumpToGameById(gid);
    }

    @Override
    public Forum queryForumById(int fid) {
        return this.teamForumMapper.queryForumById(fid);
    }

    @Override
    public int updatePublishRecruitmentInfo(Forum forum) {
        return this.teamForumMapper.updatePublishRecruitmentInfo(forum);
    }
}

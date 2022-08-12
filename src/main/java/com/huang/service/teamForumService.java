package com.huang.service;

import com.github.pagehelper.PageInfo;
import com.huang.pojo.Forum;
import com.huang.pojo.Page;
import com.huang.pojo.Reply;

import java.util.List;
import java.util.Map;

public interface teamForumService {

    int PublishRecruitmentInfo(Forum forum);

    //    展示帖子(完全展示)
    PageInfo<Forum> showForumS(Integer pageNum);


    //    多条件综合展示招募贴
    PageInfo<Forum> showForumByCondition(Integer pageNum);

    //    直接搜索展示招募贴
    PageInfo<Forum> showForumBySearchS(String keyWords,Integer pageNum);

    //    添加帖子点击量
    int addClickTimeS(int id);

    //    添加帖子回复
    int replyForumS(Map<String,Object> map);

    //    展示回复内容
    PageInfo<Reply> showReplyS(int fid,Integer pageNum);

    //    删除帖子回复
    int deleteReplyS(Map<String,Object> map);
}

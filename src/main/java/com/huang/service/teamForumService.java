package com.huang.service;

import com.github.pagehelper.PageInfo;
import com.huang.pojo.Forum;
import com.huang.pojo.Game;
import com.huang.pojo.Reply;
import com.huang.vo.ForumVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface teamForumService {

    int PublishRecruitmentInfo(Forum forum);

    //    展示帖子(完全展示)
    PageInfo<Forum> showForumS(Integer pageNum);

    //    多条件综合展示招募贴
    PageInfo<Forum> showForumByConditionS(ForumVo forumVo, Integer pageNum);

    //    直接搜索展示招募贴
    PageInfo<Forum> showForumBySearchS(String keyWords,Integer pageNum);

    //根据帖子ID查询帖子信息
    Forum queryForumById(int fid);

    int updatePublishRecruitmentInfo(Forum forum);

    //    添加帖子点击量
    int addClickTimeS(int id);

    //    添加帖子回复
    int replyForumS(Map<String,Object> map);

    //    展示回复内容
    PageInfo<Reply> showReplyS(int fid,Integer pageNum);

    //    删除帖子评论
    int deleteReplyS(Map<String,Object> map);

    int judgeDelRepIdentity( int rid);

    // 用户点击发布招募信息,跳转页面时根据用户名,在帖子表中查询该用户已创建的队伍(若没有则显示无)
    List<Forum> showTeamByCaptainId(int id);

    int queryUidByFid(Integer id);

    Game jumpToGameById(int gid);


}

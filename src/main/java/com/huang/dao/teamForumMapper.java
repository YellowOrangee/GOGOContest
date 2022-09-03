package com.huang.dao;

import com.huang.pojo.Forum;
import com.huang.pojo.Game;
import com.huang.pojo.Reply;
import com.huang.vo.ForumVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface teamForumMapper {

    int PublishRecruitmentInfo(Forum forum);



    //    展示招募帖(完全展示)
    List<Forum> showForum();

    //    多条件综合展示招募贴
    List<Forum> showForumByCondition(ForumVo forumVo);

    //    新增                  根据id查询招募贴信息
    Forum queryForumById(@Param("id") int fid);

    //修改招募贴
    int updatePublishRecruitmentInfo(Forum forum);

    //关键词检索查询招募贴
    List<Forum> showForumBySearch(@Param("keyWords") String keyWords);

    //增加浏览量
    int addClickTime(@Param("id") int id);

    //评论帖子
    int replyForum(Map<String,Object> map);

    //展示评论
    List<Reply> showReply(@Param("id") int fid);

    //判断删评论人身份
    int judgeDelRepIdentity(@Param("rid") int rid);
    //删除评论
    int deleteReply(Map<String,Object> map);

    //通过队长id展示队伍
    List<Forum> showTeamByCaptainId(@Param("id") int id);

//    消息接口
    int queryUidByFid(@Param("fid") Integer id);

    Game jumpToGameById(@Param("gid") int gid);
}

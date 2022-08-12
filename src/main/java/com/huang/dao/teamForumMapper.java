package com.huang.dao;

import com.github.pagehelper.PageInfo;
import com.huang.pojo.Forum;
import com.huang.pojo.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface teamForumMapper {

    int PublishRecruitmentInfo(Forum forum);



    //    展示招募帖(完全展示)
    List<Forum> showForum();


//    直接搜索展示招募贴
    List<Forum> showForumByCondition();

//    多条件综合展示招募贴
    List<Forum> showForumBySearch(@Param("keyWords") String keyWords);

    int addClickTime(@Param("id") int id);

    int replyForum(Map<String,Object> map);

    List<Reply> showReply(@Param("id") int fid);

    int deleteReply(Map<String,Object> map);
}

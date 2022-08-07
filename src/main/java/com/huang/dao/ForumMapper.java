package com.huang.dao;


import com.huang.pojo.Forum;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ForumMapper {

//    创建帖子
    int addForum(Map<String,Object> map);

//    展示帖子(完全展示)
    List<Forum> showForum();

    int addClickTime(@Param("id") int id);
}

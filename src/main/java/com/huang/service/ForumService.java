package com.huang.service;


import com.huang.pojo.Forum;
import com.huang.pojo.Page;

import java.util.List;
import java.util.Map;

public interface ForumService {

    int addForumS(Map<String,Object> map);

    //    展示帖子(完全展示)
    List<Forum> showForumS(Page page);

    int addClickTimeS(int id);
}

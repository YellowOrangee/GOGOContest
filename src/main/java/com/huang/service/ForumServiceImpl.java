package com.huang.service;

import com.github.pagehelper.PageHelper;
import com.huang.dao.ForumMapper;
import com.huang.pojo.Forum;
import com.huang.pojo.Page;

import java.util.List;
import java.util.Map;

public class ForumServiceImpl implements ForumService{

    private ForumMapper forumMapper;

    public void setForumMapper(ForumMapper forumMapper) {
        this.forumMapper = forumMapper;
    }

    @Override
    public int addForumS(Map<String, Object> map) {
        return this.forumMapper.addForum(map);
    }

    @Override
    public List<Forum> showForumS(Page page) {
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        return this.forumMapper.showForum();
    }

    @Override
    public int addClickTimeS(int id) {
        return this.forumMapper.addClickTime(id);
    }

}

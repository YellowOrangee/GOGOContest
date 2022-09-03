package com.huang.service;

import com.huang.dao.AdminMapper;
import com.huang.pojo.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AdminServiceImpl implements AdminService{

    private AdminMapper adminMapper;


    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin adminLogin(String name, String pwd) {
        return this.adminMapper.adminLogin(name, pwd);
    }

    @Override
    public int queryAdminId(int aid) {
        return this.adminMapper.queryAdminId(aid);
    }
}

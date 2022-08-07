package com.huang.service;

import com.huang.dao.AdminMapper;
import com.huang.pojo.Admin;

public class AdminServiceImpl implements AdminService{

    private AdminMapper adminMapper;


    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin adminLogin(String name, String pwd) {
        return this.adminMapper.adminLogin(name, pwd);
    }
}

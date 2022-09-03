package com.huang.service;

import com.huang.pojo.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminService {

    Admin adminLogin(String name, String pwd);

    int queryAdminId(@Param("aid") int aid);
}

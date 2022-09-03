package com.huang.service;

import com.huang.pojo.Admin;
import com.huang.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


public interface UserService {

    int addUser(String name,String password,String email);

    User queryUserById( int id);

    User queryUserByName( String name);

    User Login(String name,String password);

    String findPwdByEmail(String email);

    //    修改头像
    int updateImage(User user);

    String judgeImage(int uid);

    String delImage(int uid);

    int findIdentity(int uid);

    int updateUserInfo(Map<String,Object> map);

    String judgeEmail(String email);
}

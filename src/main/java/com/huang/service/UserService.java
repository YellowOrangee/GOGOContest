package com.huang.service;

import com.huang.pojo.Admin;
import com.huang.pojo.User;

import java.util.Map;


public interface UserService {

    int addUser(String name,String password,String email);

    User queryUserById( int id);

    User queryUserByName( String name);

    User Login(String name,String password);

    Admin adminLogin(String name, String password);

    String findIdentity(String name,String password);

    int updateUserInfo(Map<String,Object> map);

    String judgeEmail(String email);
}

package com.huang.service;

import com.huang.pojo.Admin;
import com.huang.pojo.User;

public interface UserService {

    int addUser(User user);

    User queryUserById( int id);

    User queryUserByName( String name);

    User Login(String name,String password);

    Admin adminLogin(String name, String password);

    String findIdentity(String name);
}

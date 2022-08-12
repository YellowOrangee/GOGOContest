package com.huang.service;

import com.huang.dao.UserMapper;
import com.huang.pojo.Admin;
import com.huang.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    //调用dao层的操作，设置一个set接口，方便Spring管理
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public int addUser(String name, String password, String email) {
        return this.userMapper.register(name,password,email);
    }

    @Override
    public User queryUserById(int id) {
        return this.userMapper.queryUserById(id);
    }

    @Override
    public String judgeEmail(String email) {
        return this.userMapper.judgeEmail(email);
    }

    @Override
    public User queryUserByName(String name) {
        return this.userMapper.queryUserByName(name);
    }

    @Override
    public User Login(String name, String password) {
        return this.userMapper.Login(name,password);
    }

    @Override
    public Admin adminLogin(String name, String password) {
        return this.userMapper.adminLogin(name,password);
    }

    @Override
    public String findIdentity(String name,String password) {
        return this.userMapper.findIdentity(name,password);
    }

    @Override
    public int updateUserInfo(Map<String, Object> map) {
        return userMapper.updateUserInfo(map);
    }




}

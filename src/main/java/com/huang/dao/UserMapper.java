package com.huang.dao;

import com.huang.pojo.Admin;
import com.huang.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {

    int register(@Param("name")String name,@Param("password")String password,@Param("email") String email);

//    查找用户ID
    User queryUserById(@Param("id") int id);

//    查找用户名
    User queryUserByName(@Param("name") String name);

//    判断登录用户身份
    int  findIdentity(@Param("uid") int uid);

//    根据邮箱查找密码
    String findPwdByEmail(@Param("email") String email);

//    用户登录
    User Login(@Param("name") String name,@Param("password") String password);

//    管理员登录
    Admin adminLogin(@Param("name") String name, @Param("password") String password);

//    修改用户信息
    int updateUserInfo(Map<String,Object> map);

//    修改头像
    int updateImage(User user);

//    判断用户是否已有头像
    String judgeImage(@Param("uid") int uid);

//    删除用户修改前的头像
    String delImage(@Param("uid") int uid);


    String judgeEmail(@Param("email") String email);

//    修改头像



}

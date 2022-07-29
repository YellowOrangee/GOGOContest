package com.huang.dao;

import com.huang.pojo.Admin;
import com.huang.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int addUser(User user);

    User queryUserById(@Param("id") int id);

    User queryUserByName(@Param("name") String name);

    String findIdentity(@Param("name") String name);

    User Login(@Param("name") String name,@Param("password") String password);

    Admin adminLogin(@Param("name") String name, @Param("password") String password);



}

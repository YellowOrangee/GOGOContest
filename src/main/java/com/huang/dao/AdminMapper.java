package com.huang.dao;

import com.huang.pojo.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {


    Admin adminLogin(@Param("name") String name, @Param("password") String password);




}

package com.huang.controller;

import com.huang.pojo.User;
import com.huang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ajaxController {




    @Autowired
    @Qualifier("UserServiceImpl")
    UserService userService;

    @RequestMapping("/a3")
    public String ajax3(String name,String password){
        String msg="";
        User user = userService.queryUserByName(name);
        //模拟数据库中存在数据
        if (name!=null){
            if (user.getU_name()==name){
                msg = "OK";
            }else {
                msg = "用户名输入错误";
            }
        }
        if (password!=null){
            if (user.getU_password()==password){
                msg = "OK";
            }else {
                msg = "密码输入有误";
            }
        }
        return msg; //由于@RestController注解，将msg转成json格式返回
    }
}

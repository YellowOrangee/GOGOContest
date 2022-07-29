package com.huang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.huang.pojo.Admin;
import com.huang.pojo.User;
import com.huang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    UserService userService;


    @RequestMapping(value="/register")
    public Map<String, Object> register(User user) {
        Map<String, Object> result = new HashMap<>();
        System.out.println("user"+user);

            if (userService.addUser(user) > 0) {
                result.put("success", true);
            }else {
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping("/login")
    public Map<String, Object> login(String name, String password, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

//        System.out.println(userService.findIdentity(name));
//        判断登录者身份
        if("1".equals(userService.findIdentity(name))){
//           进行普通用户登录
            User resultUser = userService.Login(name, password);
            System.out.println("进行普通用户登录");
            if (resultUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", resultUser);
                result.put("success", true);
            } else if (resultUser == null){
                result.put("success",false );
            }
        }else{
//          进行管理员登录
            Admin resultAdmin = userService.adminLogin(name, password);
            System.out.println("进行管理员登录");
            if (resultAdmin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", resultAdmin);
                result.put("success", true);
            } else if (resultAdmin == null){
                result.put("success",false );
            }
        }
        return result;
    }

//    判断ID是否可用
    @RequestMapping(value="/findUserById")
    public Map<String, Object> findUserById(int id) {
        Map<String, Object> result = new HashMap<>();

        //判断用户名是否已存在
        User user = userService.queryUserById(id);
        System.out.println(user);
        if (user == null) {
            result.put("success","ID可用!");
        }
        else {
            result.put("msg", "ID"+id+"已存在! 请修改后重试!");
        }
        return result;
    }

//    判断用户名是否可用
    @RequestMapping(value="/findUserByName")
    public Map<String, Object> findUserByName(String name) {
        Map<String, Object> result = new HashMap<>();

        //判断用户名是否已存在
        System.out.println(name);
        User user = userService.queryUserByName(name);
        System.out.println(user);
        if (user == null) {
            result.put("success","用户名可用!");
        }
        else {
            result.put("msg", "用户名"+name+"已存在! 请修改后重试!");
        }
        return result;
    }


//    用户个人信息界面,修改或填写个人信息
    @RequestMapping("/setInfo")
    public String setInfo(HttpServletRequest request){
        List<User> list =new ArrayList<>();

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        list.add(currentUser);
        System.out.println("user:"+currentUser);
//        使用fastJSON 将user对象转换为JSON字符串
//        return JSON.toJSONString(list, SerializerFeature.WriteMapNullValue); //显示值为空的字段 name:null
//        显示空字符串,并将null换为 "" 输出
        return JSON.toJSONString(list, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
    }
}

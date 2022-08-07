package com.huang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.huang.pojo.Admin;
import com.huang.pojo.User;
import com.huang.service.UserService;
import com.huang.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
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
    public Map<String, Object> register(String name,String password,String email) {
        Map<String, Object> result = new HashMap<>();

            if (userService.addUser(name,password,email) > 0) {
                result.put("success", true);
            }else {
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping("/login")
    public Map<String, Object> login(String name, String password,String rememberMe, HttpServletRequest request,
                                     HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> result = new HashMap<>();

        String userName=name;
        String userPwd=password;

        System.out.println("name:"+name);
        System.out.println("identity:"+userService.findIdentity(name));
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

//            三天免登录
            //添加cookie
            if (rememberMe != null) {
                CookieUtil.addCookie(userName,name,response);
                CookieUtil.addCookie(userPwd,password,response);
            }
        }
        return result;
    }

//    判断ID是否可用
    @RequestMapping(value="/findUserById",method = RequestMethod.GET)
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
    @RequestMapping(value="/findUserByName",method = RequestMethod.GET)
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

    //    判断邮箱是否可用
    @RequestMapping(value="/judgeEmail",method = RequestMethod.GET)
    public Map<String, Object> judgeEmail(String email) {
        Map<String, Object> result = new HashMap<>();

        //判断用户名是否已存在
        System.out.println(email);
        String resultEmail = userService.judgeEmail(email);
        System.out.println(resultEmail);
        if (resultEmail== null) {
            result.put("success",true);
        }
        else if(resultEmail != null){
            result.put("msg", "邮箱"+resultEmail+"已被注册! 请修改后重试!");
        }
        return result;
    }


//    用户个人信息界面,修改或填写个人信息
    @RequestMapping("/setInfo")
    public Map<String,Object> setInfo(String sex,String phone,String contactInfo,String school,
                          String major,String personalInfo,HttpServletRequest request){

        //        获取当前用户session信息
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        String name=currentUser.getU_name();
        System.out.println(name);

//        封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("sex",sex);
        map.put("phone",phone);
        map.put("contactInfo",contactInfo);
        map.put("school",school);
        map.put("major",major);
        map.put("personalInfo",personalInfo);
        map.put("name",name);

        System.out.println(map);

//        修改用户信息
        int flag=userService.updateUserInfo(map);

//        向前端返回修改是否成功信息
        Map<String,Object> result=new HashMap<>();
        if(flag > 0){
            result.put("success",true);
        }else{
            result.put("success",false);
        }
        return result;
    }

       //    展示个人信息
       @RequestMapping("/showInfo")
       public String showInfo(HttpServletRequest request){
           Map<String,Object> result=new HashMap<>();

       //       获取当前用户session信息
           HttpSession session = request.getSession();
           User currentUser = (User) session.getAttribute("currentUser");
           String name=currentUser.getU_name();
           System.out.println(name);

           User showUser = userService.queryUserByName(name);

       //        将用户信息放入list集合转为Json字符串
           List<User> list =new ArrayList<>();
           list.add(showUser);
           System.out.println("user:"+showUser);
           return JSON.toJSONString(list, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
       }
}

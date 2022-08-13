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


//    问题:如果用户表和管理员表中用户名相同,则优先登录用户表(改进:查询身份时,新增加根据代码查询条件)
    @RequestMapping("/login")
    public Map<String, Object> login(String name, String password,String rememberMe, HttpServletRequest request,
                                     HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> result = new HashMap<>();

        String userName=name;
        String userPwd=password;

        System.out.println("name:"+name);
        System.out.println("identity:"+userService.findIdentity(name,password));
//        判断登录者身份
        if("1".equals(userService.findIdentity(name,password))){
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
                session.setAttribute("currentAdmin", resultAdmin);
                System.out.println(resultAdmin);
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


    @RequestMapping(value="/showInfo",method = RequestMethod.GET,produces = {"text/plain;charset=UTF-8"})
    public String showUserInfo(HttpSession session){

//       获取当前用户session信息
        User currentUser = (User) session.getAttribute("currentUser");
        int uid = currentUser.getU_id();
        System.out.println(uid);

//        输出用户信息
        User showUser = userService.queryUserById(uid);
        //新建list数组
        List<User> list=new ArrayList<>();
        //将用户信息add到list数组中
        list.add(showUser);
        System.out.println("user:"+list);
        //调用fastJson的方法,将list集合中的数据转为JSON格式   该方法的返回值类型是String
        return JSON.toJSONString(list, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
                                       //确保对象的所有属性都被返回                 将返回数据值为null,替换为""

    }

//       修改头像
//    @RequestMapping(value = "/updateHeadPic")
//    public Map<String,String> updateHeadPic(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
//
//        Map<String,String> result=new HashMap<>();
//
//
//        User user= (User) session.getAttribute("currentUser");
//        String username=user.getU_name();
//        logger.info("/user/uploadHeadPic -> start - username: " + username);
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("profileFile");
//        logger.info("file content type: " + file.getContentType());
//        logger.info("file original name: " + file.getOriginalFilename());
//        logger.info("file name: " + file.getName());
//        if (null == file || file.isEmpty()) {
//            result.put("400", "文件不能为空");
//        }else {
//            result.put("200", "文件上传成功");
//            try {
//                user.setProfile(file.getBytes());
//                result = userService.updateProfileByUsername(user);
//            } catch (Exception e) {
//                logger.error("上传失败 - " + e.getMessage());
//                result.put("500","文件保存失败");
//            }
//            logger.info("/user/updateHeadPic -> end");
//        }
//        return result;
//    }

}

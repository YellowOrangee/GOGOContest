package com.huang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.huang.pojo.Admin;
import com.huang.pojo.User;
import com.huang.service.AdminService;
import com.huang.service.UserService;
import com.huang.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("AdminServiceImpl")
    AdminService adminService;

    @RequestMapping(value = "/register")
    public Map<String, Object> register(String name, String password, String email) {
        Map<String, Object> result = new HashMap<>();

        if (userService.addUser(name, password, email) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }


    //    问题:如果用户表和管理员表中用户名相同,则优先登录用户表(改进:查询身份时,新增加根据代码查询条件)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(String name, String password) {
        Map<String, Object> result = new HashMap<>();
        Map<String, String> map = new HashMap<>();

        User loginUser = userService.Login(name, password);

        //优先进行普通用户登录
        if (loginUser != null) {  //如果loginUser不为空,则进行用户登录

            map.put("id", String.valueOf(loginUser.getU_id()));
            map.put("name", loginUser.getU_name());

            //判断login是否为空
            //添加payload载荷
            try {
                //生成token
                String token = JwtUtil.getToken(map);
                //向前端返回code状态码msg信息和token
                result.put("state", 200);
                result.put("msg", "登陆成功!");
                result.put("token", token);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("state", 502);
                result.put("msg", e.getMessage());
            }
        } else if (loginUser == null) {   //用户表中无该用户,则进行管理员登录
            Admin adminLogin = adminService.adminLogin(name, password);
            if (adminLogin != null) {
                map.put("id", String.valueOf(adminLogin.getAd_id()));
                map.put("name", adminLogin.getAd_name());
                try {
                    //生成token
                    String token = JwtUtil.getToken(map);
                    //向前端返回code状态码msg信息和token
                    result.put("state", 200);
                    result.put("msg", "登陆成功!");
                    result.put("token", token);
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("state", 502);
                    result.put("msg", e.getMessage());
                }
            } else {
                result.put("state", 403);
                result.put("msg", "您还没有注册,请先注册");
            }
        }

        return result;
    }

    @RequestMapping(value = "/updateImage", method = RequestMethod.POST)
    public Map<String, Object> UpdateImage(MultipartFile file, HttpServletRequest request) throws Exception {
        Map<String, Object> result = new HashMap<>();
        int resultNum = 0;
        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int u_id = Integer.parseInt(verify.getClaim("id").asString());
        User user = new User();


        //保存数据库的路径
        String sqlPath = null;
        // 项目在容器中实际发布运行的根路径
        String realPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println(realPath);
        //定义 文件名
        String filename = null;
        if (!file.isEmpty()) {
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = file.getContentType();
            //获得文件后缀名
            String suffixName = contentType.substring(contentType.indexOf("/") + 1);
            //得到 文件名
            filename = uuid + "." + suffixName;
            //文件保存路径
            file.transferTo(new File(realPath + filename));
        }
        //把图片的相对路径保存至数据库
        sqlPath =filename;
        System.out.println(sqlPath);
        user.setU_image(sqlPath);
        user.setU_id(u_id);

        resultNum = userService.updateImage(user);

        if (resultNum > 0) {
            result.put("state", 200);
            result.put("msg", "修改成功");
            result.put("address", "http://60.205.2.92:8080/gogoContest/" + filename);
        } else {
            result.put("state", 500);
            result.put("msg", "修改失败,请稍后再试!");
        }

        return result;
    }


    @RequestMapping(value = "/findUserById", method = RequestMethod.GET)
    public Map<String, Object> findUserById(int id) {
        Map<String, Object> result = new HashMap<>();

        //判断用户名是否已存在
        User user = userService.queryUserById(id);
        System.out.println(user);
        if (user == null) {
            result.put("success", "ID可用!");
        } else {
            result.put("msg", "ID" + id + "已存在! 请修改后重试!");
        }
        return result;
    }

    //    判断邮箱是否可用
    @RequestMapping(value = "/judgeEmail", method = RequestMethod.GET)
    public Map<String, Object> judgeEmail(String email) {
        Map<String, Object> result = new HashMap<>();

        //判断用户名是否已存在
        System.out.println(email);
        String resultEmail = userService.judgeEmail(email);
        System.out.println(resultEmail);
        if (resultEmail == null) {
            result.put("success", true);
        } else if (resultEmail != null) {
            result.put("msg", "邮箱" + resultEmail + "已被注册! 请修改后重试!");
        }
        return result;
    }

    //    找回密码
    @RequestMapping(value = "/findPwd", method = RequestMethod.POST)
    public Map<String, Object> findPwd(String email) {
        Map<String, Object> result = new HashMap<>();
        String pwd = userService.findPwdByEmail(email);
        try {
            result.put("state", true);
            result.put("msg", pwd);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("state", false);
            result.put("msg", "系统繁忙,请稍后再试!");
        }

        return result;

    }


    //    用户个人信息界面,修改或填写个人信息
    @RequestMapping("/setInfo")
    public Map<String, Object> setInfo(String sex, String phone, String contactInfo, String school,
                                       String major, String personalInfo, HttpServletRequest request) throws UnsupportedEncodingException {


        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        String id = verify.getClaim("id").asString();
        String name = verify.getClaim("name").asString();
        System.out.println(id);
        System.out.println(name);
//        封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("sex", sex);
        map.put("phone", phone);
        map.put("contactInfo", contactInfo);
        map.put("school", school);
        map.put("major", major);
        map.put("personalInfo", personalInfo);
        map.put("name", name);

        System.out.println(map);

//        修改用户信息
        int flag = userService.updateUserInfo(map);

//        向前端返回修改是否成功信息
        Map<String, Object> result = new HashMap<>();
        if (flag > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }


    @RequestMapping(value = "/showInfo", method = RequestMethod.GET, produces = {"text/plain;charset=UTF-8"})
    public String showUserInfo(HttpServletRequest request) throws UnsupportedEncodingException {

        String token = request.getHeader("token");
        DecodedJWT verify = JwtUtil.verify(token);
        int id = Integer.parseInt(verify.getClaim("id").asString());
        System.out.println(id);

//        输出用户信息
        User showUser = userService.queryUserById(id);
        //新建list数组
        List<User> list = new ArrayList<>();
        //将用户信息add到list数组中
        list.add(showUser);
        System.out.println("user:" + list);
        //调用fastJson的方法,将list集合中的数据转为JSON格式   该方法的返回值类型是String
        return JSON.toJSONString(list, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
        //确保对象的所有属性都被返回                 将返回数据值为null,替换为""

    }
}

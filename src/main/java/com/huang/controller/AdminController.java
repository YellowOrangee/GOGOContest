package com.huang.controller;


import com.huang.pojo.Admin;
import com.huang.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    @Qualifier("AdminServiceImpl")
    AdminService adminService;

    //存储预返回页面的结果对象
    @RequestMapping("/adminLogin")
    public Map<String, Object> login(String name, String password, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
//          进行管理员登录
            Admin resultAdmin = adminService.adminLogin(name, password);
            System.out.println("进行管理员登录");
            if (resultAdmin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", resultAdmin);
                result.put("success", true);
            } else if (resultAdmin == null){
                result.put("success",false );
            }
        return result;
    }





}

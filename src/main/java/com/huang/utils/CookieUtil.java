package com.huang.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cookie工具类，提供了添加cookie、 读取cookie、删除cookie的方法。
 *
 */
public class CookieUtil {
    /**
     * 添加cookie的方法
     */
    public static void addCookie(String name, String value, HttpServletResponse response)
            throws UnsupportedEncodingException {

        Cookie c = new Cookie(name, URLEncoder.encode(value, "utf-8"));
        // 设置cookie的生存时间为三天
        c.setMaxAge(3*24*60*60);
        response.addCookie(c);
    }


    public static String findCookie(String name, HttpServletRequest request) throws UnsupportedEncodingException {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                String name1 = c.getName();
                if (name1.equals(name)) {
                    value = URLDecoder.decode(c.getValue(), "utf-8");
                }
            }
        }
        return value;
    }


    public static void deleteCookie(String name, String path, HttpServletResponse response) {
        Cookie c = new Cookie(name, "");
        c.setMaxAge(0);
        c.setPath(path);
        response.addCookie(c);
    }
}


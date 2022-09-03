package com.huang.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Map;

public class JwtUtil {
    private static final String SING="TVJGHFTY%&&^*";  //秘钥
    //    创建token
    public static String getToken(Map<String,String> map) throws UnsupportedEncodingException {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);  //默认7天过期
        //创建jwt  builder
        JWTCreator.Builder builder = JWT.create();
        //创建payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())  //指定令牌过期时间
                .sign(Algorithm.HMAC256(SING));//指定令牌的加密算法
        return token;
    }
    //    验证token并获取信息
    public static DecodedJWT verify(String token) throws UnsupportedEncodingException {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}


package com.huang.interceptor;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huang.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class jwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Map<String, Object> map = new HashMap<>();
        String uri = request.getRequestURI();
        if (uri.contains("/user/login") || uri.contains("/user/register")
                || uri.contains("/user/findPwd")) {

            return true;
        }else{
            try {
                JwtUtil.verify(token);
                return true;
            } catch (TokenExpiredException e) {
                map.put("state", 401);
                map.put("msg", "Token已经过期!!!");
            } catch (SignatureVerificationException e){
                map.put("state", 401);
                map.put("msg", "签名错误!!!");
            } catch (AlgorithmMismatchException e){
                map.put("state", 401);
                map.put("msg", "加密算法不匹配!!!");
            } catch (Exception e) {
                e.printStackTrace();
                map.put("state", 401);
                map.put("msg", "暂无token,请登录!");
            }
            String json = new ObjectMapper().writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);
            return false;
        }
    }
}






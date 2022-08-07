package com.huang.interceptor;//package com.huang.interceptor;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class LoginInterceptor implements HandlerInterceptor {
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//
////        获取请求路径
//        System.out.println("uri:"+request.getRequestURI());
//
////        用户请求进行登录时,放行
//        if(request.getRequestURI().contains("/user/login")){
////            return true 表示放行
//            return true;
//        }
//
////      判断用户是否已经登录
//
//        if(request.getSession().getAttribute("user")!=null){
//            return true;
//        }
//
//
//        // 用户没有登陆跳转到登陆页面
//        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
//        return false;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//}

package com.example.interceptor;

import com.example.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //return HandlerInterceptor.super.preHandle(request, response, handler);
        HttpSession session= request.getSession();
        User user=(User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/user/toLogin");
            return false;
        }
        return true;
    }
}

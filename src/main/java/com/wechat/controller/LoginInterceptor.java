package com.wechat.controller;

import com.wechat.common.ServerResponse;
import com.wechat.dao.UserMapper;
import com.wechat.service.impl.UserServiceImpl;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String userId = request.getHeader("userId");
        response.setContentType("application/json;charset=UTF-8");
        if (null != userId) {
            System.out.println(userId);
            if (userMapper.countUserById(userId) > 0)
                return true;
            System.out.println("拦截器错误");
            String responses = ServerResponse.createByErrorMessage("参数错误").toString();
           // JSONObject json = (JSONObject) new JSONParser().parse(responses);
            response.getWriter().write(responses);
            return false;
        }

        response.getWriter().print(ServerResponse.createByErrorMessage("缺少参数"));
        response.getWriter().close();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}

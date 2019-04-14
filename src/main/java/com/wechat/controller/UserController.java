package com.wechat.controller;


import com.wechat.common.ServerResponse;

import com.wechat.service.PostService;
import com.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;



@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public ServerResponse<String> login(String code){
        if (code == null)
            return ServerResponse.createByErrorMessage("参数失败");
        ServerResponse<String> response = userService.login(code);
        return response;
    }

    @RequestMapping(value = "/getuserinfo")
    @ResponseBody
    public ServerResponse<String> getuserinfo(String encryptedData,String iv, String userId){
           return  userService.getUserById(userId,encryptedData,iv);
    }





}

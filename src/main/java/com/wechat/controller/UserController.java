package com.wechat.controller;


import com.wechat.common.ServerResponse;

import com.wechat.service.PostService;
import com.wechat.service.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



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

    @RequestMapping(value = "/getuser")
    @ResponseBody
    public ServerResponse<String> getuserinfo(String userInfo, @RequestHeader("userId") String userId){
        System.out.println(userInfo);
        JSONObject jsonObject = null;
        try {
           jsonObject = (JSONObject) (new JSONParser().parse(userInfo));
            if (jsonObject == null) {
                return ServerResponse.createByErrorMessage("参数错误");
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("参数错误");
        }
       /* System.out.println(encryptedData);
        System.out.println(iv);
        if (encryptedData == null || iv == null) {
            return ServerResponse.createByErrorMessage("获取参数失败");
        }*/
           return  userService.getUserById(userId,jsonObject);
    }
    @RequestMapping(value = "/getuserInfo")
    @ResponseBody
    public ServerResponse<String> getuserinfo(String iv,String encryptedData,  @RequestHeader("userId") String userId){

        System.out.println(encryptedData);
        System.out.println(iv);
        if (encryptedData == null || iv == null) {
            return ServerResponse.createByErrorMessage("获取参数失败");
        }
        return  userService.getUserById(userId,encryptedData,iv);
    }






}

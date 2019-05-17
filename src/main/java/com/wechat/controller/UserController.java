package com.wechat.controller;


import com.mysql.fabric.Server;
import com.wechat.common.ServerResponse;

import com.wechat.service.PostService;
import com.wechat.service.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "/getuser")
    @ResponseBody
    public ServerResponse<String> getuserinfo(String userInfo, @RequestHeader("userId") String userId){
        System.out.println(userInfo);
        if (userInfo == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
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


    @RequestMapping(value = "/test")
    @ResponseBody
    public ServerResponse<String> test(String imgUrl,@RequestHeader("userId") String userId) {

        return userService.saveAvtaUrl(imgUrl,userId);
        //return null;
    }

    @RequestMapping(value = "communicateBySeesion")
    @ResponseBody
    public ServerResponse communicateBySeesion(String sessionId,String start,String end) {
        if (sessionId == null || start == null || end == null) {
            return ServerResponse.createByErrorMessage("没有此对话");
        }

        return userService.communicateBySeesion(sessionId,start,end);

    }
    @RequestMapping(value = "/applyToDoctor" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse applyToDoctor(@RequestHeader("userId")String userId, String desc, HttpServletRequest request, @RequestParam("images")MultipartFile[] images) throws Exception {


        return userService.applyToDoctor(userId,desc,images);
    }
}

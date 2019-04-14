package com.wechat.controller;

import com.wechat.common.ServerResponse;
import com.wechat.pojo.Post;
import com.wechat.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    //通过用户获取帖子
    @RequestMapping(value = "/getAllPostByUSer")
    @ResponseBody
    public ServerResponse<List<Post>> getAllPostByUSer(String userid){
        //假设可以获取(userid已存在)
        return  postService.getAllPostByUSer(userid);
    }

    //获取所有帖子
    @RequestMapping(value = "/getAllPost")
    @ResponseBody
    public ServerResponse getAllPost(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "5")int pageSize){
        //假设可以获取(userid已存在)
        return  postService.getAllPost(pageNum,pageSize);
    }

   /* //获取所有帖子
    @RequestMapping(value = "/deletPostByUser")
    @ResponseBody
    public ServerResponse<String> deletPostByUser(String postId){
        //假设可以获取(userid已存在)
        if (postId == null) {
            return  ServerResponse.createByErrorMessage("参数错误");
        }
        int id = Integer.parseInt(postId)
        return  postService.deletPostById(id);
    }*/

}

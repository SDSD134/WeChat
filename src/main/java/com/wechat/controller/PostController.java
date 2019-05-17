package com.wechat.controller;

import com.wechat.common.ServerResponse;
import com.wechat.pojo.Post;
import com.wechat.service.CommentService;
import com.wechat.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    //通过用户获取帖子
    @RequestMapping(value = "/getAllPostByUSer")
    @ResponseBody
    public ServerResponse<List<Post>> getAllPostByUSer(@RequestHeader String userid){
        //假设可以获取(userid已存在)
        return  postService.getAllPostByUSer(userid);
    }

    //获取所有帖子
    @RequestMapping(value = "/getAllPost")
    @ResponseBody
    public ServerResponse getAllPost(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "5")int pageSize
                                     ){
        //假设可以获取(userid已存在)
        return  postService.getAllPost(pageNum,pageSize);
    }

    //删除所有帖子
    @RequestMapping(value = "/deletPostByUser")
    @ResponseBody
    public ServerResponse<String> deletPostByUser(String postId){
        //假设可以获取(userid已存在)
        if (postId == null) {
            return  ServerResponse.createByErrorMessage("参数错误");
        }
        int id = Integer.parseInt(postId);
        return  postService.deletePostById(id);
    }

    //增加阅读数量,进行点赞，待改进
    @RequestMapping(value = "/addCountById")
    @ResponseBody
    public ServerResponse<String> addPostReadById(String postId,@RequestHeader String userId){
        Integer id = Integer.parseInt(postId);
        if (id == null)
            return ServerResponse.createByErrorMessage("参数错误");
       return postService.addPostReadOrPraise(id,userId,"praisePost");

    }

    //添加帖子
    @RequestMapping(value = "/addPostById")
    @ResponseBody
    public ServerResponse addPostById(@RequestHeader String userId,Post post,
                                              @RequestParam(value = "postImages") MultipartFile[] postImages) {
        if (post == null) {
            return ServerResponse.createByErrorMessage("帖子参数错误");
        }
        post.setUserId(userId);
        return postService.addPostById(post,postImages);
    }

    //增加阅读数量,进行点赞，待改进
    @RequestMapping(value = "/readPost")
    @ResponseBody
    public ServerResponse readPost(String postId){
        if (postId == null)
            return ServerResponse.createByErrorMessage("参数错误");
       postService.addPostReadOrPraise(Integer.parseInt(postId),null ,"readCount");
        return  commentService.listCommentByPost(postId);
    }





}

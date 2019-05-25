package com.wechat.controller;

import com.wechat.common.ServerResponse;
import com.wechat.pojo.Post;
import com.wechat.service.CommentService;
import com.wechat.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
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
    @RequestMapping(value = "/getAllPostByUser")
    @ResponseBody
    public ServerResponse<List<Post>> getAllPostByUSer(@RequestHeader String userId,
                                                       @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                                       @RequestParam(value = "pageSize",defaultValue = "5")int pageSize){
        //假设可以获取(userid已存在)
        return  postService.getAllPostByUSer(userId,pageNum,pageSize);
    }

    //获取所有帖子
    @RequestMapping(value = "/getAllPost")
    @ResponseBody
    public ServerResponse getAllPost(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "5")int pageSize,
                                     @RequestHeader String userId
                                     ){
        //假设可以获取(userid已存在)
        return  postService.getAllPost(pageNum,pageSize,userId);
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
    public ServerResponse<String>  addPostById(@RequestHeader String userId,Post post,
                                      @RequestParam(value = "postImages",required = false) MultipartFile[] postImages) {
        if (post.getPostContent() == null || post.getPostContent() == "") {
            return ServerResponse.createByErrorMessage("帖子参数错误");
        }
        System.out.println(post.getPostId());
        post.setUserId(userId);
        return postService.addPostById(post,postImages);
    }

    //增加阅读数量,并返回一级评论
    @RequestMapping(value = "/readPost")
    @ResponseBody
    public ServerResponse readPost(String postId,@RequestHeader String userId){
        if (postId == null)
            return ServerResponse.createByErrorMessage("参数错误");
       postService.addPostReadOrPraise(Integer.parseInt(postId),userId ,"readCount");
        return  commentService.listCommentByPost(postId);
    }





}

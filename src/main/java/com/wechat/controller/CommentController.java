package com.wechat.controller;

import com.wechat.common.ServerResponse;
import com.wechat.dao.ReplyMapper;
import com.wechat.pojo.Comment;
import com.wechat.pojo.Reply;
import com.wechat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/comment")
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;


    //评论帖子
    @RequestMapping("/commentPost")
    @ResponseBody
    public ServerResponse commentPost(Comment comment,@RequestHeader String userId) {
        if (comment == null) {
            return ServerResponse.createByErrorMessage("添加失败");
        }
        if (comment.getPostId() == null && comment.getCommentContext() == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        comment.setUserId(userId);
        return commentService.commentPost(comment);
    }

    //评论
    @RequestMapping("/reply")
    @ResponseBody
    public ServerResponse reply(Reply reply, @RequestHeader String userId) {
        if (reply == null) {
            return ServerResponse.createByErrorMessage("添加失败");
        }
        if (reply.getCommentId() == null && reply.getReplyContext() == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        reply.setUserId(userId);
        return commentService.addReply(reply,userId);
    }

    //删除回复
    @RequestMapping("/deleteReplyByID")
    @ResponseBody
    public ServerResponse deleteReplyByID(String replyId,String commentId) {
        if (replyId == null && commentId == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return commentService.deleteReplyById(replyId,commentId);
    }

    /*//通过帖子获取评论
    @RequestMapping("/listCommentByPost")
    @ResponseBody
    public ServerResponse listCommentByPost(String postId){
        if (postId == null) {
            return ServerResponse.createByErrorMessage("用户参数错误");
        }
        return commentService.listCommentByPost(postId);

    }*/

    @RequestMapping("/getCommentReply")
    @ResponseBody
    public ServerResponse getCommentReply(Comment comment) {
        if (comment== null && comment.getCommentId() == null &&
                comment.getPostId() == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return commentService.getCommentReply(comment);
    }



}

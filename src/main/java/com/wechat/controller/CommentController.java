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


@Controller
@RequestMapping("/comment")
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

    //删除一级评论
    @RequestMapping("/deleteCommentById")
    @ResponseBody
    public ServerResponse deleteCommentById(String commentId,@RequestHeader String userId)  {
        if (commentId == null) {
            return ServerResponse.createByErrorMessage("回复参数错误");
        }
        return commentService.deleteCommentById(commentId,userId);

    }

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

package com.wechat.dao;

import com.wechat.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentMapper {

    //添加评论
    Integer insertCommentPost(Comment comment);

    //获取帖子的一级评论
    List<Comment> listCommentByPost(String postId);

    //获取一级评论的评论
    Comment getCommentReply(Integer commentId);

    //获取评论
    Comment getComment(@Param("commentId") Integer commentId, @Param("postId") Integer postId);

    //增加评论数量
    Integer updatePlusComment(Integer commentId);

    //减少评论数量
    Integer updateReduceComment(Integer commentId);

    Integer updatePostReward(Integer postId);
}

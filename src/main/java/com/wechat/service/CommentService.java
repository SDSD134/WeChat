package com.wechat.service;

import com.wechat.common.ServerResponse;
import com.wechat.pojo.Comment;
import com.wechat.pojo.Reply;


public interface CommentService {
    ServerResponse<String> commentPost(Comment comment);

    ServerResponse addReply(Reply reply, String userId);

    ServerResponse deleteReplyById(String replyId,String commentId);

    ServerResponse listCommentByPost(Comment comment);

    ServerResponse getCommentReply(Comment comment);
}

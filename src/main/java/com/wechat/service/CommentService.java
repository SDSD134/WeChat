package com.wechat.service;

import com.wechat.common.ServerResponse;


public interface CommentService {
    ServerResponse<String> commentPost(String postId,String conntext);
}

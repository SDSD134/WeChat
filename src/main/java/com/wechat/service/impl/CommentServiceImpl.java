package com.wechat.service.impl;

import com.wechat.common.ServerResponse;
import com.wechat.dao.CommentMapper;
import com.wechat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public ServerResponse<String> commentPost(String postId, String conntext) {
        return null;
    }
}

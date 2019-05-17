package com.wechat.dao;

import com.wechat.pojo.Reply;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReplyMapper {
    Integer insertReply(Reply reply);

    Integer deleteReplyById(Integer replyId);

    List<Reply> listGetReply(Integer commentId);
}

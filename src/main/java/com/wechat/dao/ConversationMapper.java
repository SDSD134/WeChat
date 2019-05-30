package com.wechat.dao;

import com.wechat.pojo.Conversation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConversationMapper {
    List<Conversation> listConversation(@Param("doctorId") Integer doctorId);
}

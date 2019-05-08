package com.wechat.dao;

import com.wechat.pojo.Post;
import com.wechat.pojo.User;
import com.wechat.pojo.WSMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UserMapper {
    int countUserById(String userId);

    User findUserById(String userId);

    int insertUser(User user);

    int updateUserByID(User user);

    WSMessage getWsMessage(@Param("fromUserId") String fromuserId, @Param("toUserId") String toUserId);

}

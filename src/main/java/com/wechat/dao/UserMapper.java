package com.wechat.dao;

import com.wechat.pojo.User;
import org.springframework.stereotype.Component;


@Component
public interface UserMapper {
    int countUserById(String userId);

    User findUserById(String userId);

    int insertUser(User user);

    int updateUerByID(User user);

}

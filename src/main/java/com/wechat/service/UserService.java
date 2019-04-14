package com.wechat.service;


import com.wechat.common.ServerResponse;
import com.wechat.pojo.User;

public interface UserService {

    ServerResponse<String> login(String code);

    ServerResponse<String> getUserById (String userId,String encryptedData,String iv);

    ServerResponse<String> getAllPostById(String userid);
}

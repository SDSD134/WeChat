package com.wechat.service;


import com.wechat.common.ServerResponse;
import com.wechat.pojo.Post;
import com.wechat.pojo.User;
import org.json.simple.JSONObject;

import java.util.List;

public interface UserService {

    ServerResponse<String> login(String code);

    ServerResponse<String> getUserById (String userId,String encryptedData,String iv);
    ServerResponse<String> getUserById (String userId,JSONObject userInfo);


}

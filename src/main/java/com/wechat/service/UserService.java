package com.wechat.service;


import com.wechat.common.ServerResponse;
import com.wechat.pojo.User;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;




public interface UserService {

    ServerResponse<String> login(String code);

    ServerResponse<String> getUserById (String userId,String encryptedData,String iv);
    ServerResponse<String> getUserById (String userId,JSONObject userInfo);

    ServerResponse<User> managerLogin(String username,String password);
    ServerResponse<String> saveAvtaUrl (String avatarUrl,String userId);
    ServerResponse communicateBySeesion(String sessionId,String start,String stop );


    ServerResponse applyToDoctor(String userId, String desc, MultipartFile[] images) throws Exception;
}

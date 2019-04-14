package com.wechat.service.impl;


import com.wechat.common.ServerResponse;

import com.wechat.dao.UserMapper;
import com.wechat.pojo.User;
import com.wechat.service.UserService;
import com.wechat.util.HttpURLConnection;

import com.wechat.util.encryptedDataUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Value("${APPID}")
    private String APPID;
    @Value("${SECRET}")
    private  String SECRET;
    @Value("${LOGIN_URL}")
    private String LOGIN_URL;
    public ServerResponse<String> login(String code){
        String params = "appid=" + APPID + "&secret=" + SECRET + "&js_code=" + code +
                "&grant_type=authorization_code";
        String result = HttpURLConnection.sendGet(LOGIN_URL,params);
        JSONObject json = null;
        String session_key=null;
        String openid = null;
        User user = new User();
        System.out.println("进入");
        try {
             json = (JSONObject) (new JSONParser().parse(result));
            session_key = (String) json.get("session_key");
            openid = (String) json.get("openid");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //获取失败返回错误信息
        if (session_key == null|| openid == null ) {
            String errormsg = (String) json.get("errmsg");
            return ServerResponse.createByErrorMessage("授权失败");
        }
        //String sessionId = UUID.randomUUID().toString().replace("-", "");
        //对openid使用MD5加密
        String sessionId = DigestUtils.md5Hex(openid);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setSessionkey(session_key);
        user.setUserId(sessionId);
        user.setOpenid(openid);
        //数据库中是否有这个用户
        int count = userMapper.countUserById(sessionId);
        System.out.println(sessionId);
        if (count > 0) {
            int updateResult = userMapper.updateUerByID(user);
            if (updateResult > 0)
                return ServerResponse.createBySuccess("更新用户成功");
            return ServerResponse.createByErrorMessage("更新用户失败");
        }
        //没有这个用户，加入用户信息
        int insertResult = userMapper.insertUser(user);
        if (!(insertResult>0)) {
            return ServerResponse.createByErrorMessage("添加数据库失败");
        }
        return ServerResponse.createBySuccess(sessionId);

    }

    @Override
    public ServerResponse<String> getUserById(String userId,String encryptedData,String iv) {
        int result;
        try {
           result = userMapper.countUserById(userId);
            if (!(result > 0)) {
                return ServerResponse.createByErrorMessage("未登录，请重新登录");
            }
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未登录，请重新登录");
        }

        //存在该用户
        User user = userMapper.findUserById(userId);
        //解密
        String info = null;
        JSONObject json = null;
        try {
            info = encryptedDataUtil.decryptData(encryptedData,user.getSessionkey(),iv);
            //转换为json
            json = (JSONObject) (new JSONParser().parse(info));
        } catch (ParseException e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("解密错误");
        }
        if (json == null) {
            return ServerResponse.createByErrorMessage("解密失败");
        }

        int gender = (Integer)json.get("gender");
        String nickName = (String)json.get("nickName");
        String city = (String)json.get("city");
        String province = (String) json.get("province");
        String country = (String)json.get("country");
        String avatarUrl =(String) json.get("avatarUrl");
        JSONObject watermark = null;
        try {
            watermark = (JSONObject) (new JSONParser().parse((String) json.get("watermark")));
            Date timestamp = (Date) watermark.get("timestamp");
            Date currenttime = new Date();
            if ((currenttime.getTime() - timestamp.getTime())/(1000*60*60*24) >= 2) {
                return ServerResponse.createByErrorMessage("授权时间过长");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        user.setAddress(city + province + country);
        user.setNickName(nickName);
        user.setAvatarUrl(avatarUrl);
        user.setGender(gender);
        int count = userMapper.updateUerByID(user);
        if (!(count > 0)) {
            return ServerResponse.createByErrorMessage("更新失败");
        }

        return ServerResponse.createBySuccessMessage("授权成功");
    }

    @Override
    public ServerResponse<String> getAllPostById(String userid) {
        return null;
    }

}

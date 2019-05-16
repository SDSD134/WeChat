package com.wechat.service.impl;


import com.mysql.fabric.Server;
import com.wechat.common.ServerResponse;

import com.wechat.dao.PostMapper;
import com.wechat.dao.UserMapper;
import com.wechat.pojo.Post;
import com.wechat.pojo.User;
import com.wechat.service.UserService;
import com.wechat.util.DownLoad;
import com.wechat.util.HttpURLConnection;

import com.wechat.util.RedisUtil;
import com.wechat.util.aliyunoss.OSSClientUtil;
import com.wechat.util.encryptedDataUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import redis.clients.jedis.Jedis;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PostMapper postMapper;
    @Value("${APPID}")
    private String APPID;
    @Value("${SECRET}")
    private  String SECRET;
    @Value("${LOGIN_URL}")
    private String LOGIN_URL;

    //用户登录
    public ServerResponse<String> login(String code){
        String params = "appid=" + APPID + "&secret=" + SECRET + "&js_code=" + code +
                "&grant_type=authorization_code";
        String result = HttpURLConnection.sendGet(LOGIN_URL,params);
        JSONObject json = null;
        String session_key=null;
        String openid = null;
        User user = new User();
        System.out.println("进入");
        System.out.println(result);
        try {
            json = (JSONObject) (new JSONParser().parse(result));
            session_key = (String) json.get("session_key");
            openid = (String) json.get("openid");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //获取失败返回错误信息
        if (session_key == null|| openid == null ) {
         //   String errormsg = (String) json.get("errmsg");
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
            int updateResult = userMapper.updateUserByID(user);
            if (updateResult > 0)
                return ServerResponse.createBySuccess("更新用户成功",sessionId);
            return ServerResponse.createByErrorMessage("更新用户失败");
        }
        //没有这个用户，加入用户信息
        int insertResult = userMapper.insertUser(user);
        if (!(insertResult>0)) {
            return ServerResponse.createByErrorMessage("添加数据库失败");
        }
        System.out.println("login成功");
        return ServerResponse.createBySuccess(sessionId);

    }

    //获取用户信息
    @Override
    public ServerResponse<String> getUserById(String userId,JSONObject userInfo) {
        User user = new User();
        Long gender2 = (Long) userInfo.get("gender");
        String gender = String.valueOf(gender2);

        String nickNames = (String)userInfo.get("nickName");
       /* try {
            nickNames=new String(nickNames.getBytes("iso-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

        System.out.println(nickNames);
        String city = "";
        if(userInfo.get("city") != null) {
            city = (String)userInfo.get("city");
        }
        String province="";
        if(userInfo.get("province") != null) {
            province = (String)userInfo.get("province");
        }
        String country = "";
        if(userInfo.get("country") != null) {
            country = (String)userInfo.get("country");
        }
        String avatarUrl =(String) userInfo.get("avatarUrl");
        OSSClientUtil util = null;
        try {
           // String fileName = avatarUrl.substring(avatarUrl.lastIndexOf("."));
            String fileName = userId ;
            DownLoad downLoad = new DownLoad();

            for (int i = 0; i<3; i++){
                InputStream stream = downLoad.getImageStream(avatarUrl);
                if (stream != null){
                    util = new OSSClientUtil();
                    try {
                        avatarUrl = util.uploadFile2OSS(stream,fileName);
                        break;
                    }catch (Exception e) {
                        if (i == 3) {
                            util.destory();
                            return ServerResponse.createByErrorMessage("授权失败");
                        }

                    }

                }
                user.setAvatarUrl(avatarUrl);
            }

           /* else {
                user.setAvatarUrl(avatarUrl);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println(avatarUrl);
            util.destory();
        }
       /* JSONObject watermark = null;
        try {
            watermark = (JSONObject) userInfo.get("watermark");
            Long timestampLong = (long) watermark.get("timestamp");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timestamp = new Date(timestampLong);
            Date currenttime = new Date();
            if ((currenttime.getTime() - timestamp.getTime())/(1000*60*60*24) >= 2) {
                return ServerResponse.createByErrorMessage("授权时间过长");
            }
        } catch (Exception e) {
            e.printStackTrace();
          // return ServerResponse.createByErrorMessage("授权错误");
        }*/
        user.setAddress(city + province + country);
        user.setNickName(nickNames);
        user.setGender(gender);
        user.setUserId(userId);
        int count = userMapper.updateUserByID(user);
        if (!(count > 0)) {
            return ServerResponse.createByErrorMessage("更新失败");
        }

        System.out.println("获取成功");
        return ServerResponse.createBySuccessMessage("授权成功");

    }


    public ServerResponse<String> saveAvtaUrl(String avatarUrl,@RequestHeader String userId) {
        OSSClientUtil util = new OSSClientUtil();
        try {
           /* String fileName = avatarUrl.substring(avatarUrl.lastIndexOf("/"));
            System.out.println(fileName);
            fileName = userId + "." + fileName;*/
           String fileName = userId;
            DownLoad downLoad = new DownLoad();
            InputStream stream = downLoad.getImageStream(avatarUrl);
            if (stream != null){
                System.out.println(fileName);
                avatarUrl = util.uploadFile2OSS(stream,fileName);
                return ServerResponse.createBySuccess("成功",avatarUrl);
            }
            else {
                return ServerResponse.createByErrorMessage("没有获取流");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("获取流错误");
        }
        finally {
            util.destory();
        }
    }

    @Override
    public ServerResponse communicateBySeesion(String sessionId,String start ,String stop) {
        Jedis jedis = RedisUtil.getJedis();
        boolean isExits = jedis.exists(sessionId);
        if (!isExits) {
            return ServerResponse.createByErrorMessage("此对话没有消息");
        }
        List<String> list = jedis.lrange(sessionId,Integer.parseInt(start),Integer.parseInt(stop));
        jedis.close();
        return ServerResponse.createBySuccess(list);

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
        System.out.println(userId);

        if (userId == null || encryptedData == null || iv==null) {
            return ServerResponse.createByErrorMessage("参数传递失败");
        }
        //存在该用户
        User user = null;
        //解密
        String info = null;
        JSONObject json = null;
        try {
            user = userMapper.findUserById(userId);
            info = encryptedDataUtil.decryptData(encryptedData,user.getSessionkey(),iv);
            //转换为json
            json = (JSONObject) (new JSONParser().parse(info));
            System.out.println(info);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("解密错误");
        }
        if (json == null) {
            return ServerResponse.createByErrorMessage("解密失败");
        }

        long gender2 = (Long) json.get("gender");
        String gender = String.valueOf(gender2);
        String nickName = (String)json.get("nickName");
        String city = (String)json.get("city");
        String province = (String) json.get("province");
        String country = (String)json.get("country");
        String avatarUrl =(String) json.get("avatarUrl");
        System.out.println(avatarUrl);
        JSONObject watermark = null;
        try {
            watermark = (JSONObject) json.get("watermark");
            long timestampLong = (long) watermark.get("timestamp");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timestamp = new Date(timestampLong);
            Date currenttime = new Date();
            if ((currenttime.getTime() - timestamp.getTime())/(1000*60*60*24) >= 2) {
                return ServerResponse.createByErrorMessage("授权时间过长");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setAddress(city + province + country);
        user.setNickName(nickName);
        user.setAvatarUrl(avatarUrl);
        user.setGender(gender);
        int count = userMapper.updateUserByID(user);
        if (!(count > 0)) {
            return ServerResponse.createByErrorMessage("更新失败");
        }

        System.out.println("获取成功");
        return ServerResponse.createBySuccessMessage("授权成功");
    }




}

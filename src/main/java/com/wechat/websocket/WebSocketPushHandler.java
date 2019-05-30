package com.wechat.websocket;



import com.wechat.common.ServerResponse;
import com.wechat.pojo.WSMessage;
import com.wechat.dao.UserMapper;
import com.wechat.util.RedisUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.*;

//创建处理器
@Component
public class WebSocketPushHandler implements WebSocketHandler {
    //userMap存放的是在线用户的信息，当用户下线就从userMap里踢出去。
    @Autowired
    private UserMapper userMapper;
   // private static final List<WebSocketSession> users = new ArrayList<>();
    private final static Map<String,WebSocketSession> userMap = new HashMap<String,WebSocketSession>();


    @Override
    //在WebSocket协商成功并且WebSocket连接打开并准备好使用后调用
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
       // users.add(webSocketSession);
       /* String userId = getUserId(webSocketSession);*/
       String userId =(String) webSocketSession.getAttributes().get("userId");

        if (userMap.get(userId) == null) {
            userMap.put(userId,webSocketSession);
            System.out.println(userId + "用户未连接建立连接成功");
        }
        System.out.println(userId + "用户已经连接");
        //sendMessageToUser(userId,new TextMessage("连接成功"));
        /*Jedis jedis = RedisUtil.getJedis();
        try {
            //用户来连接后查看是否有没有接受的消息
            Long count = jedis.llen(userId);
            if (count > 0) {
                List<String> list = jedis.lrange(userId,0,-1);
                jedis.del(userId);
                sendMessageToUser(userId,new TextMessage(list.toString()));
            return;
            }

        } catch (Exception e) {

        }
        finally {
            jedis.close();
        }*/



    }

    @Override
    //新的websocket消息到达时调用，调用此方法传输数据
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if (webSocketMessage.getPayloadLength()==0) {
            return;
        }
        //将管道中数据转为json格式
        JSONObject json = null;
        try {
            System.out.println(webSocketMessage.getPayload().toString());
            json = (JSONObject) new JSONParser().parse(webSocketMessage.getPayload().toString());
        } catch (ParseException e){
            sendMessageToUser(webSocketSession,new TextMessage(ServerResponse.createByErrorMessage("传输失败").toString()));
            e.printStackTrace();
            return;
        }
        System.out.println(json);
        //获取管道中的数据，存入对象中
        WSMessage wsMessage = new WSMessage();
        String fromUser = (String) json.get("fromUser");
        String toUser = (String) json.get("toUser");
        //判断数据库中是否有该用户
        Integer to = userMapper.countUserById(toUser);
        if (fromUser == null || toUser == null || !(to > 0)) {
            sendMessageToUser(webSocketSession,new TextMessage(ServerResponse.createByErrorMessage("不存在该用户").toString()));
            return;
        }
        String message =(String) json.get("message");
        String messageType = (String) json.get("messageType");
        wsMessage.setFromUser(fromUser);
        wsMessage.setToUser(toUser);
        String sessioId = fromUser + toUser;
        wsMessage.setMessage(message);
        wsMessage.setMessageType(messageType);
        WSMessage wsMessageInfo = userMapper.getWsMessage(wsMessage.getFromUser(),wsMessage.getToUser());
        if (wsMessage != null) {
            wsMessage.setFromUserImg(wsMessageInfo.getFromUserImg());
            wsMessage.setFromUserName(wsMessageInfo.getFromUserName());
            wsMessage.setToUserImg(wsMessageInfo.getToUserImg());
            wsMessage.setToUsername(wsMessageInfo.getToUsername());
        }
        System.out.println(wsMessage);
        //映射错误，失败
        if (wsMessage.getFromUser() == null && wsMessageInfo == null) {
            sendMessageToUser(webSocketSession,new TextMessage(ServerResponse.createByErrorMessage("传输失败").toString()));
            return;
        }
        Jedis jedis = RedisUtil.getJedis();
        //成员不在线上则把信息存入redis,等待发送
        if (isOpen(toUser) == null) {
            if (jedis == null) {
                sendMessageToUser(webSocketSession,new TextMessage(ServerResponse.createByErrorMessage("传输失败").toString()));
                return;
            }
            try {
               // jedis.lpush(toUser,wsMessage.toString());
                jedis.lpush(sessioId,wsMessage.toString());
            } catch (Exception e){
                jedis.close();
                sendMessageToUser(webSocketSession,new TextMessage(ServerResponse.createByErrorMessage("发送失败").toString()));
                return ;
            }

            sendMessageToUser(webSocketSession,new TextMessage(ServerResponse.createByErrorMessage("该用户不在线").toString()));
            return;
        }
       // JSONObject object = (JSONObject) new JSONParser().parse(wsMessage.toString());

        jedis.lpush(sessioId,wsMessage.toString());
        jedis.close();


        //成员在线上且发送成功
        sendMessageToUser(userMap.get(wsMessage.getToUser()),new TextMessage(wsMessage.toString()));
        System.out.println("已发送");



    }

    // 后台错误信息处理方法
    //处理底层WebSocket消息传输中的错误
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    // 用户退出后的处理，不如退出之后，要将用户信息从websocket的session中remove掉，这样用户就处于离线状态了，也不会占用系统资源
    //websocket连接被任何一方关闭后或者发生传输错误后调用
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        String userId=this.getUserId(webSocketSession);
        if (userMap.get(userId) != null) {
            sendMessagesToUsers(new TextMessage(userMap.get(userId) + "下线"));
            userMap.remove(userId);
        }
        System.out.println(userId + "安全退出了系统");


    }

    @Override
   // WebSocketHandler是否处理部分消息
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有的用户发送消息
     */
    public void sendMessagesToUsers(TextMessage message) {
       /* for (WebSocketSession user : users) {
            try {
                // isOpen()在线就发送
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        Set<String> userIds = userMap.keySet();
        for (String userId:userIds) {
            System.out.println(userId);
            sendMessageToUser(userId,message);
        }
    }

    /**
     * 发送消息给指定的用户
     */
    public void sendMessageToUser(String userId, TextMessage message) {
       /* for (WebSocketSession user : users) {
            if (user.getAttributes().get("").equals(userId)) {
                try {
                    // isOpen()在线就发送
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
       WebSocketSession socketSession = isOpen(userId);
        if (socketSession != null) {
            try {
                socketSession.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    public void sendMessageToUser(WebSocketSession webSocketSession,TextMessage message) throws IOException {
        webSocketSession.sendMessage(message);
    }

    //获取用户id
    public String getUserId(WebSocketSession session) {
        try {
           // return String.valueOf(((User)session.getAttributes().get("user")).getUserId());
            return (String) session.getAttributes().get("userId");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public WebSocketSession isOpen(String userId) {
        WebSocketSession session = userMap.get(userId);
        if (session != null && session.isOpen())
            return session;
        return null;
    }

}

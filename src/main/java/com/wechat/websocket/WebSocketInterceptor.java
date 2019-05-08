package com.wechat.websocket;

import com.wechat.dao.UserMapper;
import com.wechat.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

//WebSocket握手请求的拦截器. 检查握手请求和响应, 对WebSocketHandler传递属性
//创建握手
public class WebSocketInterceptor implements HandshakeInterceptor {
    @Autowired
    private UserMapper userMapper;
    //在握手之前执行该方法, 继续握手返回true, 中断握手返回false.
     //       通过attributes参数设置WebSocketSession的属性
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        // 将ServerHttpRequest转换成request请求相关的类，用来获取request域中的用户信息
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
            HttpServletRequest httpRequest = servletRequest.getServletRequest();
            String userid = httpRequest.getHeader("userId");
          /* // HttpSession session = httpRequest.getSession();
            if (session != null) {
                User user = (User) session.getAttribute("userid");
                map.put(String.valueOf(user.getUserId()),user);
            }*/
            if (userid == null || userid.equals("") ){
                userid="10";
                // return false;
            }
          Integer count = userMapper.countUserById(userid);
            if (!(count > 0) ) {
                return false;
            }



            map.put("userId",userid); //为服务器创建WebSocketSession做准备
        }

        System.out.println("连接到我了");

        return true;

    }

   // 在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头.
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {


    }
}

package com.wechat.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。
/**
 类似Servlet的注解mapping。无需在web.xml中配置。
 * configurator = SpringConfigurator.class是为了使该类可以通过Spring注入。
 */
//@ServerEndpoint(value = "/websocket",configurator = SpringConfigurator.class)
@Configuration   //指明该类为Spring 配置类
@EnableWebSocket   //申明该类支持websocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(WebSocketPushHandler(), "/websocket").addInterceptors(new WebSocketInterceptor()).setAllowedOrigins("*");
        webSocketHandlerRegistry.addHandler(WebSocketPushHandler(), "/sockjs/webSocketServer.action")  //允许客户端使用SockJS
                .addInterceptors(new WebSocketInterceptor()).withSockJS();

    }
    @Bean
    public WebSocketHandler WebSocketPushHandler() {
        return new WebSocketPushHandler();
    }



}

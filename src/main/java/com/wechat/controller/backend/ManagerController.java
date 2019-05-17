package com.wechat.controller.backend;

import com.wechat.common.Const;
import com.wechat.common.ServerResponse;
import com.wechat.pojo.User;
import com.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
@RequestMapping("/backend/manager")
public class ManagerController {

    @Autowired
    private UserService userService;
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = userService.managerLogin(username,password);
        if (response.isSuccess()) {
            User user = response.getDate();
            if (user.getType() == Const.Role.ROLE_ADMIN) {
                //说明登录的是管理员
                session.setAttribute(Const.CURRENT_USER,user);
                return response;
            } else {
                return ServerResponse.createByErrorMessage("不是管理员,无法登录");
            }
        }
        return response;
    }
}

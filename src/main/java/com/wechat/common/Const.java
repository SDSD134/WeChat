package com.wechat.common;

public class Const {
    public static final String CURRENT_USER = "currentUser";


    public interface Role{
        String ROLE_ADMIN = "0";    //管理员
        String ROLE_CUSTOMER = "1";  //普通用户
        String ROLE_DOCTOR = "2";   //医生
    }
}

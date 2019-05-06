package com.wechat.pojo.vo;

public class UserVO {
   // private String  userId;
    private String avatarUrl;
    private String nickName;
    private String superReplyUserName;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSuperReplyUserName() {
        return superReplyUserName;
    }

    public void setSuperReplyUserName(String superReplyUserName) {
        this.superReplyUserName = superReplyUserName;
    }
}

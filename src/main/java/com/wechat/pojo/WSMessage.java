package com.wechat.pojo;

public class WSMessage {
    private String fromUser;
    private String toUser;
    private String message;
    private String messageType;
    private String fromUserName;
    private String toUsername;
    private String fromUserImg;
    private String toUserImg;

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getFromUserImg() {
        return fromUserImg;
    }

    public void setFromUserImg(String fromUserImg) {
        this.fromUserImg = fromUserImg;
    }

    public String getToUserImg() {
        return toUserImg;
    }

    public void setToUserImg(String toUserImg) {
        this.toUserImg = toUserImg;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "{" +
                "\"fromUser\":\"" + fromUser + "\"" +
                ",\"toUser\":\"" + toUser + "\""  +
                ",\"message\":\"" + message + "\"" +
                ",\"messageType\":\"" + messageType + "\""  +
                ", \"fromUserName\":\"" + fromUserName +"\""  +
                ", \"toUsername\":\"" + toUsername + "\""  +
                ", \"fromUserImg\":\"" + fromUserImg +"\"" +
                ",\"toUserImg\":\"" + toUserImg + "\""  +
                '}';
    }
}

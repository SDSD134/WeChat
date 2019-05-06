package com.wechat.pojo;

import java.util.Date;

public class GoodPost {
    private String postId;
    private String userId;
    private Date createTime;
    private Date updateTime;
    private Integer goodPostId;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getGoodPostId() {
        return goodPostId;
    }

    public void setGoodPostId(Integer goodPostId) {
        this.goodPostId = goodPostId;
    }
}

package com.wechat.pojo;

import java.util.Date;

public class Post {
    private Integer postId;

    private String postTopic;

    private String postRead;

    private String postGoodcount;

    private String userId;

    private String postReward;

    private String postType;

    private Date createTime;

    private Date updateTime;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostTopic() {
        return postTopic;
    }

    public void setPostTopic(String postTopic) {
        this.postTopic = postTopic;
    }

    public String getPostRead() {
        return postRead;
    }

    public void setPostRead(String postRead) {
        this.postRead = postRead;
    }

    public String getPostGoodcount() {
        return postGoodcount;
    }

    public void setPostGoodcount(String postGoodcount) {
        this.postGoodcount = postGoodcount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostReward() {
        return postReward;
    }

    public void setPostReward(String postReward) {
        this.postReward = postReward;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
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
}
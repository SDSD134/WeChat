package com.wechat.pojo;

import java.util.Date;

public class Post {
    private Integer postId;

    private String postTopic;

    private Integer postRead;

    private Integer postGoodcount;

    private String userId;

    private Integer postReward;

    private Integer postType;

    private Date createtime;

    private Date updatetime;

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
        this.postTopic = postTopic == null ? null : postTopic.trim();
    }

    public Integer getPostRead() {
        return postRead;
    }

    public void setPostRead(Integer postRead) {
        this.postRead = postRead;
    }

    public Integer getPostGoodcount() {
        return postGoodcount;
    }

    public void setPostGoodcount(Integer postGoodcount) {
        this.postGoodcount = postGoodcount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getPostReward() {
        return postReward;
    }

    public void setPostReward(Integer postReward) {
        this.postReward = postReward;
    }

    public Integer getPostType() {
        return postType;
    }

    public void setPostType(Integer postType) {
        this.postType = postType;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
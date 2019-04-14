package com.wechat.pojo;

public class Post {
    private Integer postId;
    private String postTopic;
    private String postContent;
    private String postRead;
    private Integer postGoodCount;
    private String postImage;
    private String userId;
    private Integer postReward;
    private Integer postType;
    private String createTime;
    private String updateTime;

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

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostRead() {
        return postRead;
    }

    public void setPostRead(String postRead) {
        this.postRead = postRead;
    }

    public Integer getPostGoodCount() {
        return postGoodCount;
    }

    public void setPostGoodCount(Integer postGoodCount) {
        this.postGoodCount = postGoodCount;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}

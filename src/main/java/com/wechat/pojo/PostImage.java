package com.wechat.pojo;

import java.util.Date;

public class PostImage {
    private Integer postImageId;

    private String postImageUrl;

    private Integer postId;

    private Date creatime;

    private Date updatetime;

    public Integer getPostImageId() {
        return postImageId;
    }

    public void setPostImageId(Integer postImageId) {
        this.postImageId = postImageId;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl == null ? null : postImageUrl.trim();
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Date getCreatime() {
        return creatime;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
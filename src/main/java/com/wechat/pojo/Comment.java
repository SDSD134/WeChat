package com.wechat.pojo;

import java.util.Date;

public class Comment {
    private Integer commentId;

    private String userId;

    private Integer postId;

    private Integer goodcount;

    private Date creatime;

    private Date updatetime;

    private String commentContext;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getGoodcount() {
        return goodcount;
    }

    public void setGoodcount(Integer goodcount) {
        this.goodcount = goodcount;
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

    public String getCommentContext() {
        return commentContext;
    }

    public void setCommentContext(String commentContext) {
        this.commentContext = commentContext == null ? null : commentContext.trim();
    }
}
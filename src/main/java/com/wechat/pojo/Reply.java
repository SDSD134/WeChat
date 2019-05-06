package com.wechat.pojo;

import com.wechat.pojo.vo.UserVO;

import java.util.Date;

public class Reply {
    private Integer replyId;

    private Integer commentId;

    private String userId;

    private Integer superReplyId;

    private String superReplyUserId;

    private UserVO user;

    private Date createtime;

    private Date updatetime;

    private String replyContext;

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }


    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

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

    public Integer getSuperReplyId() {
        return superReplyId;
    }

    public void setSuperReplyId(Integer superReplyId) {
        this.superReplyId = superReplyId;
    }

    public String getSuperReplyUserId() {
        return superReplyUserId;
    }

    public void setSuperReplyUserId(String superReplyUserId) {
        this.superReplyUserId = superReplyUserId == null ? null : superReplyUserId.trim();
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

    public String getReplyContext() {
        return replyContext;
    }

    public void setReplyContext(String replyContext) {
        this.replyContext = replyContext == null ? null : replyContext.trim();
    }
}
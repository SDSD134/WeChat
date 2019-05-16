package com.wechat.pojo;

import java.util.Date;

public class Doctor {
    private Integer doctorId;

    private String userId;

    private String doctorDesc;

    private Date creatime;

    private Date updatetime;

    private String doctorWorkImage;

    private String status;

    private User user;
    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getDoctorDesc() {
        return doctorDesc;
    }

    public void setDoctorDesc(String doctorDesc) {
        this.doctorDesc = doctorDesc == null ? null : doctorDesc.trim();
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

    public String getDoctorWorkImage() {
        return doctorWorkImage;
    }

    public void setDoctorWorkImage(String doctorWorkImage) {
        this.doctorWorkImage = doctorWorkImage == null ? null : doctorWorkImage.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
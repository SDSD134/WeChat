package com.wechat.pojo;

import java.util.Date;

public class DoctorImage {
    private Integer doctorImageId;

    private Integer doctorId;

    private String doctorImageUrl;

    private Date creatime;

    private Date updatetime;

    public Integer getDoctorImageId() {
        return doctorImageId;
    }

    public void setDoctorImageId(Integer doctorImageId) {
        this.doctorImageId = doctorImageId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorImageUrl() {
        return doctorImageUrl;
    }

    public void setDoctorImageUrl(String doctorImageUrl) {
        this.doctorImageUrl = doctorImageUrl == null ? null : doctorImageUrl.trim();
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
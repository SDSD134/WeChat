package com.wechat.pojo;

import java.util.Date;

public class Pet {
    private Integer petId;

    private Integer petType;

    private String petPrice;

    private String userId;

    private String petTitle;

    private Date creatime;

    private Date updatetime;

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Integer getPetType() {
        return petType;
    }

    public void setPetType(Integer petType) {
        this.petType = petType;
    }

    public String getPetPrice() {
        return petPrice;
    }

    public void setPetPrice(String petPrice) {
        this.petPrice = petPrice == null ? null : petPrice.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPetTitle() {
        return petTitle;
    }

    public void setPetTitle(String petTitle) {
        this.petTitle = petTitle == null ? null : petTitle.trim();
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
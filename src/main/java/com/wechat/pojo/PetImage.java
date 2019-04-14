package com.wechat.pojo;

import java.util.Date;

public class PetImage {
    private Integer petImageId;

    private String petImageType;

    private String petImageUrl;

    private Integer petId;

    private Date creatime;

    private Date updatetime;

    public Integer getPetImageId() {
        return petImageId;
    }

    public void setPetImageId(Integer petImageId) {
        this.petImageId = petImageId;
    }

    public String getPetImageType() {
        return petImageType;
    }

    public void setPetImageType(String petImageType) {
        this.petImageType = petImageType == null ? null : petImageType.trim();
    }

    public String getPetImageUrl() {
        return petImageUrl;
    }

    public void setPetImageUrl(String petImageUrl) {
        this.petImageUrl = petImageUrl == null ? null : petImageUrl.trim();
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
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
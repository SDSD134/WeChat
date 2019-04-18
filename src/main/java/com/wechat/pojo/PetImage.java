package com.wechat.pojo;

import java.util.Date;

public class PetImage {
    private Integer petImageId;
    private String petImageType;
    private String petImageUrl;
    private Integer petId;
    private Date createtime;
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
        this.petImageType = petImageType;
    }

    public String getPetImageUrl() {
        return petImageUrl;
    }

    public void setPetImageUrl(String petImageUrl) {
        this.petImageUrl = petImageUrl;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
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

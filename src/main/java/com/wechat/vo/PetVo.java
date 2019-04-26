package com.wechat.vo;

import com.wechat.pojo.Pet;

import java.util.Date;

public class PetVo{
    private Integer petId;
    private String petType;
    private String petPrice;
    private String userId;
    private String petTitle;
    private String petCity;
    private String petName;
    private String petAge;
    private String petGender;
    private String petBreed;
    private String petVaccine;
    private String mainImage;


    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetVaccine() {
        return petVaccine;
    }

    public void setPetVaccine(String petVaccine) {
        this.petVaccine = petVaccine;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetPrice() {
        return petPrice;
    }

    public void setPetPrice(String petPrice) {
        this.petPrice = petPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPetTitle() {
        return petTitle;
    }

    public void setPetTitle(String petTitle) {
        this.petTitle = petTitle;
    }

    public String getPetCity() {
        return petCity;
    }

    public void setPetCity(String petCity) {
        this.petCity = petCity;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }
}

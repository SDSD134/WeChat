package com.wechat.vo;

import com.wechat.pojo.Pet;
import com.wechat.pojo.PetImage;

import java.util.List;

public class PetDetailVo extends Pet{

    private List<PetImage> petImages;

    public List<PetImage> getPetImages() {
        return petImages;
    }

    public void setPetImages(List<PetImage> petImages) {
        this.petImages = petImages;
    }
}

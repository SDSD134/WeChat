package com.wechat.pojo.vo;

import com.wechat.pojo.Pet;
import com.wechat.pojo.PetImage;

import java.util.List;

public class PetDetailVO extends Pet{

    private List<PetImage> petImages;

    public List<PetImage> getPetImages() {
        return petImages;
    }

    public void setPetImages(List<PetImage> petImages) {
        this.petImages = petImages;
    }
}

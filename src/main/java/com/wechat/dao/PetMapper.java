package com.wechat.dao;

import com.wechat.pojo.Pet;
import com.wechat.pojo.PetImage;
import com.wechat.vo.PetDetailVo;
import com.wechat.vo.PetVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PetMapper {
    List<PetVo> selectAllPet(Pet pet) throws Exception;

    PetDetailVo findPetDetail(@Param("petId") Integer petId) throws Exception;

    int publish(Pet pet) throws Exception;

    int uploadImage(PetImage petImage) throws Exception;

    int deletePet(Integer petId) throws Exception;

    int deletePetImage(Integer petId) throws Exception;
}

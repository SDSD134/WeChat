package com.wechat.dao;

import com.wechat.pojo.Pet;
import com.wechat.pojo.PetImage;
import com.wechat.pojo.vo.PetDetailVO;
import com.wechat.pojo.vo.PetVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PetMapper {
    List<PetVO> listAllPet(@Param("pet")Pet pet) throws Exception;

    PetDetailVO getPetDetail(@Param("petId") Integer petId) throws Exception;

    int savePet(Pet pet) throws Exception;

    int uploadImage(PetImage petImage) throws Exception;

    int deletePet(Integer petId) throws Exception;

    int deletePetImage(Integer petId) throws Exception;

    int updatePet(Pet pet) throws Exception;
}

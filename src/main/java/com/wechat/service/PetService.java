package com.wechat.service;

import com.wechat.common.ServerResponse;
import com.wechat.pojo.Pet;
import com.wechat.vo.PetDetailVo;
import org.springframework.web.multipart.MultipartFile;

public interface PetService {
    ServerResponse selectAllPet(Pet pet, int pageNum, int pageSize) throws Exception;

    ServerResponse<PetDetailVo> findPetDetail(Integer petId) throws Exception;

    ServerResponse publish(Pet pet, String[] petImage) throws Exception;

    ServerResponse deletePet(Integer petId) throws Exception;

    ServerResponse publishOrUpdate(Pet pet,MultipartFile[] petImages)throws Exception;
}

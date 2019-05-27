package com.wechat.service;

import com.wechat.common.ServerResponse;
import com.wechat.pojo.Pet;
import com.wechat.pojo.vo.PetDetailVO;
import org.springframework.web.multipart.MultipartFile;

public interface PetService {
    ServerResponse listAllPet(Pet pet, int pageNum, int pageSize) throws Exception;

    ServerResponse<PetDetailVO> getPetDetail(Integer petId) throws Exception;

    ServerResponse deletePet(Integer petId) throws Exception;

    ServerResponse saveOrUpdate(Pet pet,MultipartFile image)throws Exception;
}

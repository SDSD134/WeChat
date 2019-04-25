package com.wechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wechat.common.ServerResponse;
import com.wechat.dao.PetMapper;
import com.wechat.pojo.Pet;
import com.wechat.pojo.PetImage;
import com.wechat.service.PetService;
import com.wechat.util.aliyunoss.OSSClientUtil;
import com.wechat.vo.PetDetailVo;
import com.wechat.vo.PetVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service("petService")
public class PetServiceImpl implements PetService {
    @Autowired
    private PetMapper petMapper;
    @Override
    public ServerResponse selectAllPet(Pet pet, int pageNum, int pageSize) throws Exception {
        if(!StringUtils.isNotBlank(pet.getPetType())){
            return ServerResponse.createByErrorMessage("没有传入交易类型");
        }
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper--收尾
        PageHelper.startPage(pageNum,pageSize);
        List<PetVo> petVoList = petMapper.selectAllPet(pet);
        if(petVoList == null || petVoList.isEmpty()){
            return ServerResponse.createByErrorMessage("没有查询结果");
        }
        PageInfo pageResult = new PageInfo(petVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<PetDetailVo> findPetDetail(Integer petId) throws Exception {
        if (petId == null){
            return ServerResponse.createByErrorMessage("没有传入交易id");
        }
        PetDetailVo petDetail = petMapper.findPetDetail(petId);
        if(petDetail == null){
            return ServerResponse.createByErrorMessage("此交易已下架");
        }
        return ServerResponse.createBySuccess(petDetail);
    }

    @Override
    public ServerResponse deletePet(Integer petId) throws Exception {
        int resultCount1 = petMapper.deletePet(petId);
        int resultCount2 = petMapper.deletePetImage(petId);
        if (resultCount1 > 0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    public ServerResponse publish(Pet pet, String[] petImages) throws Exception {
        if(!StringUtils.isNotBlank(pet.getWx())||!StringUtils.isNotBlank(pet.getPhone())){
            return ServerResponse.createByErrorMessage("微信和手机号必须留一个");
        }
        if(!StringUtils.isNotBlank(pet.getPetTitle())){
            return ServerResponse.createByErrorMessage("必须添加标题");
        }
        int resultCount1 = petMapper.publish(pet);
        for(int i = 0; i<petImages.length;i++){
            PetImage petImage = new PetImage();
            if (i==0){
                petImage.setPetImageType("1");
            }
            petImage.setPetImageUrl(petImages[i]);
            petImage.setPetId(pet.getPetId());
            int resultCount = petMapper.uploadImage(petImage);
        }

        if(resultCount1 > 0){
            return ServerResponse.createByErrorMessage("发布成功");
        }
        return ServerResponse.createByErrorMessage("发布失败");
    }
    @Override
    public ServerResponse publishOrUpdate(Pet pet,MultipartFile[] petImages) throws Exception {
        if(!StringUtils.isNotBlank(pet.getWx())||!StringUtils.isNotBlank(pet.getPhone()))
            return ServerResponse.createByErrorMessage("微信和手机号必须留一个");
        if(!StringUtils.isNotBlank(pet.getPetTitle()))
            return ServerResponse.createByErrorMessage("必须添加标题");
        if(!StringUtils.isNotBlank(pet.getPetAge()))
            return ServerResponse.createByErrorMessage("添加宠物年龄");
        if(!StringUtils.isNotBlank(pet.getPetBreed()))
            return ServerResponse.createByErrorMessage("添加宠物品种");
        if(!StringUtils.isNotBlank(pet.getPetGender()))
            return ServerResponse.createByErrorMessage("添加宠物性别");
        if(!StringUtils.isNotBlank(pet.getPetName()))
            return ServerResponse.createByErrorMessage("添加宠物昵称");
        if(pet.getPetId()==null){
            //发布
            int resultCount = petMapper.publish(pet);
            if(petImages != null) {
                OSSClientUtil ossClientUtil = new OSSClientUtil();
                for (int i = 0; i < petImages.length; i++) {
                    String imageUrl = ossClientUtil.uploadImg2Oss(petImages[i]);
                    PetImage petImage = new PetImage();
                    petImage.setPetId(pet.getPetId());
                    petImage.setPetImageUrl(imageUrl);
                    if (i == 0)
                        petImage.setPetImageType("1");
                    petMapper.uploadImage(petImage);
                }
            }
            if (resultCount > 0)
                return ServerResponse.createBySuccessMessage("添加成功");
        }else{
            petMapper.updatePet(pet);
        }
        return ServerResponse.createByError();
    }
}
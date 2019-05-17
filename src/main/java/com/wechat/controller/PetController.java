package com.wechat.controller;

import com.wechat.common.ServerResponse;
import com.wechat.pojo.Pet;
import com.wechat.service.PetService;
import com.wechat.pojo.vo.PetDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;
    @RequestMapping(value = "/allPet",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse allPet(Pet pet, @RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "5") int pageSize, String token) throws Exception {
        return petService.listAllPet(pet,pageNum,pageSize);
    }
    @RequestMapping(value = "/petDetail",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PetDetailVO> petDetail(Integer petId, String token) throws Exception {
        return petService.getPetDetail(petId);
    }
    @RequestMapping(value = "/publishOrUpdate",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse publishOrUpdate(Pet pet,HttpServletRequest request,@RequestParam("petImages")MultipartFile[] petImages, @RequestHeader("userId")String userId) throws Exception {
        pet.setUserId(userId);
        return petService.saveOrUpdate(pet,petImages);
    }
    @RequestMapping(value = "/deletePet",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse deletePet(Integer petId) throws Exception {
        return petService.deletePet(petId);
    }
}

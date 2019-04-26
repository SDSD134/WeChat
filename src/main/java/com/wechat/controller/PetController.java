package com.wechat.controller;

import com.wechat.common.ServerResponse;
import com.wechat.pojo.Pet;
import com.wechat.pojo.PetImage;
import com.wechat.service.PetService;
import com.wechat.vo.PetDetailVo;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;
    @RequestMapping(value = "/selectAllPet",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse selectPet(Pet pet, @RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "5") int pageSize, String token) throws Exception {
        return petService.selectAllPet(pet,pageNum,pageSize);

    }
    @RequestMapping(value = "/petDetail",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PetDetailVo> petDetail(Integer petId, String token) throws Exception {
        return petService.findPetDetail(petId);
    }
    @RequestMapping(value = "/publishOrUpdate",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse publishOrUpdate(Pet pet,HttpServletRequest request,@RequestParam("petImages")MultipartFile[] petImages) throws Exception{

        return petService.publishOrUpdate(pet,petImages);
        //return petService.publish(pet,petImages);
    }
    @RequestMapping(value = "/deletePet",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse deletePet(Integer petId) throws Exception{
        return petService.deletePet(petId);
    }
}

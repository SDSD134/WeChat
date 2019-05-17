package com.wechat.controller.backend;
import com.wechat.common.ServerResponse;
import com.wechat.pojo.Pet;
import com.wechat.service.PetService;
import com.wechat.pojo.vo.PetDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/petManage")
public class PetManageController {
    @Autowired
    private PetService petService;
    @RequestMapping(value = "/deletePet",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse deletePet(Integer petId) throws Exception {
        return petService.deletePet(petId);
    }
    @RequestMapping(value = "/listAllPet",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listAllPet(Pet pet, @RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "5") int pageSize, String token) throws Exception {
        return petService.listAllPet(pet,pageNum,pageSize);

    }
    @RequestMapping(value = "/petDetail",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PetDetailVO> petDetail(Integer petId, String token) throws Exception {
        return petService.getPetDetail(petId);
    }
}

package com.wechat.controller;

import com.wechat.common.ServerResponse;
import com.wechat.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @RequestMapping(value = "/listDoctor",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listDoctor(@RequestHeader("userId") String userId) {
        return doctorService.listDoctor(userId);
    }


}

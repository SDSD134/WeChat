package com.wechat.controller;

import com.wechat.common.ServerResponse;
import com.wechat.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @RequestMapping("/listDoctor")
    @ResponseBody
    public ServerResponse listDoctor() {
        return doctorService.listDoctor();
    }
    
}

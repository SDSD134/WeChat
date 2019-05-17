package com.wechat.controller.backend;

import com.wechat.common.ServerResponse;
import com.wechat.pojo.Doctor;
import com.wechat.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/backend/doctorManage")
public class DoctorManageController {
    @Autowired
    private DoctorService doctorService;
    @RequestMapping("/validateDoctor")
    @ResponseBody
    public ServerResponse validateDoctor() {
        return doctorService.listDoctorApplication();
    }
    @RequestMapping("/addDoctor")
    @ResponseBody
    public ServerResponse addDoctor(Doctor doctor) {
        return doctorService.updateDoctor(doctor);
    }
    @RequestMapping("/deleteDoctor")
    @ResponseBody
    public ServerResponse deleteDoctor(Integer doctorId) {
        return doctorService.deleteDoctor(doctorId);
    }
}

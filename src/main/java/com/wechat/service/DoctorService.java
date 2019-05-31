package com.wechat.service;

import com.wechat.common.ServerResponse;
import com.wechat.pojo.Doctor;

public interface DoctorService {
    ServerResponse listDoctorApplication();

    ServerResponse updateDoctor(Doctor doctor);


    ServerResponse deleteDoctor(Integer doctorId);

    ServerResponse listDoctor(String userId);

    ServerResponse saveConversation(Integer doctorTd,String userId);
}

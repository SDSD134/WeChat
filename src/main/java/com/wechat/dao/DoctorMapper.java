package com.wechat.dao;

import com.wechat.pojo.Doctor;

import java.util.List;

public interface DoctorMapper {
    List<Doctor> listDoctorApplication();

    int updateDoctor(Doctor doctor);

    int deleteDoctor(Integer doctorId);

    List<Doctor> listDoctor();
}

package com.wechat.dao;

import com.wechat.pojo.Doctor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface DoctorMapper {
    List<Doctor> listDoctorApplication();

    int updateDoctor(Doctor doctor);

    int deleteDoctor(Integer doctorId);

    List<Doctor> listDoctor();

    Integer checkDoctor(@Param("userId") String userId);

}

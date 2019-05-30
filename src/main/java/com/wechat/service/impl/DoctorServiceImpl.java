package com.wechat.service.impl;

import com.wechat.common.ServerResponse;
import com.wechat.dao.ConversationMapper;
import com.wechat.dao.DoctorMapper;
import com.wechat.dao.UserMapper;
import com.wechat.pojo.Conversation;
import com.wechat.pojo.Doctor;
import com.wechat.pojo.User;
import com.wechat.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private ConversationMapper conversationMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public ServerResponse listDoctorApplication() {
        List<Doctor> doctors = doctorMapper.listDoctorApplication();
        if (doctors == null || doctors.isEmpty()) {
            return ServerResponse.createByErrorMessage("没有医生");
        }
        return ServerResponse.createBySuccess(doctors);
    }

    @Override
    public ServerResponse updateDoctor(Doctor doctor) {
        if (doctor != null) {
            int resultCount = doctorMapper.updateDoctor(doctor);
            if( resultCount > 0) {
                return ServerResponse.createBySuccessMessage("更新成功");
            } else {
                return ServerResponse.createByErrorMessage("更新失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新医生参数不正确");
    }

    @Override
    public ServerResponse deleteDoctor(Integer doctorId) {
        int resultCount = doctorMapper.deleteDoctor(doctorId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse listDoctor(String userId) {
        Integer doctotId = doctorMapper.checkDoctor(userId);
        if(doctotId != null) {//用户为医生身份
            List<Conversation> conversationList = conversationMapper.listConversation(doctotId);
            if(conversationList.isEmpty() || conversationList == null) {
                return ServerResponse.createBySuccessMessage("还没有人和您询问");
            }
            for(Conversation conversation : conversationList) {
                User user = userMapper.findUserById(conversation.getUserId());
                conversation.getUser().setNickName(user.getNickName());
                conversation.getUser().setAvatarUrl(user.getAvatarUrl());
            }
            return ServerResponse.createBySuccess(conversationList);
        }
        List<Doctor> doctors = doctorMapper.listDoctor();
        if (doctors == null || doctors.isEmpty()) {
            return ServerResponse.createByErrorMessage("没有医生");
        }
        return ServerResponse.createBySuccess(doctors);
    }
}

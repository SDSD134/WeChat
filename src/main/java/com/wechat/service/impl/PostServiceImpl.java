package com.wechat.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wechat.common.ServerResponse;
import com.wechat.dao.PostMapper;
import com.wechat.pojo.Post;
import com.wechat.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public ServerResponse<List<Post>>  getAllPostByUSer(String userid) {
        List<Post> postList = postMapper.getPostByUser(userid);
        Integer count = postMapper.countPostByUser(userid);
        if (!(count > 0) && postList.isEmpty()) {
            ServerResponse.createBySuccessMessage("此用户没有写过贴吧");
        }
        System.out.println(postList);
        return ServerResponse.createBySuccess("获取成功",postList);
    }

    @Override
    public ServerResponse<List<Post>> getAllPost(int pageNum,int pageSize) {
        //PageHelper只对紧跟着的第一个SQL语句起作用
        PageHelper.startPage(pageNum,pageSize);
        List<Post> mapperList = postMapper.getAllPost();
        if (mapperList.isEmpty()) {
            return ServerResponse.createByErrorMessage("获取失败,联系开发人员");
        }
        return ServerResponse.createBySuccess("获取成功",mapperList);
    }


}

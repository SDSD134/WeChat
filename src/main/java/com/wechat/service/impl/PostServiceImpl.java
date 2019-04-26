package com.wechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
            return ServerResponse.createBySuccessMessage("此用户没有写过贴吧");
        }
        System.out.println(postList);
        return ServerResponse.createBySuccess("获取成功",postList);
    }

    @Override
    public ServerResponse getAllPost(int pageNum,int pageSize) {
        //PageHelper只对紧跟着的第一个SQL语句起作用
        PageHelper.startPage(pageNum,pageSize);
        List<Post> postList = postMapper.getAllPost();
        if (postList.isEmpty()) {
            return ServerResponse.createByErrorMessage("获取失败,联系开发人员");
        }
        PageInfo<List<Post>> pageResult = new PageInfo(postList);


        return ServerResponse.createBySuccess("获取成功",pageResult);
    }

    @Override
    public ServerResponse<String> deletePostById(int id) {
        Integer deletCount = postMapper.deletPostById(id);
        if (deletCount > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createBySuccessMessage("删除失败，参数错误");

    }

    @Override
    public ServerResponse<String> addPostReadOrPraise(int id,String type) {
        Integer isExist = postMapper.isExist(id);
        if (!(isExist > 0))
            return ServerResponse.createByErrorMessage("帖子不存在");
        if (type.equals("readCount")) {
            Integer addCount = postMapper.addPostRead(id);
            if (addCount > 0)
            return ServerResponse.createBySuccessMessage("成功");
        }
        if (type.equals("praisePost")) {
            Integer addCount = postMapper.addPraiseById(id);
            if (addCount > 0)
                return ServerResponse.createBySuccessMessage("成功");
        }
        return ServerResponse.createByErrorMessage("种类参数错误");
    }





}

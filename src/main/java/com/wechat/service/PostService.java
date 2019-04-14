package com.wechat.service;

import com.wechat.common.ServerResponse;
import com.wechat.pojo.Post;
import org.json.simple.JSONObject;

import java.util.List;

public interface PostService {
    ServerResponse<List<Post>> getAllPostByUSer(String userid);

    ServerResponse<List<Post>> getAllPost(int pageNum,int pageSize);
}

package com.wechat.service;

import com.wechat.common.ServerResponse;
import com.wechat.pojo.Post;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    ServerResponse<List<Post>> getAllPostByUSer(String userid,int pageNum, int pageSize);

    ServerResponse<List<Post>> getAllPost(int pageNum,int pageSize);

    ServerResponse<String> deletePostById(int id);

    ServerResponse<String> addPostReadOrPraise(Integer  id,String userId,String type);

    ServerResponse<String> addPostById(Post post, MultipartFile[] postImages);
}

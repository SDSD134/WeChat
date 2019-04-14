package com.wechat.dao;

import com.wechat.pojo.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostMapper {
    List<Post> getPostByUser(String userid);

    Integer  countPostByUser (String userid);

    List<Post>  getAllPost();

    void deletPostById(int id);
}

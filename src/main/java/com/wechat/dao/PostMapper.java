package com.wechat.dao;

import com.wechat.pojo.GoodPost;
import com.wechat.pojo.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostMapper {
    List<Post> getPostByUser(String userId);

    Integer  countPostByUser (String userId);

    List<Post>  getAllPost();

    Integer deletPostById(int id);

    Integer updatePostRead(int id);

    Integer addPraiseById(int id);

    Integer isExist(int id);

    Integer addGoodPost(@Param("userId") String userId, @Param("postId")Integer postId);

    Integer countGoodPost(@Param("userId") String userId, @Param("postId")Integer postId);

    Integer deleteGoodPost(@Param("userId")String userId, @Param("postId")Integer postId);

    Integer reducePraiseById(Integer id);

    Integer addPostById(Post post);

    Integer addImageByPost(@Param("postId") Integer postId,@Param("imageUrl")String imageUrl);

    List<String> getImageByPost(Integer postId);
}

package com.wechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wechat.common.ServerResponse;
import com.wechat.dao.PostMapper;
import com.wechat.pojo.Post;
import com.wechat.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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
    public ServerResponse getAllPost(int pageNum,int pageSize,String userId) {
        //PageHelper只对紧跟着的第一个SQL语句起作用
        PageHelper.startPage(pageNum,pageSize);
        List<Post> postList = postMapper.getAllPost(userId);
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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ServerResponse<String> addPostReadOrPraise(Integer id,String type,String userId) {
        Integer isExist = postMapper.isExist(id);
        if (!(isExist > 0))
            return ServerResponse.createByErrorMessage("帖子不存在");
        if (type.equals("readCount")) {
            Integer addCount = postMapper.addPostRead(id);
            if (addCount > 0)
            return ServerResponse.createBySuccessMessage("成功");
        }
        if (type.equals("praisePost")) {
            //是否点赞
            Integer isPraise = postMapper.countGoodPost(userId,id);
            //已经点赞取消
            if(isPraise > 0) {
                //添加事务处理，还未添加
               Integer count =  postMapper.deleteGoodPost(userId,id);
               Integer reduce = postMapper.reducePraiseById(id);
               if (count > 0 && reduce > 0) {
                   return ServerResponse.createBySuccessMessage("取消点赞成功");
               } else {
                   return ServerResponse.createBySuccessMessage("取消点赞失败");
               }
            }else{
                //添加事务处理
                //没有点赞，点赞数加一
                Integer addCount=null;
                Integer praise = null;
                int flag = 0;
                try {
                    addCount = postMapper.addPraiseById(id);
                    //添加点赞记录
                    praise= postMapper.addGoodPost(userId,id);
                }catch ( RuntimeException e) {
                    flag = 1;
                    throw e;
                }
                if (flag == 1) {
                    return ServerResponse.createByErrorMessage("未知错误");
                }

                if (addCount > 0 && praise >0)
                    return ServerResponse.createBySuccessMessage("点赞成功");
                Integer addGood = postMapper.addGoodPost(userId,id);

            }


        }
        return ServerResponse.createByErrorMessage("种类参数错误");
    }





}

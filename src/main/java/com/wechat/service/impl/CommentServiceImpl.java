package com.wechat.service.impl;

import com.wechat.common.ServerResponse;
import com.wechat.dao.CommentMapper;
import com.wechat.dao.ReplyMapper;
import com.wechat.pojo.Comment;
import com.wechat.pojo.Reply;
import com.wechat.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public ServerResponse<String> commentPost(Comment comment) {
        Integer insert = commentMapper.insertCommentPost(comment);
        if (insert > 0)
            return ServerResponse.createBySuccessMessage("评论成功");

        return ServerResponse.createByErrorMessage("参数错误");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ServerResponse addReply(Reply reply, String userId) {
        //需要添加事务
        Integer update = null;
        Integer insert = null;
        try {
             update = commentMapper.updatePlusComment(reply.getCommentId());
             insert = replyMapper.insertReply(reply);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }

        if (insert > 0 && update > 0 ) {
            ServerResponse.createBySuccessMessage("评论成功");
        }
        return ServerResponse.createBySuccessMessage("评论失败");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    //删除评论
    public ServerResponse deleteReplyById(String replyId,String commentId) {
        Integer id = Integer.parseInt(replyId);
        Integer delete = null;
        Integer update = null;
        //需要添加业务处理，还未添加
        try {
            delete = replyMapper.deleteReplyById(id);
            update = commentMapper.updateReduceComment(Integer.parseInt(commentId));
        } catch (RuntimeException e) {
            throw e;
        }

        if (delete > 0 && update >0 ) {
            return ServerResponse.createBySuccessMessage("删除错误");

        }
        return ServerResponse.createBySuccessMessage("删除错误");
    }


    @Override
    public ServerResponse listCommentByPost(Comment comment) {
        List<Comment> list = commentMapper.listCommentByPost(comment.getPostId());
        if (list == null) {
            return ServerResponse.createByErrorMessage("该帖子没有评论");
        }
        return ServerResponse.createBySuccess(list);
    }

    public ServerResponse getCommentReply(Comment comment) {
       comment = commentMapper.getComment(comment.getCommentId(),comment.getPostId());
       List<Reply> replies = replyMapper.listGetReply(comment.getCommentId());
       if (replies!= null) {
           if (replies.isEmpty()){
              return  ServerResponse.createBySuccessMessage("此评论没有回复");
           }

       }

       comment.setReply(replies);
        return ServerResponse.createBySuccess(comment);
    }
}

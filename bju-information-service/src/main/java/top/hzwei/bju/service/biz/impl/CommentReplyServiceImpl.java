package top.hzwei.bju.service.biz.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hzwei.bju.model.dto.CommentDTO;
import top.hzwei.bju.model.entry.Comment;
import top.hzwei.bju.model.mapper.CommentReplyMapper;
import top.hzwei.bju.model.vo.MovingDetailsVO;
import top.hzwei.bju.service.biz.CommentReplyService;

/**
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/6 15:29
 */
@Slf4j
@Service
public class CommentReplyServiceImpl implements CommentReplyService {

    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Override
    public boolean addMvoingComment( CommentDTO commentDTO) {
        log.info("#### 动态评论服务层，新增评论：commentDTO={}",commentDTO);
        Integer count = commentReplyMapper.insertComment(commentDTO);
        log.info("#### 动态评论服务层，新增评论结果：count={}",count);
        return count != 0;
    }

    @Override
    public boolean updateCommentLikeCountAndUser(Integer commentId, Integer likeUserId) {
        log.info("#### 评论回复更新指定评论的点赞量及点赞用户列表service层，入参：commentId={}，likeUserId={}",commentId,likeUserId);
        Integer count = commentReplyMapper.updateCommentLikeCountAndLikeUser(commentId,likeUserId);
        log.info("#### 评论回复更新指定评论的点赞量及点赞用户列表service层，结果：count={}",count);
        return count != 0;
    }


}

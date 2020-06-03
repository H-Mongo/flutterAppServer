package top.hzwei.bju.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.hzwei.bju.model.dto.CommentDTO;
import top.hzwei.bju.model.entry.Comment;
import top.hzwei.bju.model.vo.MovingDetailsVO;

/**
 * 评论回复Mapper
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/6 21:50
 */
@Mapper
public interface CommentReplyMapper {

    /***
     *  插入评论信息
     * @param commentDTO 评论内容DTO对象
     * @return 返回插入成功的记录行数
     */
    Integer insertComment(CommentDTO commentDTO);

    /**
     *  更新指定评论的点赞量及点赞用户列表
     * @param likeUserId 点赞用户ID
     * @param commentId 评论ID
     * @return 返回更新成功的记录行数
     */
    Integer updateCommentLikeCountAndLikeUser(@Param("commentId") Integer commentId, @Param("likeUserId") Integer likeUserId);
}

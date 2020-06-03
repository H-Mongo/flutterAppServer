package top.hzwei.bju.service.biz;

import top.hzwei.bju.model.dto.CommentDTO;
import top.hzwei.bju.model.entry.Comment;
import top.hzwei.bju.model.vo.MovingDetailsVO;

import java.util.List;

/**
 * 评论回复服务层
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/6 15:29
 */
public interface CommentReplyService {

   /**
    * 新增动态评论
    * @param commentDTO 评论内容DTO对象
    * @return 返回新增成功或者失败
    */
   boolean addMvoingComment(CommentDTO commentDTO);

    /**
     *  更新指定评论的点赞量及点赞用户列表
     * @param commentId 评论ID
     * @param likeUserId    点赞用户ID
     * @return  返回执行数据更新的结果（true/false）
     */
   boolean updateCommentLikeCountAndUser(Integer commentId, Integer likeUserId);
}
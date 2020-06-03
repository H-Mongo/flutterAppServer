package top.hzwei.bju.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/29 16:32
 */
@Data
public class CommentDTO implements Serializable {

    /**
     *  评论ID
     */
    Integer commentId;
    /**
     *  被评论动态ID
     */
    Integer movingId;
    /**
     *  评论内容
     */
    String commentContent;
    /**
     *  评论点赞量
     */
    Integer commentLike;
    /**
     * 评论点赞用户（多个用户之间使用“,”分割）
     */
    String commentLikeUser;
    /**
     *  评论状态（0：正常；1：违规；2：删除）
     */
    Integer commentStatus;
    /**
     *  写评论用户ID
     */
    Integer commentUid;
    /**
     * 写评论用户名
     */
    String commentAuthor;
    /**
     *  评论人头像
     */
    String commentAuthorAvatar;
    /**
     *  被评论动态用户ID
     */
    Integer replyMovingUid;
    /**
     *  被评论动态用户名
     */
    String replyMovingUname;
    /**
     *  父评论ID
     */
    Integer commentParent;
    /**
     * 艾特用户列表
     */
    List<String> atAliasList;
    /**
     *  评论通知用户号码
     */
    String commentAlias;
    /**
     *  创建时间
     */
    Date createTime;
    /**
     *  更新时间
     */
    Date updateTime;


}

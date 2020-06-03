package top.hzwei.bju.model.entry;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论回复实体类
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/22 22:48
 */
@Data
public class Reply  implements Serializable {
    /**
     * 回复ID
     */
    Integer replyId;
    /**
     * 回复类型（回复对象分类：1：comment；2：reply）
     */
    Integer replyType;
    /**
     * 回复内容
     */
    String replyContent;
    /**
     * 回复点赞量
     */
    Integer replyLike;
    /**
     * 回复点赞用户（多个用户之间用","分割）
     */
    String replyLikeUser;
    /**
     * 回复主题ID（与reply_type相关）
     */
    Integer replySubjectId;
    /**
     * 回复发起者ID
     */
    Integer replySponsor;
    /**
     * 回复接收者ID
     */
    Integer replyReceiver;
    /**
     * 回复内容状态（0：正常；1：违规；2：删除）
     */
    Integer replyStatus;
    Date createTime;
    Date updateTime;

}

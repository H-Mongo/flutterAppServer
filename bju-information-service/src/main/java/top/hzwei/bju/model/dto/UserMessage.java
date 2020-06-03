package top.hzwei.bju.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户自定义消息类
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/23 12:59
 */
@Data
@Builder
public class UserMessage  implements Serializable {
    /**
     * 消息头
     */
    String title;
    /**
     * 消息体
     */
    String content;
    /**
     * 消息类型
     * @see top.hzwei.bju.common.enums.MessageTypeEnum
     */
    Integer messageType;
    /**
     * 来源用户名
     */
    String fromUserName;
    /**
     * 来源用户Id
     */
    Integer fromUserId;
    /**
     * 来源用户头像URL
     */
    String fromUserAvatar;
    /**
     * 目标用户名
     */
    String toUserName;
    /**
     * 查询ID（MovingID）
     */
     Integer queryId;
    /**
     * 接受时间
     */
     String receivedTime;
    /**
     * 是否已读（默认未读）
     */
     Boolean read;

}

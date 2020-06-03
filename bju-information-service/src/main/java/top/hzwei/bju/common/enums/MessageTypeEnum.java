package top.hzwei.bju.common.enums;

import lombok.Data;

/**
 * 消息类型枚举
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/22 23:43
 */
public enum MessageTypeEnum {


//     ********** 消息类型描述 ******************
//       code   description   example
//        0         通知       应用通知
//        1          @        mongo@了你
//        2       评论动态     mongo评论了你的动态
//        3       回复评论     mongo回复了你的评论
//        4       赞动态       mongo赞了你的动态
//        5       赞评论       mongo赞了你的评论
//    *****************************************


    NOTICE(0,"通知"),
    AT(1, "@"),
    COMMENT_MOVING(2,"评论动态"),
    REPLY_COMMENT(3, "回复评论"),
    LIKE_MOVING(4, "赞动态"),
    LIKE_COMMENT(5, "赞评论");



    private int messageTypeCode;
    private String messageTypeName;


    MessageTypeEnum(int messageTypeCode, String messageTypeName){
        this.messageTypeCode = messageTypeCode;
        this.messageTypeName = messageTypeName;
    }

    public int getMessageTypeCode() {
        return messageTypeCode;
    }

    public String getMessageTypeName() {
        return messageTypeName;
    }
}

package top.hzwei.bju.utils;

import lombok.extern.slf4j.Slf4j;
import top.hzwei.bju.model.dto.UserMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息工具类
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/23 13:09
 */
@Slf4j
public class MessageUtil {

    /**
     * 日期格式
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 构造自定义消息
     * @param messageType 消息类型
     * @param content 消息体
     * @param fromUserName 来源用户名
     * @param fromUserId 来源用户ID
     * @param fromUserAvatar 来源用户头像
     * @param toUserName 目标用户名
     * @param queryId 动态ID
     * @return 返回构造的用户自定义消息
     */
    @Deprecated
    public static UserMessage buildUserMessage(Integer messageType,String content, String fromUserName, Integer fromUserId, String fromUserAvatar, String toUserName, Integer queryId){
       return UserMessage.builder()
                .title(createMessageTitle(messageType,fromUserName))
                .content(content)
                .messageType(messageType)
                .fromUserName(fromUserName)
                .fromUserId(fromUserId)
                .fromUserAvatar(fromUserAvatar)
                .toUserName(toUserName)
                .queryId(queryId)
                .receivedTime(LocalDateTime.now().format(DATE_TIME_FORMATTER))
                .build();
    }

    /**
     * 创建自定义消息头
     * @param messageType 消息类型
     * @param name 消息来源用户名
     * @return 返回消息头
     */
    private static String createMessageTitle(Integer messageType, String name) {
        switch (messageType){
            case 0 :
                return "系统通知";
            case 1 :
                return name + "@了你";
            case 2 :
                return name + "评论了你的动态";
            case 3 :
                return name + "回复了你的评论";
            case 4 :
                return name + "赞了你的动态";
            case 5 :
                return name + "赞了你的评论";
            default:
                log.info("#### 创建自定义消息头出错了!");
                return "错误消息";
        }

    }
    /**
     * 构造JPush移动端extras参数
     * @param messageType 消息类型
     * @param content 消息体
     * @param fromUserName 来源用户名
     * @param fromUserId 来源用户ID
     * @param fromUserAvatar 来源用户头像
     * @param toUserName 目标用户名
     * @param queryId 动态ID
     * @return 返回JPush移动端extrasMap
     */
    public static Map<String,String> createMessage2ExtrasMap(Integer messageType,String content, String fromUserName, Integer fromUserId, String fromUserAvatar, String toUserName, Integer queryId){
        Map<String,String> extrasMap = new HashMap();
        extrasMap.put("title",createMessageTitle(messageType,fromUserName));
        extrasMap.put("content",content);
        extrasMap.put("messageType",messageType.toString());
        extrasMap.put("fromUserName",fromUserName);
        extrasMap.put("fromUserId",fromUserId.toString());
        extrasMap.put("fromUserAvatar",fromUserAvatar);
        extrasMap.put("toUserName",toUserName);
        extrasMap.put("queryId",queryId.toString());
        extrasMap.put("receivedTime",LocalDateTime.now().format(DATE_TIME_FORMATTER));
        extrasMap.put("read",Boolean.FALSE.toString());
        return extrasMap;
    }



}

package top.hzwei.bju.controller.notification;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.hzwei.bju.common.annotation.JWTIgnore;
import top.hzwei.bju.common.model.Response;
import top.hzwei.bju.service.notification.JPushService;
import top.hzwei.bju.utils.MessageUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * JPush API接口
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/20 23:51
 */
@RestController
@RequestMapping("notification")
public class JPushAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JPushAPIController.class);

    @Autowired
    private JPushService jPushService;

    @JWTIgnore
    @GetMapping("/testJPush")
    public Response sendNotification2All(@RequestParam("title") String title,@RequestParam("content") String content){
        LOGGER.info("#### [TEST] APP推送系统信息，参数：title={}，content={}",title,content);
        try {

            PushResult pushResult = jPushService.sendPushAllPlatformWithConfigAlias(title, content, null, null);

            return Response.responseSuccess("系统通知推送成功",pushResult);
        } catch (APIConnectionException e) {
            LOGGER.error("#### 推送所有用户失败： message={}","JPush连接异常");
            return Response.responseFail("JPush连接异常",null);
        } catch (APIRequestException e) {
            LOGGER.error("#### 推送所有用户失败： message={}","JPush请求异常");
            return Response.responseFail("JPush请求异常",null);
        }
    }

    @JWTIgnore
    @PostMapping("/alias")
    public Response pushWithAlias(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("alias") String alias){
        LOGGER.info("#### [TEST] APP推送系统信息，参数：title={}，content={}",title,content);
        try {
            if(Objects.isNull(alias)){
                return Response.responseFail("别名为空!",null);
            }
            PushResult pushResult = jPushService.sendPushAllPlatformWithConfigAlias(title, content, null, Arrays.asList(alias));

            return Response.responseSuccess("系统通知推送成功",pushResult);
        } catch (APIConnectionException e) {
            LOGGER.error("#### 推送所有用户失败： message={}","JPush连接异常");
            return Response.responseFail("JPush连接异常",null);
        } catch (APIRequestException e) {
            LOGGER.error("#### 推送所有用户失败： message={}","JPush请求异常");
            return Response.responseFail("JPush请求异常",null);
        }
    }

    /**
     *  全员推送信息
     * @param messageType   消息类别
     * @param content   推送内容
     * @return  返回业务响应对象
     */
    @PostMapping("/all")
    public Response sendAppNotice2All(@RequestParam("messageType") Integer messageType,@RequestParam("content") String content){
        LOGGER.info("#### APP推送系统信息，参数：messageType={}，content={}",messageType,content);
        PushResult pushResult = null;
        try {
            //UserMessage userMessage = MessageUtil.buildUserMessage(messageType, content, null, null, null, null, null);
            Map<String, String> extrasMap = MessageUtil.createMessage2ExtrasMap(messageType, content, null, 0, null, null, 0);
            LOGGER.info("#### APP推送系统信息，JPush extrasMap={}",extrasMap);
            // 调用推送服务
            pushResult = jPushService.alertMessage2AndroidWithAliasAndExtras(extrasMap.get("title"), content, extrasMap, null);
        } catch (Exception e) {
            LOGGER.error("#### 推送所有用户失败： e=",e);
            return Response.responseFail("通知失败！",null);
        }
        if(Objects.nonNull(pushResult) && pushResult.isResultOK()){
            LOGGER.info("#### APP系统通知推送成功！pushResult={}",pushResult);
            return Response.responseSuccess("通知成功！",pushResult);
        }
        return Response.responseFail("通知失败！",null);

    }

    /**
     * 测试通知API
     * @param messageType   通知类型
     * @param content   通知内容
     * @param fromUserName  来源用户名
     * @param fromUserId    来源用户ID
     * @param fromUserAvatar    来源用户头像地址
     * @param toUserName    目标用户名
     * @param queryId   查询动态的ID
     * @param aliasList 通知别名列表
     * @return 返回通知的结果
     */
    @JWTIgnore
    @PostMapping("/alert/alias")
    Response testAlertMessageWithAlias(@RequestParam("messageType") Integer messageType, @RequestParam("content") String content,
                                       @RequestParam("fromUserName") String fromUserName, @RequestParam("fromUserId") Integer fromUserId,
                                       @RequestParam("fromUserAvatar")  String fromUserAvatar, @RequestParam("toUserName")  String toUserName,
                                       @RequestParam("queryId") Integer queryId,@RequestParam("aliasList") List<String> aliasList){
        Map<String, String> extrasMap = MessageUtil.createMessage2ExtrasMap(messageType, content, fromUserName, fromUserId, fromUserAvatar, toUserName, queryId);
        // 调用推送服务
        PushResult pushResult = jPushService.alertMessage2AndroidWithAliasAndExtras(extrasMap.get("title"), extrasMap.get("content"), extrasMap, aliasList);
        if(Objects.nonNull(pushResult) && pushResult.isResultOK()){
            return Response.responseSuccess("测试定向通知成功！",pushResult);
        }
        return Response.responseSuccess("测试定向通知失败！",pushResult);

    }




}

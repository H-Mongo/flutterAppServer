package top.hzwei.bju.service.notification.impl;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.DefaultResult;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.rmi.runtime.Log;
import top.hzwei.bju.common.model.Response;
import top.hzwei.bju.config.JPushConfig;
import top.hzwei.bju.service.notification.JPushService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/20 20:55
 */
@Service
public class JPushServiceImpl implements JPushService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JPushServiceImpl.class);

    /**
     * JPush配置（appKey,masterSecret）
     */
    @Resource(name = "jPushConfig")
    private JPushConfig jPushConfig;

    /**
     * JPush客户端配置
     */
    @Resource(name = "customClientConfig")
    private ClientConfig clientConfig;

    /**
     *  极光推送客户端
     */
    private JPushClient jPushClient;


    @Override
    public PushResult alertMessage2AndroidWithAliasAndExtras(String title, String alert, Map<String,String> extras, List<String> aliasList) {
        LOGGER.info("#### 安卓系统通知服务，入参：title={}, alert={}, extras={}, alias={}",title,alert,extras,aliasList);
        // 推送客户端
        JPushClient client = null;
        // 推送结果
        PushResult pushResult = null;
//        // 推送的别名数组
//        String[] alias = null;
//        if(!CollectionUtils.isEmpty(aliasList)){
//            // 将List集合转为数组
//            alias = aliasList.toArray(new String[aliasList.size()]);
//        }
        try {
            // 获取推送客户端
            client = buildJPushClient();
            // 推送信息
            pushResult = client.sendPush(buildPushPayload_androidAlert_with_aliasAndExtras(alert,title,extras,aliasList));
//            pushResult = client.sendAndroidNotificationWithAlias(title, alert, extras, CollectionUtils.isEmpty(aliasList) ? new String[0] : aliasList.toArray(new String[aliasList.size()]));
            LOGGER.info("#### 安卓系统通知服务，");
        } catch (APIConnectionException e) {
            LOGGER.error("#### 安卓系统通知服务，推送客户端连接异常！e={}",e);
        } catch (APIRequestException e) {
            LOGGER.error("#### 安卓系统通知服务，推送客户端请求异常！e={}",e);
        } finally {
            // 关闭推送连接
            client.close();
            LOGGER.info("#### 安卓系统通知服务，推送连接关闭！");
        }
        if(Objects.nonNull(pushResult) && pushResult.isResultOK()){
            LOGGER.info("#### 安卓系统通知服务，通知成功！");
            LOGGER.info("#### 安卓系统通知服务，推送结果：pushResult={}",pushResult);
            return pushResult;
        }
        LOGGER.info("#### 安卓系统通知服务，通知失败！");
        return pushResult;
    }

    @Override
    public PushResult sendPushAllPlatformWithConfigAlias(String title, String msgContent, Map<String, String> extras, List<String> alias) throws APIConnectionException, APIRequestException {
        LOGGER.info("#### 通过Alias向Android发送自定义信息：title={}，msgContent={}，extras={}，alias={}",title, msgContent, extras, alias);
        PushResult pushResult = null;
        JPushClient jPushClient = null;
        try {
            jPushClient = buildJPushClient();
            pushResult = jPushClient.sendPush(buildJPushPayLoad(title, msgContent, extras, alias));
        } catch (Exception e) {
            LOGGER.error("#### 推送出错了！e={}",e);
        } finally {
            jPushClient.close();
            LOGGER.info("#### 推送连接已关闭！");
        }
        if(Objects.nonNull(pushResult) && pushResult.isResultOK()){
            LOGGER.info("#### 通过Alias向Android发送自定义信息：pushResult={}",pushResult);
            return pushResult;
        }
        LOGGER.warn("#### 通过Alias向Android发送自定义信息失败");
        return null;
    }

    @Override
    public Response sendPushAllPlatform(String title, String msgContent, Map<String, String> extras, List<String> alias) {
        return null;
    }

    @Override
    public boolean setDeviceAlias(String registrationId, String phoneNumber) {
        // 获取客户端对象
        JPushClient jc = buildJPushClient();
//        jc.bindMobile(registrationId,phoneNumber);
        // 结果
        DefaultResult defaultResult = null;
        try {
            // 设置别名
            defaultResult = jc.updateDeviceTagAlias(registrationId, phoneNumber, null, null);
        } catch (APIConnectionException e) {
            LOGGER.error("#### 设置设备别名，连接异常！");
        } catch (APIRequestException e) {
            LOGGER.error("#### 设置设备别名，请求异常！");
        } finally {
            LOGGER.info("#### 设置设备别名，连接已关闭！");
            jc.close();
        }
        // 设置成功
        if(!Objects.isNull(defaultResult) && defaultResult.isResultOK()){
            return true;
        }
        // 设置失败
        return false;
    }

    /**
     * 构建JPush客户端，唯一客户端对象
     */
    private JPushClient buildJPushClient(){
        LOGGER.info("#### 构建JPushClient接收参数：jPushConfig = {}, clientConfig = {}",jPushConfig,clientConfig);
        if(jPushClient != null){
            return jPushClient;
        }
        jPushClient = new JPushClient(jPushConfig.getMasterSecret(),jPushConfig.getAppKey(),null,clientConfig);
        return jPushClient;
    }

    /**
     *  构造安卓平台的通知携带extras，并支持alias
     *
     * @param alert     通知的内容
     * @param title     通知的标题
     * @param extras    通知的附加信息
     * @param alias     别名（为NULL通知全平台）
     * @return  返回创建的PushPayload（推送载荷）对象
     */
    private PushPayload buildPushPayload_androidAlert_with_aliasAndExtras(String alert, String title, Map<String,String> extras, List<String> alias){
        LOGGER.info("#### 极光推送服务，构建推送的PushPayload对象，入参：alert={}，title={}，extras={}，alias={}",alert,title,extras,alias);
        // 按照参数构建PushPayload
        final PushPayload pushPayload = PushPayload.newBuilder()
                // 推送平台为Android
                .setPlatform(Platform.android())
                // 听众为空，则是全平台，否则定向推送
                .setAudience(CollectionUtils.isEmpty(alias) ? Audience.all() : Audience.alias(alias))
                // 通知
                .setNotification(Notification.android(alert, title, extras))
                .build();
        LOGGER.info("#### 极光推送服务，构建推送的PushPayload对象，结果：pushPayload={}",pushPayload);
        return pushPayload;
    }




    /**
     * 构建自定义消息的JPushPayLoad对象
     * @param title 标题
     * @param msgContent 消息体
     * @param extras 附加信息
     * @param alias 推送别名
     * @return 返回实例化的推送负载对象
     */
    private PushPayload buildJPushPayLoad(String title, String msgContent, Map<String, String> extras, List<String> alias){
        // 构建PayLoad对象
        PushPayload pushPayload = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                // 推送的对象（接受push的用户），为空全员推送
                .setAudience(CollectionUtils.isEmpty(alias) ? Audience.all() : Audience.alias(alias))
                // 消息体（自定义消息）
                .setMessage(Message.newBuilder()
                        // 消息头
                        .setTitle(title)
                        // 消息体
                        .setMsgContent(msgContent)
                        // 附加信息
                        .addExtras(CollectionUtils.isEmpty(extras) ? new HashMap<>(10) : extras)
                        .build())
                .build();
        if(pushPayload == null){
            LOGGER.info("#### 推送载荷为空！");
            return null;
        }
        return pushPayload;
    }


}

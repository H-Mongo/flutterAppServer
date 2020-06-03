package top.hzwei.bju.service.notification;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import top.hzwei.bju.common.model.Response;

import java.util.List;
import java.util.Map;

/**
 *  JPush服务
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/19 20:27
 */
public interface JPushService {

    /**
     *  推送通知（安卓系统用户）
     * @param title 通知的标题
     * @param alert 通知的内容
     * @param extras 通知的附加信息
     * @param aliasList 需要通知的别名列表
     * @return  返回极光推送结果信息
     */
    PushResult alertMessage2AndroidWithAliasAndExtras(String title, String alert, Map<String,String> extras, List<String> aliasList);


    /**
     * 自定义配置JPush，通过Alias向Android平台推送
     * @param title 标题
     * @param msgContent 消息体
     * @param extras 附加信息
     * @param alias 推送别名
     * @return 返回推送后的结果
     * @throws APIConnectionException
     * @throws APIRequestException
     */
    PushResult sendPushAllPlatformWithConfigAlias(String title, String msgContent, Map<String, String> extras, List<String> alias) throws APIConnectionException, APIRequestException;

    /**
     * 自定义配置JPush，通过Alias向全平台推送
     * @param title 标题
     * @param msgContent 消息体
     * @param extras 附加信息
     * @param alias 推送别名
     * @return 返回推送后的结果
     */
    Response sendPushAllPlatform(String title, String msgContent, Map<String, String> extras, List<String> alias);

    /**
     *  设置设备的别名
     * @param registrationId 注册号
     * @param phoneNumber 手机号码
     * @return 返回成功或者失败
     */
    boolean setDeviceAlias(String registrationId, String phoneNumber);


}

package top.hzwei.bju.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 阿里验证码工具类
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/26 17:14
 */
@Slf4j
public class AliSMSUtil {

    /**
     * 区域ID
     */
    private static final String REGION_ID = "cn-hangzhou";
    /**
     * 访问KeyID
     */
    private static final String ACCESS_KEY_ID = "LTAI4Fuo6QrxsNAxNf5Vf7JC";
    /**
     * 访问秘钥
     */
    private static final String ACCESS_SECRET = "NwmE5Gdiz5ahRqcEFvChCi5UPlaZpJ";
    private static final String SYS_DOMAIN = "dysmsapi.aliyuncs.com";
    private static final String SIGN_NAME = "BJU信息平台";
    private static final String TEMPLATE_CODE = "SMS_184221150";


    /**
     * 发送阿里验证码
     * @param phoneNumber 目标电话号码，支持多用户，使用英文逗号（,）分隔
     * @param validateCode 手机验证码
     * @return 成功返回json形式（response对象的data参数）字符串，失败返回null
     */
    public static String sendSMSWithMobile(String phoneNumber, String validateCode){
        log.info("#### 发送阿里验证码，接收：phoneNumber={},validateCode={}",phoneNumber,validateCode);

        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESS_KEY_ID, ACCESS_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        // 模板参数
        Map<String,String> templateParam = new HashMap<>(1);
        templateParam.put("code",validateCode);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(SYS_DOMAIN);
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        //设置连接超时为5000毫秒
        request.setSysReadTimeout(5000);
        //设置读超时为5000毫秒
        request.setSysConnectTimeout(5000);
        // 接收的电话号码
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        // 验证码签名
        request.putQueryParameter("SignName", SIGN_NAME);
        // 验证码模板
        request.putQueryParameter("TemplateCode", TEMPLATE_CODE);
        // 模板中的参数:${code}
        request.putQueryParameter("TemplateParam", new Gson().toJson(templateParam));
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("#### 发送阿里验证码成功！response={}，data={}",response,response.getData());
//            {
//                "Message": "OK",
//                "RequestId": "7C3CBCF4-BEFC-4F62-BD6B-6E203B429C0C",
//                "BizId": "871311682715557353^0",
//                "Code": "OK"
//            }
            return response.getData();
        } catch (ServerException e) {
            log.error("#### 发送阿里验证码，服务器异常！",e);
        } catch (ClientException e) {
            log.error("#### 发送阿里验证码，客户端异常！",e);
        }
        log.info("#### 发送阿里验证码失败！" );
        return null;
    }

    /**
     * 生成手机验证码
     * @return 返回6位数的手机验证码字符串
     */
    public static String createPhoneValidateCode(){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        // 六位随机数生成
        for (int i = 0; i < 6; i++){
            int nextInt = random.nextInt(10);
            stringBuilder.append(nextInt);
        }
        log.info("#### 生成的手机验证码为：code={}", stringBuilder.toString());
        return stringBuilder.toString();
    }

    /**
     * 验证手机验证码是否合法
     * @param phoneCode 待验证手机验证码
     * @param cacheCode 缓存中获取的验证码
     * @return 返回验证结果（true：合法，false：不合法）
     */
    public static boolean validatePhoneCode(String phoneCode, String cacheCode){
        return phoneCode.equals(cacheCode);
    }

}

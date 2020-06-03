package top.hzwei.bju.controller.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.hzwei.bju.common.annotation.JWTIgnore;
import top.hzwei.bju.common.model.Response;
import top.hzwei.bju.utils.AliSMSUtil;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 短信API
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/27 1:30
 */
@Slf4j
@RestController
@RequestMapping("SMS")
public class SMSController {

    /**
     * 验证码在缓存中的key前缀
     */
    private static final String KEY_PREFIX = "SMS_VERIFY_CODE_PHONE:";

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @JWTIgnore
    @PostMapping("/verifyCode/{phoneNumber}")
    public Response sendVerifyCode(@PathVariable("phoneNumber") String phoneNumber){
        log.info("#### SMS信息发送验证码，phoneNumber={}",phoneNumber);
        try {
            // 创建key
            String key = KEY_PREFIX+phoneNumber;
            // 生成手机验证码
            String validateCode = AliSMSUtil.createPhoneValidateCode();
            log.info("#### SMS验证码，生成的验证码为：validateCode={}" ,validateCode);
            // 验证码存入Redis中，设置过期值（存在则不在存入）
            final Boolean absent = redisTemplate.opsForValue().setIfAbsent(key,validateCode,5,TimeUnit.MINUTES);
            log.info("#### SMS验证码，Redis中key={}，存储新值结果：absent={}（true：成功，false：失败）",key,absent);
            log.info("#### SMS手机验证码存入Redis，key={}",key);
            // 发送的短信验证码
            final String sendCode = redisTemplate.opsForValue().get(key);
//            String sendCode = absent ? (String) redisTemplate.opsForValue().get(key) :  validateCode;
            log.info("#### SMS验证码，发送的验证码为: sendCode={}",sendCode);
            // 发送短信验证码
            String res = AliSMSUtil.sendSMSWithMobile(phoneNumber, sendCode);
            Map<String, Object> map = new JacksonJsonParser().parseMap(res);
            log.info("#### SMS信息返回，res={},转为map={}",res,map);
            String smsSendOk = "OK";
            String smsResultCode = "Code";
            if(smsSendOk.equals(map.get(smsResultCode))){
                log.info("#### SMS验证码，发送成功！");
                return Response.responseSuccess("验证码发送成功！",map);
            }
            log.info("#### SMS验证码，发送失败！");
            log.error("#### SMS验证码，SMS发送手机验证码失败，查看日志获得更多信息！");
            return Response.responseFail("#### SMS发送手机验证码失败，查看日志获得更多信息！",null);
        } catch (Exception e){
            log.info("#### SMS验证码存入Redis失败！",e);
            log.error("#### SMS验证码，SMS发送手机验证码失败，查看日志获得更多信息！");
            return Response.responseFail("#### SMS发送手机验证码失败，查看日志获得更多信息！",null);
        }
    }

}

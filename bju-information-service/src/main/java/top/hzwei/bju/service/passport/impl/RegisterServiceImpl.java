package top.hzwei.bju.service.passport.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.hzwei.bju.model.mapper.UserMapper;
import top.hzwei.bju.service.passport.RegisterService;
import top.hzwei.bju.utils.MD5Utils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/27 1:14
 */
@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String,String> redisTemplate;



    @Override
    public boolean existNickname(String nickname) {
        log.info("#### 注册服务，检测用户名存在与否，参数：nickname={}",nickname);
        Integer count = userMapper.existNickname(nickname);
        log.info("#### 注册服务，查询到昵称记录数：count={}",count);
        return count != 0;
    }

    @Override
    public boolean existMobile(String userMobile) {
        log.info("#### 注册服务，检测手机号存在与否，参数：userMobile={}",userMobile);
        Integer count = userMapper.existMobile(userMobile);
        log.info("#### 注册服务，查询到手机号记录数：count={}",count);
        return count != 0;
    }

    @Override
    public boolean verificationCode(String userMobile, String verifyCode) {
        log.info("#### 注册服务，验证码验证，参数：userMobile={},verifyCode={}",userMobile,verifyCode);
        // key
        final String key = "SMS_VERIFY_CODE_PHONE:"+userMobile;
        // 验证码
        final String val = redisTemplate.opsForValue().get(key);
        log.info("#### 注册服务，Redis中获取验证码！key={},val={}",key,val);
        if(Objects.isNull(val)){
            return false;
        }
        return verifyCode.equals(val);

    }

    @Override
    public boolean registerUser(String userNickName, String userMobile, String userPassword) {
        log.info("#### 注册服务，新用户注册：userNickName={},userMobile={},userPassword={}",userNickName,userMobile,userPassword);


        try {
            // 密码加密存储
            final String password = MD5Utils.generatePassword(userPassword);
            log.info("#### 注册服务，新用户注册，加密后的密码为：password={}",password);
            Integer count = userMapper.registerUser(userNickName,userMobile,password);
            // 删除验证码
            final String key = "SMS_VERIFY_CODE_PHONE:"+userMobile;
            final Boolean delete = redisTemplate.delete(key);
            if(delete){
                log.info("#### 注册服务，成功销毁redis中的验证码信息，key={}",key);
            } else {
                log.info("#### 注册服务，销毁redis中的验证码信息失败，key={}",key);
            }
            log.info("#### 注册服务，新用户注册记录：count={}",count);
            return count != 0;
        } catch (Exception e) {
            log.info("#### 注册服务，新用户注册记录产生异常！");
            log.error("#### 注册服务，异常信息如下：",e);
            return false;
        }

    }
}

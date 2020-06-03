package top.hzwei.bju.service.passport.impl;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.hzwei.bju.config.JWTConfig;
import top.hzwei.bju.model.entry.User;
import top.hzwei.bju.model.mapper.UserMapper;
import top.hzwei.bju.service.biz.UserService;
import top.hzwei.bju.service.passport.LoginService;
import top.hzwei.bju.utils.JWTUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/26 0:58
 */
@Slf4j
@Service
public class LoginSerivceImpl implements LoginService {

    @Autowired
    private JWTConfig jwtConfig;

    @Autowired
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private UserService userService;

    @Override
    public Map<String, Object> loginWithUP(String userMobile, String password) {
        log.info("#### 用户登录service,获取参数：userMobile={},password={}",userMobile,password);
        try {
            // 查找用户信息
            final User user = userMapper.selectUserByUP(userMobile,password);
            if(Objects.isNull(user)){
                return null;
            }
            // 生成声明
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId",user.getUserId());
            claims.put("roleId",user.getRoleId());
            claims.put("userMobile",user.getUserMobile());
            claims.put("userNickname",user.getUserNickname());
            claims.put("userAvatar",user.getUserAvatar());

            log.info("#### 生成token声明：claims={}",claims);
            // 生成Token
            String authorizationHeaderToken = JWTUtil.createAuthorizationHeader(JWTUtil.generateToken(claims, jwtConfig));
            if(StringUtils.isEmpty(JWTUtil.getRealTokenWithPrefix(authorizationHeaderToken))){
                log.info("#### Service获取token失败!");
                return null;
            }
            // key形如：user:1:token:adfadgrtag.datatea.fadfa
            String key = JWTUtil.createRedisKeyWithToken(authorizationHeaderToken);
            // 将token存入Redis
            redisTemplate.opsForValue().setIfAbsent(key,user,24, TimeUnit.HOURS);
            log.info("#### 用户登录信息成功存入Redis，key={}",key);
            // 查询用户的计数项（获赞，动态，@）
            final Map<String, Object> allCounts = userService.getInfoPageAllCounts(user.getUserId());
            // 返回Token与user信息
            Map<String,Object> map = new HashMap<>(2);
            map.put("token", authorizationHeaderToken);
            map.put("user", user);
            // 计数项
            map.put("allCounts",allCounts);
            return map;
        } catch (Exception e){
            log.error("#### 用户登录因异常终止！e=",e);
            return null;
        }
    }

    @Override
    public boolean logout(String headerToken) {

        Boolean isDelete = false;
        try {
            // 去得到前缀的token串
//            String token = JWTUtil.getRealTokenWithPrefix(headerToken);
//            log.info("#### 注销登录服务，解析携带前缀后token串为：token={}",token);
            // 生成Redis中的key
            final String key = JWTUtil.createRedisKeyWithToken(headerToken);
            log.info("#### 注销登录服务，依据token串生成Redis中的key：key={}",key);
            // 删除已经存在的key
            isDelete = redisTemplate.delete(key);
        } catch (Exception e) {
            log.info("#### 注销登录服务，删除Redis中存储的token信息，产生异常，注销登录失败！");
            log.error("#### 注销登录服务，异常信息：e{}",e);
            return false;
        }
        return isDelete ? true : false;
    }
}

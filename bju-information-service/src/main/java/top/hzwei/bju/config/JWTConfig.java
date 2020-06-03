package top.hzwei.bju.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jwt配置信息
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/26 1:38
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig {
    /**
     * 签发方
     */
    String iss;
    /**
     * 面向的用户
     */
    String sub;
    /**
     * 过期时间（24h）
     */
    Integer expiration;
    /**
     * 秘钥
     */
    String secret;

}

package top.hzwei.bju.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/20 19:43
 */
@Data
@Component("jPushConfig")
@ConfigurationProperties(prefix = "jpush")
public class JPushConfig {

    /**
     * JPush的AppKey
     */
    private String appKey;
    /**
     * JPush的MasterSecret
     */
    private String masterSecret;

}

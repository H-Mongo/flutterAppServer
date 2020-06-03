package top.hzwei.bju.config;

import cn.jiguang.common.ClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;


/**
 * 宝大服务平台的配置类
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/20 21:27
 */
@Configuration
public class BjuISConfig {
    /**
     * 创建配置JPush的客户端配置对象
     * @return
     */

    @Bean
    public ClientConfig customClientConfig(){
        ClientConfig clientConfig = ClientConfig.getInstance();
        // 连接请求超时
        clientConfig.setConnectionRequestTimeout(10 * 1000);
        // 存活时间 1天
        clientConfig.setTimeToLive(60 * 60 * 24);
        // 连接超时10s
        clientConfig.setConnectionTimeout(10 * 1000);
        // 重试次数
        clientConfig.setMaxRetryTimes(3);
        // 开发环境
        clientConfig.setApnsProduction(false);
        // 推送地址
        //clientConfig.setPushHostName();
        return clientConfig;
    }


}

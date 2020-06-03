package top.hzwei.bju.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.hzwei.bju.common.intercepter.JWTIntercepter;

/**
 * JWT配置器
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/26 16:08
 */
@Configuration
public class JWTIntercepterConfigurer implements WebMvcConfigurer {

    /**
     * 图片的本地存储地址
     */
    @Value(value = "${images.upload.path}")
    private  String uploadImagesLocalPath;

    /**
     * 图片本地存储对应的映射路径
     */
    @Value(value = "${image.mapping}")
    private String imageMapping;



    @Bean
    public JWTIntercepter jwtIntercepter(){
        return new JWTIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册JWT拦截器
        registry.addInterceptor(jwtIntercepter())
                .excludePathPatterns("/static/**")
                .excludePathPatterns(imageMapping)
                .addPathPatterns("/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 动态图片的存储位置
//        final String movingImages = "file:E:/graduationDesign/project/app_server/images/moving";
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler(imageMapping)
                .addResourceLocations(uploadImagesLocalPath);
    }


}


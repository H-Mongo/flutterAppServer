package top.hzwei.bju.common.annotation;

import java.lang.annotation.*;

/**
 * JWT请求忽略注解
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/26 1:59
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JWTIgnore {
}

package top.hzwei.bju.common.intercepter;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.hzwei.bju.common.annotation.JWTIgnore;
import top.hzwei.bju.common.model.Response;
import top.hzwei.bju.config.JWTConfig;
import top.hzwei.bju.utils.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;

/**
 * JWT拦截器
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/26 1:45
 */
@Slf4j
public class JWTIntercepter extends HandlerInterceptorAdapter {
    
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private JWTConfig jwtConfig;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("#### JWT拦截器，请求对象信息：ContentType={}",request.getContentType());
        final Enumeration<String> headers = request.getHeaders("Headers");
        while (headers.hasMoreElements()){
            log.info("#### JWT拦截器，请求对象信息中的header内的信息：{}",headers.nextElement());
        }

        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if(Objects.nonNull(handlerMethod.getMethodAnnotation(JWTIgnore.class))){
                return true;
            }
        }

        // 预检请求
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        // 获取header token串
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        String contentType = request.getContentType();
        log.info("#### JWT拦截器获取请求头部的文本信息：ContentType={}, content-type={}",contentType,request.getHeader("content-type"));
        log.info("#### JWT拦截检查鉴权头部信息：authHeader={}",authHeader);
        if(StringUtils.isEmpty(authHeader)){
            log.info("#### 请求无Auth头部，未登录！");
            reponseErrorAdvice(response,"未登录");
            return false;
        }

        // 获取签名token并解析
        Claims claims = JWTUtil.validateAndParseToken(JWTUtil.getRealTokenWithPrefix(authHeader), jwtConfig.getSecret());
        if(Objects.isNull(claims)){
            log.info("#### 拦截器验证解析Token失败！");
            // 因为header前缀Bearer,防止无效token攻击
            reponseErrorAdvice(response,"无效Token");
            return false;
        }
        return true;
    }

    private void reponseErrorAdvice(HttpServletResponse response,String message){
        Response fail = Response.responseFail(message, null);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(new Gson().toJson(fail));
        } catch (IOException e) {
            log.info("#### JWT拦截器返回错误通知失败！",e);
        }


    }

}

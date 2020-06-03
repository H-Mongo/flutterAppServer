package top.hzwei.bju.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import top.hzwei.bju.config.JWTConfig;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/26 12:54
 */
@Slf4j
public class JWTUtil {
    /**
     * token 前缀
     */
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    private JWTUtil() {}

    /***
     *  生成Token
     * @param claims 声明
     * @param jwtConfig JWT配置信息
     * @return 返回完成签名的Token字符串
     */
    public static String generateToken(Map<String,Object> claims, JWTConfig jwtConfig){
        log.info("#### 生成Token，获取参数：claims={}，配置信息：jwtConfig={}",claims,jwtConfig);
        try {
            // JWT头部参数
            Map<String, Object> headerParam = new HashMap<>(2);
            headerParam.put("alg","HS256");
            headerParam.put("typ","JWT");

            long currentTimeMillis = System.currentTimeMillis();

            long expiration = currentTimeMillis +  jwtConfig.getExpiration() * 1000;
            log.info("#### Token签发时间：now={}ms，有效时间：expiration={}",currentTimeMillis,expiration);
            String jwtToken = Jwts.builder()
                    // 头部参数
                    .setHeaderParams(headerParam)
                    // 签发人
                    .setIssuer(jwtConfig.getIss())
                    // 面向用户
                    .setSubject(jwtConfig.getSub())
                    // 接受方（手机号码）
                    .setAudience(claims.putIfAbsent("userMobile","User").toString())
                    // 声明（也称载荷）
                    .setClaims(claims)
                    // 有效时间（24h）
                    .setExpiration(new Date(expiration))
                    // 签发时间
                    .setIssuedAt(new Date(currentTimeMillis))
                    // 生效时间
                    .setNotBefore(new Date(currentTimeMillis))
                    // 签名算法和秘钥
                    .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                    .compact();
            log.info("#### 生成Token：jwtToken={}",jwtToken);
            return jwtToken;
        } catch (Exception e){
            log.error("#### JWT签名失败！异常信息：",e);
            return null;
        }
    }

    /**
     *  生成Token串
     * @param token 完成签名的token
     * @return 返回HTTP请求头中的Token串
     */
    public static String createAuthorizationHeader(String token){
        return AUTHORIZATION_HEADER_PREFIX + token;
    }

    /**
     * 获取Token
     * @param headerToken auth头中的Token串
     * @return 返回真正的原始Token串
     */
    public static String getRealTokenWithPrefix(String headerToken){
        return headerToken.substring(AUTHORIZATION_HEADER_PREFIX.length());
    }

    /**
     * 验证并解析Token
     * @param token 已签名原始Token
     * @param secret 签名秘钥
     * @return 返回JWT中的payload信息
     */
    public static Claims validateAndParseToken(String token,String secret){
        log.info("#### 待验证与解析token：token={}",token);
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            log.info("#### 成功解析Token，获得claims={}",claims);
            return claims;
        } catch (SignatureException e) { //签名异常
            log.error("#### JWT签名异常，秘钥不匹配！异常信息：",e);
        } catch (MalformedJwtException e) { //JWT格式错误
            log.error("#### JWT格式错误！异常信息：",e);
        } catch (ExpiredJwtException e) { //JWT过期
            log.error("#### Token已过期！异常信息：",e);
        } catch (UnsupportedJwtException e) { //不支持该JWT
            log.error("#### 无效Token！异常信息：",e);
        } catch (IllegalArgumentException e) { //参数错误异常
            log.error("#### 参数错误异常！异常信息：",e);
        }
        return null;
    }

    /**
     * 登录信息Redis key
     * @param authToken 含有前缀的token
     * @return 返回一个由Token串构成的key
     */
    public static String createRedisKeyWithToken(String authToken){
        // 获取Token
        String token = getRealTokenWithPrefix(authToken);
        String key = "token:"+token;
        return key;
    }


}

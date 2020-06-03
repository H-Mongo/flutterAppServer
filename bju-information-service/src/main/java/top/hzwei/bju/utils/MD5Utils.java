package top.hzwei.bju.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

/**
 * MD5工具类
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/25 22:59
 */
@Slf4j
public class MD5Utils {

    /**
     * 生成MD5密码
     * @param password 待加密密码
     * @return MD加密的字符串
     */
    public static String generatePassword(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();

    }

    /**
     * 密码比较
     * @param encrypted 已加密
     * @param password 待加密
     * @return 返回密码是否一致
     */
    public static boolean comparePassword(String encrypted, String password){
        return encrypted.equals(generatePassword(password));

    }

}

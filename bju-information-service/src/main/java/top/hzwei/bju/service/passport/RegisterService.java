package top.hzwei.bju.service.passport;

/**
 * 注册服务
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/27 1:14
 */
public interface RegisterService {

    /**
     * 注册昵称唯一性检验
     * @param nickname 注册昵称
     * @return 返回验证结果：true：验证通过，false，反之
     */
    boolean existNickname(String nickname);

    /**
     * 注册手机号码唯一性检验
     * @param userMobile 注册手机号
     * @return 返回验证结果：true：验证通过，false，反之
     */
    boolean existMobile(String userMobile);

    /**
     * 手机验证码时效性检查
     * @param userMobile 注册手机号
     * @param verifyCode 手机验证码
     * @return 返回验证结果：true：验证通过，false，反之
     */
    boolean verificationCode(String userMobile, String verifyCode);

    /**
     * 注册用户
     * @param userNickName 用户昵称
     * @param userMobile 手机号码
     * @param userPassword 密码
     * @return 返回注册的结果（true：成功，false：失败）
     */
    boolean registerUser(String userNickName, String userMobile, String userPassword);
}

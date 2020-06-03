package top.hzwei.bju.service.passport;


        import top.hzwei.bju.model.entry.User;

        import java.util.Map;

/**
 * 登录服务
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/26 0:57
 */
public interface LoginService {

    /**
     * 用户名密码登录
     * @param userMobile 用户名
     * @param password 密码
     * @return 返回携带用户信息和Token的集合
     */
    Map<String, Object> loginWithUP(String userMobile, String password);

    /**
     *  注销登录
     * @param headerToken 登录令牌
     * @return  返回注销结果（true/false）
     */
    boolean logout(String headerToken);
}

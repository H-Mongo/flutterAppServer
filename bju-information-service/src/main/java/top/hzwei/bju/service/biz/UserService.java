package top.hzwei.bju.service.biz;

import org.springframework.web.multipart.MultipartFile;
import top.hzwei.bju.model.entry.User;

import java.util.List;
import java.util.Map;

/**
 * 用户服务
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/3 22:04
 */
public interface UserService {

    /**
     * 获取获赞，@我，动态三个计数
     * @param userId 用户ID
     * @return 返回计数的Map集合
     */
    Map<String, Object> getInfoPageAllCounts(Integer userId);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 返回true修改成功，false修改失败
     */
    boolean updateUserInfo(User user);

    /**
     * 更换用户头像
     * @param userId 用户ID
     * @param file 头像文件
     * @return 返回用户头像保存的新URL
     */
    String changeUserAvatar(Integer userId, MultipartFile file);

    /**
     *  用户信息模糊搜索
     * @param keywords  搜索关键字
     * @return  返回模糊搜索的用户数据列表
     */
    List<Map<String, Object>> userInfosFuzzySearch(String keywords);

    /**
     *  获取指定的用户信息
     * @param userId 用户ID
     * @return 返回用户信息对象
     */
    User getUserInfoById(Integer userId);

    /**
     * 用户密码修改
     * @param userMobile    用户手机号
     * @param userNickname  用户昵称
     * @param newPassword   新密码
     * @return  返回修改结果（true/false）
     */
    boolean modifyUserPassword(String userMobile, String userNickname, String newPassword);
}

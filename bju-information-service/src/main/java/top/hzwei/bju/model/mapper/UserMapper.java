package top.hzwei.bju.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.hzwei.bju.model.entry.User;

import java.util.List;
import java.util.Map;

/**
 * 用户Mapper
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/26 22:54
 */
@Mapper
public interface UserMapper {

    /**
     * 用户名密码，获取登录用户
     * @param userMobile 用户手机号码
     * @param password 密码
     * @return 用户存在，返回登录用户信息，否则，返回null
     */
    User selectUserByUP(@Param("userMobile") String userMobile, @Param("password") String password);

    /**
     * 检测昵称唯一性
     * @param nickname 昵称
     * @return 返回指定昵称的记录数
     */
    Integer existNickname(@Param("nickname") String nickname);

    /**
     * 检测手机号唯一性
     * @param userMobile 手机号码
     * @return 返回指定手机号的记录数
     */
    Integer existMobile(@Param("userMobile") String userMobile);

    /**
     * 新用户注册
     * @param userNickName 用户昵称
     * @param userMobile 手机号码
     * @param userPassword 密码
     * @return 返回执行成功的记录数
     */
    Integer registerUser(@Param("userNickName") String userNickName,@Param("userMobile") String userMobile,@Param("userPassword") String userPassword);


    /**
     * 获取获赞，@我，动态三个计数
     * @param userId 用户ID
     * @return 返回计数的Map集合
     * @see UserMapper#selectCounts(java.lang.Integer)
     */
    @Deprecated
    Map<String, Object> getInfoPageAllCounts(@Param("userId") Integer userId);

    /**
     * 获取用户计数（动态，点赞，@）
     * @param userId 用户ID
     * @return 返回一个携带计数Map的List集合
     */
    List<Map<String,Object>> selectCounts(@Param("userId") Integer userId);

    /**
     * 修改用户信息：
     *   为了解决动态SQL的对NGNL语句解析问题（例如属性名中的lt会被解析为小于符号），将个别名称进行了别名设置
     * @param userId 用户ID
     * @param userNickname 昵称
     * @param userBorth 生日
     * @param userAddress 地址
     * @param userHobby 爱好
     * @param userMotto 签名
     * @param facultyId 院系ID
     * @param specialtyId 专业ID
     * @param facultyName 院系名称
     * @param specialtyName 专业名称
     * @return 返回受影响的记录数
     */
    Integer updateUserInfo(@Param("userId") Integer userId, @Param("userNickname") String userNickname, @Param("userBorth") String userBorth,
                           @Param("userAddress") String userAddress, @Param("userHobby") String userHobby, @Param("userMotto") String userMotto,
                           @Param("fId") Integer facultyId, @Param("sId") Integer specialtyId, @Param("fName") String facultyName,
                           @Param("sName") String specialtyName);

    /**
     * 更换用户头像
     * @param userId 用户ID
     * @param avatarUrl 头像的URL
     * @return 返回受影响的记录数
     */
    Integer changeUserAvatar(@Param("userId") Integer userId, @Param("avatarUrl") String avatarUrl);

    /**
     *  用户信息模糊搜索
     * @param keywords  搜索关键字
     * @return  返回模糊搜索的用户数据列表
     */
    List<Map<String, Object>> userInfosFuzzySearch(@Param("keywords") String keywords);

    /**
     *  按照ID获取用户信息
     * @param userId 用户ID
     * @return 返回用户实体
     */
    User selectUserByUserId(@Param("userId") Integer userId);

    /**
     *  用户密码修改
     * @param userMobile    用户手机号
     * @param userNickname  用户昵称
     * @param newPassword   新密码
     * @return  返回受影响的记录数
     */
    Integer modifyUserPassword(@Param("userMobile") String userMobile, @Param("userNickname") String userNickname, @Param("newPassword") String newPassword);
}

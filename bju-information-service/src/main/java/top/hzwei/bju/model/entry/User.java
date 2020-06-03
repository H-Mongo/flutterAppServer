package top.hzwei.bju.model.entry;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户实体类
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/22 23:26
 */
@Data
@Accessors(chain = true)
public class User  implements Serializable {
    /**
     * 用户ID（唯一）
     */
    Integer userId;
    /**
     * 角色ID（唯一）
     */
    Integer roleId;
    /**
     * 用户头像
     */
    String userAvatar;
    /**
     * 用户昵称
     */
    String userNickname;
    /**
     * 用户密码
     */
    String userPassword;
    /**
     * 手机号码（登录使用）
     */
    String userMobile;
    /**
     * 出生年月
     */
    String userBorth;
    /**
     * 用户地区
     */
    String userAddress;
    /**
     * 用户爱好（多个爱好使用‘,’分割）
     */
    String userHobby;
    /**
     * 用户签名
     */
    String userMotto;
    /**
     * 专业ID
     */
    Integer facultyId;
    /**
     * 院系ID
     */
    Integer specialtyId;
    /**
     * 院系名称
     */
    String facultyName;
    /**
     * 专业名称
     */
    String specialtyName;


}

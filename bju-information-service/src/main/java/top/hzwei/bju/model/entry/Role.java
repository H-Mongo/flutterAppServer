package top.hzwei.bju.model.entry;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色实体类
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/22 23:08
 */
@Data
public class Role  implements Serializable {
    /**
     * 角色ID
     */
    Integer roleId;
    /**
     * 角色名称
     */
    String roleName;
    /**
     * 角色描述
     */
    String roleDesc;
    /**
     * 角色状态（0：启用，1：禁用，2：删除）
     */
    Integer roleStatus;

}

package top.hzwei.bju.model.entry;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 模块实体类
 * @author hzw
 * @version 1.0
 * @date 2020/2/22 22:41
 */
@Data
public class Module  implements Serializable {
    /**
     *  模块ID
     */
   Integer moduleId;
    /**
     * 模块名称
     */
   String moduleName;
    /**
     * 模块状态（0：启用；1：禁用；2：删除）
     */
   Integer moduleStatus;
    /**
     * 模块介绍
     */
   String moduleDesc;

   Date createTime;
   Date updateTime;
    /**
     * 操作人（最新一次操作）
     */
   String operatorName;

}

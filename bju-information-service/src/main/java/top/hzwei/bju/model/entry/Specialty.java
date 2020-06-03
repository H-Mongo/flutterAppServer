package top.hzwei.bju.model.entry;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 专业实体类
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/22 23:12
 */
@Data
public class Specialty  implements Serializable {

    /**
     * 专业ID
     */
    Integer specialtyId;
    /**
     * 专业代码
     */
    String specialtyCode;
    /**
     * 专业名称
     */
    String specialtyName;
    /**
     * 专业状态（0：正常，1：禁用，2：删除）
     */
    Integer specialtyStatus;
    /**
     * 专业描述
     */
    String specialtyDesc;
    /**
     * 院系ID
     */
    Integer facultyId;
    Date createTime;
    Date updateTime;
    /**
     * 操作人（最新一次操作）
     */
    String operatorName;


}

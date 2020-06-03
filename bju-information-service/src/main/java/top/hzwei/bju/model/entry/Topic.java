package top.hzwei.bju.model.entry;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 话题实体类
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/22 23:21
 */
@Data
public class Topic  implements Serializable {
    /**
     * 话题ID
     */
    Integer topicId;
    /**
     * 话题名称
     */
    String topicName;
    /**
     * 话题描述
     */
    String topicDesc;
    /**
     * 话题状态（0：正常；1：禁用；2：删除）
     */
    Integer topicStatus;
    /**
     * 模块ID
     */
   Integer moduleId;
   Date createTime;
   Date updateTime;
    /**
     * 操作人（最新一次操作）
     */
   String operatorName;

}

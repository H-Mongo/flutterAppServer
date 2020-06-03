package top.hzwei.bju.model.entry;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 院系实体类
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/22 22:30
 */
@Data
public class Faculty  implements Serializable {

    /**
     * 院系ID
     */
    Integer facultyId;
    /**
     * 院系名称
     */
    String facultyName;
    /**
     * 院系英文名
     */
    String facultyEnglish;
    /**
     * 院系英文名缩写
     */
    String englishAbbreviations;
    /**
     * 院系简介
     */
    String facultyDesc;
    /**
     * 院系状态（0：正常，1：删除）
     */
    Integer facultyStatus;
    Date createTime;
    Date updateTime;
    /**
     * 操作人（最新一次操作）
     */
    String operatorName;


}

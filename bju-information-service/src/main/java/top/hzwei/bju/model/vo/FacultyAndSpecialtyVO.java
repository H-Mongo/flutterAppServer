package top.hzwei.bju.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 院系专业VO
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/5 17:24
 */
@Data
public class FacultyAndSpecialtyVO {

    /**
     * 院系名称
     */
    private String facultyName;
    /**
     * 院系下的专业
     */
    private List<String> specialtyNames;

}

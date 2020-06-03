package top.hzwei.bju.service.biz;


import top.hzwei.bju.model.vo.FacultyAndSpecialtyVO;

import java.util.List;
import java.util.Map;

/**
 * 院系接口层
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/5 14:43
 */
public interface FacultyService {

    /**
     * 根据院系和专业名称查找对应的院系ID与专业ID号
     * @param facultyName 院系名称
     * @param specialtyName 专业ID
     * @return 返回带有院系ID和专业ID的map
     */
    Map<String,Object> getFacultyIdAndSpeicaltyIdWithName(String facultyName, String specialtyName);

    /**
     * 院系下对应的专业
     * @return 返回VO
     */
    List<Map<String,List<String>>> getAllFacultyAndSpecial();






}

package top.hzwei.bju.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.hzwei.bju.model.vo.FacultyAndSpecialtyVO;

import java.util.List;
import java.util.Map;

/**
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/5 14:42
 */
@Mapper
public interface FacultyMapper {

    /**
     * 根据院系和专业名称查找对应的院系ID与专业ID号
     * @param facultyName 院系名称
     * @param specialtyName 专业ID
     * @return 返回带有院系ID和专业ID的map
     */
    Map<String,Object> getFacultyIdAndSpeicaltyIdWithName(@Param("facultyName") String facultyName, @Param("specialtyName") String specialtyName);


    /**
     * 院系下对应的专业
     * @return 返回一个视图模型
     */
    List<FacultyAndSpecialtyVO> getAllFacultyAndSpecial();






}

package top.hzwei.bju.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 话题Mapper
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/5 19:04
 */
@Mapper
public interface TopicMapper {

    /**
     * 按照模块ID获取话题
     * @param moduleId 模块ID
     * @return 返回话题名称集合
     */
    List<String> getTopicsByModuleId(@Param("moduleId") Integer moduleId);
}

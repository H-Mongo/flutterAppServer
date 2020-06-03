package top.hzwei.bju.service.biz;

import java.util.List;

/**
 * 话题服务
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/5 19:00
 */
public interface TopicService {

    /**
     * 按照模块ID获取话题
     * @param moduleId 模块ID
     * @return 返回话题名称集合
     */
    List<String> getTopicsByModuleId(Integer moduleId);

}

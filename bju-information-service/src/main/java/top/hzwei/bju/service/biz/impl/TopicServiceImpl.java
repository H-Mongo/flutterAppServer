package top.hzwei.bju.service.biz.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.hzwei.bju.model.mapper.TopicMapper;
import top.hzwei.bju.service.biz.TopicService;

import java.util.List;

/**
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/5 19:02
 */
@Slf4j
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<String> getTopicsByModuleId(Integer moduleId) {
        List<String> topics = topicMapper.getTopicsByModuleId(moduleId);
        log.info("#### 话题服务，获取模块下的话题，结果：topics={}",topics);
        if(CollectionUtils.isEmpty(topics)){
            log.info("#### 话题服务，该模块下无话题信息！");
            return null;
        }
        return topics;
    }
}

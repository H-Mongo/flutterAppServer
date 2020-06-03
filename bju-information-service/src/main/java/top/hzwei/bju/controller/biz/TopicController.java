package top.hzwei.bju.controller.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.hzwei.bju.common.annotation.JWTIgnore;
import top.hzwei.bju.common.model.Response;
import top.hzwei.bju.service.biz.TopicService;

import java.util.List;

/**
 * 话题Controller
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/23 0:47
 */
@Slf4j
@RestController
@RequestMapping("module/")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @JWTIgnore
    @GetMapping("topic")
    Response getTopicsByModuleId(@RequestParam("moduleId") Integer moduleId){
        log.info("#### 话题API，获取模块下的话题信息，入参：moduleId={}",moduleId);
        List<String> topics = topicService.getTopicsByModuleId(moduleId);
        if(CollectionUtils.isEmpty(topics)){
            return Response.responseSuccess("该模块下无话题信息！",null);
        }
        return Response.responseSuccess("成功获取话题信息！",topics);
    }

}

package top.hzwei.bju.controller.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hzwei.bju.common.annotation.JWTIgnore;
import top.hzwei.bju.common.model.Response;
import top.hzwei.bju.model.vo.FacultyAndSpecialtyVO;
import top.hzwei.bju.service.biz.FacultyService;

import java.util.List;
import java.util.Map;

/**
 * 院系Controller
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/23 0:49
 */
@Slf4j
@RestController
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @JWTIgnore
    @GetMapping("faculties")
    Response allFacultyAndSpecialty(){
        List<Map<String, List<String>>> allFacultyAndSpecial = facultyService.getAllFacultyAndSpecial();
        log.info("#### 院系API，获取结果：allFacultyAndSpecial={}",allFacultyAndSpecial);
        if(CollectionUtils.isEmpty(allFacultyAndSpecial)){
            return Response.responseFail("暂无院系信息！",null);
        }
        return Response.responseSuccess("成功获取院系信息！",allFacultyAndSpecial);
    }


}

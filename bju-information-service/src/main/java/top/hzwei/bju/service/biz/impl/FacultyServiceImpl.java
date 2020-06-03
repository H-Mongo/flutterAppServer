package top.hzwei.bju.service.biz.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.hzwei.bju.model.mapper.FacultyMapper;
import top.hzwei.bju.model.vo.FacultyAndSpecialtyVO;
import top.hzwei.bju.service.biz.FacultyService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/5 14:48
 */
@Slf4j
@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyMapper facultyMapper;

    @Override
    public Map<String, Object> getFacultyIdAndSpeicaltyIdWithName(String facultyName, String specialtyName) {
        log.info("#### 院系服务层，按名称查找院系与专业ID，入参：facultyName={},specialtyName={}",facultyName,specialtyName);
        Map<String, Object> idMap = facultyMapper.getFacultyIdAndSpeicaltyIdWithName(facultyName, specialtyName);
        log.info("#### 院系服务层，查询结果：idMap={}",idMap);
        if(CollectionUtils.isEmpty(idMap)){
            log.info("####  院系服务层，按名称查找院系与专业ID，查找失败！");
            return null;
        }
        return idMap;
    }

    @Override
    public List<Map<String,List<String>>> getAllFacultyAndSpecial() {
        List<FacultyAndSpecialtyVO> facultyAndSpecial = facultyMapper.getAllFacultyAndSpecial();
        log.info("#### 院系服务，获取院系下的所有专业，原始结果：facultyAndSpecial={}",facultyAndSpecial);
        if(CollectionUtils.isEmpty(facultyAndSpecial)){
            return null;
        }
        // 将信息转为前端可以展示的形式
        List<Map<String, List<String>>> mapList = facultyAndSpecial.stream().map(facultyAndSpecialtyVO -> {
            final Map<String, List<String>> map = new HashMap<>();
            // 封装Map
            map.put(facultyAndSpecialtyVO.getFacultyName(), facultyAndSpecialtyVO.getSpecialtyNames());
            return map;
        }).collect(Collectors.toList());
        log.info("#### 院系服务，前端可视化VO，mapList={}",mapList);
        return mapList;
    }
}

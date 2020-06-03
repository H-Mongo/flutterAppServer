package top.hzwei.bju.service.biz.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import top.hzwei.bju.model.entry.User;
import top.hzwei.bju.model.mapper.UserMapper;
import top.hzwei.bju.service.biz.UserService;
import top.hzwei.bju.utils.FileUploadUtil;
import top.hzwei.bju.utils.MD5Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/4 18:50
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> getInfoPageAllCounts(Integer userId) {
        log.info("#### 用户服务层，入参：userId={}",userId);
        List<Map<String, Object>> countsMapList = userMapper.selectCounts(userId);
        log.info("#### 用户服务层，查询原始结果：countsMapList={}",countsMapList);
        if(CollectionUtils.isEmpty(countsMapList)){
            return null;
        }
        // 取出原始的数据进行业务数据的包装
        Map<String,Object> countMap = new HashMap<>(3);
        // 动态计数项和获赞数
        countMap.putAll(countsMapList.get(0));
        // @
        countMap.put("atCount",countsMapList.get(1).get("likeCount"));
        log.info("#### 用户服务层，获取用户计数项，计数集合：countMap={}",countMap);
        return countMap;
    }

    @Override
    public boolean updateUserInfo(User user) {
        log.info("#### 用户服务层，更新用户信息，入参：user={}",user);
        Integer count = userMapper.updateUserInfo(user.getUserId(),user.getUserNickname(),user.getUserBorth(),
                                                user.getUserAddress(),user.getUserHobby(),user.getUserMotto(),
                                                user.getFacultyId(),user.getSpecialtyId(),user.getFacultyName(),
                                                user.getSpecialtyName());
        log.info("#### 用户服务层，更新用户信息，结果：count={}",count);
        return count != 0;
    }

    @Override
    public String changeUserAvatar(Integer userId, MultipartFile file) {
        log.info("#### 用户头像修改服务，入参：userId={}，file={}",userId,file);
        // 开始头像的上传
        final String avatarUrl = FileUploadUtil.singleFileUpload(file);
        log.info("#### 用户头像修改服务，上传工具上传头像，结果：avatarUrl={}",avatarUrl);
        // 是否上传成功
        if(avatarUrl == null){
            log.info("#### 用户头像修改服务，头像上传保存失败！");
            return null;
        }log.info("#### 用户头像修改服务，头像上传保存成功！");
        // 修改DB中的用户头像地址
        final Integer count = userMapper.changeUserAvatar(userId, avatarUrl);
        log.info("#### 用户头像修改服务，修改DB中的用户头像地址，结果：count={}",count);
        return count != 0 ? avatarUrl : null;
    }

    @Override
    public List<Map<String, Object>> userInfosFuzzySearch(String keywords) {
        log.info("#### 用户服务层，用户信息模糊搜索，入参：keywords={}",keywords);
        List<Map<String, Object>> fuzzyList = userMapper.userInfosFuzzySearch(keywords);
        log.info("#### 用户服务层，用户信息模糊搜索，结果：fuzzyList={}",fuzzyList);
        if(CollectionUtils.isEmpty(fuzzyList)){
            return null;
        }
        return fuzzyList;
    }

    @Override
    public User getUserInfoById(Integer userId) {
        User user = userMapper.selectUserByUserId(userId);
        return user;
    }

    @Override
    public boolean modifyUserPassword(String userMobile, String userNickname, String newPassword) {
        log.info("#### 用户密码修改服务，入参：userMobile={}，userNickname={}，newPassword={}",userMobile,userNickname,newPassword);
        Integer count = userMapper.modifyUserPassword(userMobile,userNickname, newPassword);
        return count != 0;
    }
}

package top.hzwei.bju.controller.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.hzwei.bju.common.model.Response;
import top.hzwei.bju.model.entry.User;
import top.hzwei.bju.service.biz.FacultyService;
import top.hzwei.bju.service.biz.UserService;
import top.hzwei.bju.utils.MD5Utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户Controller
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/23 0:35
 */
@Slf4j
@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FacultyService facultyService;

    @GetMapping("info/counts")
    Response getInfoPageAllCounts(@RequestParam("userId") Integer userId){
        log.info("#### 用户接口API，获取计数，入参：userId={}",userId);
        Map<String, Object> allCounts = userService.getInfoPageAllCounts(userId);
//        if(CollectionUtils.isEmpty(allCounts)){
//            Response.responseSuccess("该计数项无数据！",allCounts);
//        }
        return Response.responseSuccess("获取计数项数据！",allCounts);
    }

    @PutMapping("info/edit")
    Response editUserInfo(@RequestParam("userId") Integer userId, @RequestParam("nickname") String nickname,
                          @RequestParam("motto") String motto, @RequestParam("hobby") String hobby,
                          @RequestParam("birthday") String birthday, @RequestParam("address") String address,
                          @RequestParam("yxzy") String yxzy){
        log.info("#### 用户接口API，入参：userId={}，nickname={}，motto={}，hobby={}，birthday={}，address={}，yxzy={}",userId,nickname,motto,hobby,birthday,address,yxzy);
        // 切割院系专业字符串
        String[] split = yxzy.split("-");
        log.info("#### 用户接口API，修改用户信息，yxzy={}",split.toString());
        Map<String, Object> idMap = facultyService.getFacultyIdAndSpeicaltyIdWithName(split[0], split[1]);
        if(CollectionUtils.isEmpty(idMap)){
            log.info("#### 用户接口API，修改用户信息，院系信息数据有误！");
            return Response.responseFail("院系信息数据有误！",null);
        }
        // 更新的用户信息
        final User user = new User()
                .setUserId(userId)
                .setUserNickname(nickname)
                .setUserMotto(motto)
                .setUserHobby(hobby)
                .setUserBorth(birthday)
                .setUserAddress(address)
                .setFacultyId(Integer.parseInt(String.valueOf(idMap.get("facultyId"))))
                .setSpecialtyId(Integer.parseInt(String.valueOf(idMap.get("specialtyId"))))
                .setFacultyName(split[0])
                .setSpecialtyName(split[1]);

        boolean b = userService.updateUserInfo(user);
        if(!b){
            return Response.responseFail("修改信息失败！",null);
        }
        // 查询用户信息，刷新数据
        final User updateUser = userService.getUserInfoById(user.getUserId());
        log.info("#### 用户接口API，修改用户信息，结果：updateUser={}",updateUser);
        return Response.responseSuccess("修改信息成功！",Objects.isNull(updateUser) ? null: updateUser);
    }

    @PostMapping(value = "info/avatar",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    Response changeUserAvatar(@RequestParam("userId") Integer userId, @RequestParam("file") MultipartFile file){
        log.info("#### 用户头像修改，入参：userId={}，file={}",userId,file);
        final String userAvatar = userService.changeUserAvatar(userId, file);
        if(Objects.isNull(userAvatar)){
            log.info("#### 用户头像修改，用户头像上传失败！");
            return Response.responseFail("头像上传失败！",null);
        }
        log.info("#### 用户头像修改，用户头像上传成功！");
        return Response.responseSuccess("头像上传成功！",userAvatar);
    }

    @GetMapping("fuzzy")
    Response getUserInfoByKeyword(@RequestParam("keywords") String keywords){
        log.info("#### 用户接口API，模糊搜索用户信息：keywords={}",keywords);
        List<Map<String,Object>> fuzzySearchList = userService.userInfosFuzzySearch(keywords);
        log.info("#### 用户接口API，模糊搜索用户信息，查询结果：fuzzySearchList={}",fuzzySearchList);
        if(CollectionUtils.isEmpty(fuzzySearchList)){
            return Response.responseFail("无相匹配的用户信息！",null);
        }
        return Response.responseSuccess("成功获取匹配用户信息！",fuzzySearchList);

    }

    @GetMapping("info/{userId}")
    Response getUserInfoById(@PathVariable("userId") Integer userId){
        log.info("#### 获取指定用户的信息，入参：userId={}",userId);
        User user = userService.getUserInfoById(userId);
        log.info("#### 获取指定用户的信息，结果：user={}",user);
        if(Objects.isNull(user)){
            return Response.responseFail("用户ID无效",null);
        }
        return Response.responseSuccess("获取成功",user);

    }

    /**
     * 修改用户密码
     * @param userMobile    用户手机号码
     * @param userNickname  用户昵称
     * @param newPassword   新密码
     * @return 返回业务响应对象（包含数据）
     */
    @PutMapping("safe/password/modify")
    Response modifyUserPassword(@RequestParam("userMobile") String userMobile, @RequestParam("userNickname") String userNickname, @RequestParam("newPassword") String newPassword){
        log.info("#### 用户密码修改API，入参：userMobile={}，userNickname={}，newPassword={}",userMobile,userNickname,newPassword);
        boolean  b = userService.modifyUserPassword(userMobile,userNickname, MD5Utils.generatePassword(newPassword));
        if(!b){
            log.info("#### 用户密码修改API，修改密码失败！");
            return Response.responseFail("修改失败！",null);
        }
        log.info("#### 用户密码修改API，修改密码成功！");
        return Response.responseSuccess("修改成功！",null);
    }

}

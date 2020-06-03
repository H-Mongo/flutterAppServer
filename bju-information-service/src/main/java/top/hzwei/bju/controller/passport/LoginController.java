package top.hzwei.bju.controller.passport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import top.hzwei.bju.common.annotation.JWTIgnore;
import top.hzwei.bju.common.model.Response;
import top.hzwei.bju.service.passport.LoginService;
import top.hzwei.bju.utils.MD5Utils;

import java.util.HashMap;
import java.util.Map;


/**
 *  登录验证
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/19 20:42
 */
@Slf4j
@RestController
@RequestMapping("passport/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @JWTIgnore
    @PostMapping(value = "/upLogin")
    public Response login(@RequestParam("userMobile") String userMobile, @RequestParam("password") String password){
        log.info("#### 登录获取参数：userMobile={},原始密码：password={}",userMobile,password);
        Map<String, Object> map = loginService.loginWithUP(userMobile, MD5Utils.generatePassword(password));
        if(!CollectionUtils.isEmpty(map)){
            return Response.responseSuccess("登录成功",map);
        }
        return Response.responseFail("登录失败",null);
    }

    @JWTIgnore
    @GetMapping("/test")
    Response loginTest(){

        return Response.responseSuccess(null,"服务正常");

    }

    @PostMapping("/logout")
    Response logout(@RequestHeader("Authorization") String headerToken){
        log.info("#### 退出登录API，入参：headerToken={}",headerToken);
        boolean b = loginService.logout(headerToken);
        if(!b){
            log.info("#### 退出登录API，退出登录失败！");
            return Response.responseFail("退出失败！",null);
        }
        log.info("#### 退出登录API，退出登录成功！");
        return Response.responseSuccess("退出成功！",null);

    }



}

package top.hzwei.bju.controller.passport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.hzwei.bju.common.annotation.JWTIgnore;
import top.hzwei.bju.common.model.Response;
import top.hzwei.bju.service.passport.RegisterService;

/**
 * 注册API
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/25 14:55
 */
@Slf4j
@RestController
@RequestMapping("passport/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @JWTIgnore
    @PostMapping("/doRegister")
    public Response registerUser(@RequestParam("userNickName") String userNickName, @RequestParam("userMobile") String userMobile, @RequestParam("userPassword") String userPassword){
        log.info("#### 用户注册API，参数：userNickName={}，userMobile={}，userPassword={}",userNickName,userMobile,userPassword);
        boolean b= registerService.registerUser(userNickName,userMobile,userPassword);
        if(b){
            log.info("#### 用户注册API，注册成功！");
            return Response.responseSuccess("注册成功",null);
        }
        log.info("#### 用户注册API，注册失败！");
        return Response.responseSuccess("注册失败",null);
    }

    @JWTIgnore
    @PostMapping(value = "/validate/nickname")
    public Response existNickname(@RequestParam("nickname") String nickname){
        log.info("#### 用户注册API，昵称唯一性检测 nickname={}",nickname);
        boolean b = registerService.existNickname(nickname);
        if(b){
            return Response.responseFail("昵称已存在",null);
        }
        return Response.responseSuccess("昵称有效",null);
    }

    @JWTIgnore
    @PostMapping(value = "/validate/mobile")
    public Response existMobile(@RequestParam("mobile") String userMobile){
        log.info("#### 用户注册API，手机号唯一性检测 userMobile={}",userMobile);
        boolean b = registerService.existMobile(userMobile);
        if(b){
            return Response.responseFail("手机号码已存在",null);
        }
        return Response.responseSuccess("手机号码有效",null);
    }

    @JWTIgnore
    @PostMapping(value = "/validate/code")
    public Response verificationCode(@RequestParam("mobile") String userMobile, @RequestParam("verifyCode") String verifyCode){
        log.info("#### 用户注册API，手机验证码时效性检验 userMobile={},verifyCode={}",userMobile, verifyCode);
        boolean b = registerService.verificationCode(userMobile, verifyCode);
        log.info("#### 用户注册API，手机验证码时效性检验， 结果：{}",b);
        if(b){
            return Response.responseSuccess("验证码有效",null);
        }
        return Response.responseFail("验证码无效",null);
    }

}

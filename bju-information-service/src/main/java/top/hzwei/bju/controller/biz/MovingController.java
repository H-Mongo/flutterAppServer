package top.hzwei.bju.controller.biz;

import cn.jpush.api.push.PushResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.hzwei.bju.common.annotation.JWTIgnore;
import top.hzwei.bju.common.model.Response;
import top.hzwei.bju.model.vo.MovingDetailsVO;
import top.hzwei.bju.model.vo.MovingVO;
import top.hzwei.bju.service.biz.MovingService;
import top.hzwei.bju.service.notification.JPushService;
import top.hzwei.bju.utils.MessageUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 动态Controller
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/23
 */
@Slf4j
@RestController
@RequestMapping("moving/")
public class MovingController {

    @Autowired
    private MovingService movingService;

    @Autowired
    private JPushService jPushService;

    @PostMapping(value = "publish",consumes = "multipart/form-data")
    Response publishMoving(@RequestParam("movingType") Integer movingType, @RequestParam("movingContent")  String content,
                           @RequestParam("currentAddress")  String location, @RequestParam("userName") String author,
                           @RequestParam("selectedTopics[]")  List<String> topics, @RequestParam("files[]") MultipartFile[] files,
                           @RequestParam("userId") String authorId){
        log.info("#### 动态发布API，参数：movingType={},content={},topics={},author={},authorId={},location={},files={}",movingType,content,topics,author,authorId,location,files);
        boolean b = movingService.publishMoving(movingType, content, location, author, topics, files, authorId);
        if(b){
            log.info("#### 动态发布API，发布成功！");
            return Response.responseSuccess("发布成功！",null);
        }
        log.info("#### 动态发布API，发布失败！");
        return Response.responseFail("发布失败！",null);
    }

    @JWTIgnore
    @GetMapping("new")
    Response queryPublishedMovingsWithNew(){
        log.info("#### 动态API接口，开始获取最新动态信息...");
        List<MovingVO> movingVOS = movingService.queryPublishedMovingsWithNew();
        log.info("#### 动态API接口，获得最新动态信息：movingVOS={}",movingVOS);
        if(CollectionUtils.isEmpty(movingVOS)){
            return Response.responseFail("最新动态信息列表为空！",null);
        }
        return Response.responseSuccess("获取到最新动态列表！",movingVOS);
    }

    @JWTIgnore
    @GetMapping("hot")
    Response queryPublishedMovingsWithHot(){
        log.info("#### 动态API接口，开始获取热门动态信息...");
        List<MovingVO> movingVOS = movingService.queryPublishedMovingsWithHot();
        log.info("#### 动态API接口，获得热门动态信息：movingVOS={}",movingVOS);
        if(CollectionUtils.isEmpty(movingVOS)){
            return Response.responseFail("热门动态信息列表为空！",null);
        }
        return Response.responseSuccess("获取到热门动态列表！",movingVOS);
    }

    /***
     *  点击查看详情，并更新游览量数据
     * @param movingId  动态ID号
     * @return  返回业务响应对象（并回传数据）
     */
    @JWTIgnore
    @GetMapping(path = "details/{movingId}")
    Response getMovingDetailsByMovingId(@PathVariable(value = "movingId") Integer movingId){
        log.info("#### 动态API接口，查询指定动态的详情信息并更新浏览量，入参：movingId={}",movingId);
        MovingDetailsVO movingDetailsVO = movingService.selectMovingDetailsByMovingId(movingId);
        if(Objects.isNull(movingDetailsVO)){
            return Response.responseFail("无对应动态详情信息！",null);
        }
        return Response.responseSuccess("",movingDetailsVO);
    }

    @JWTIgnore
    @GetMapping(path = "fuzzy/{searchKeyword}")
    Response fuzzySearchMovingByKeyword(@PathVariable(value = "searchKeyword") String searchKeyword){
        log.info("#### 模糊搜索动态信息，搜索关键字为：searchKeyword={}",searchKeyword);
        List<Map<String,Object>> fuzzySearchMapList = movingService.fuzzySearchMovingByKeyword(searchKeyword);
        log.info("#### 模糊搜索动态信息，查询结果：fuzzySearchMapList={}",fuzzySearchMapList);
        if(CollectionUtils.isEmpty(fuzzySearchMapList)){
            return Response.responseFail("暂无匹配信息！",null);
        }
        return Response.responseSuccess("查到以下信息！",fuzzySearchMapList);
    }

    @JWTIgnore
    @GetMapping(path = "module/{moduleId}")
    Response queryMovingByModuleId(@PathVariable(value = "moduleId") Integer moduleId){
        log.info("#### 动态API，按照模块ID获取动态信息，入参：moduleId={}",moduleId);
        List<MovingVO>  movingVOList = movingService.queryMovingByModuleId(moduleId);
        log.info("#### 动态API，按照模块ID获取动态信息，结果：movingVOList={}",movingVOList);
        if(CollectionUtils.isEmpty(movingVOList)){
            return Response.responseFail("暂无相关数据！",null);
        }
        return Response.responseSuccess("成功获取数据！",movingVOList);
    }

    /**
     *  按照ID更新动态浏览量数据
     * @param movingId  动态ID编号
     * @return  返回业务响应对象（包含处理结果等信息）
     */
    @JWTIgnore
    @PutMapping("refresh/browse")
    Response updateBrowseCountByMovingId(@RequestParam("movingId") Integer movingId){
        log.info("#### 动态浏览量更新API，开始刷新浏览量...");
        boolean b = movingService.updateBrowseCountByMovingId(movingId);
        if(!b){
            log.info("#### 动态浏览量更新API，浏览量刷新失败！");
            log.error("#### 动态浏览量更新API，浏览量刷新失败！");
            return Response.responseFail("浏览量刷新失败！",null);
        }
        log.info("#### 动态浏览量更新API，浏览量刷新成功！");
        return Response.responseSuccess("浏览量刷新成功！",null);
    }

    /**
     *  按照ID更新动态的点赞量及点赞用户列表更新
     * @param movingId  动态ID编号
     * @param likeUserId    点赞用户编号
     * @return  返回业务响应对象（包含处理结果等信息）
     */
    @PutMapping("refresh/like")
    Response updateLikeCountByMovingId(@RequestParam("movingId") Integer movingId, @RequestParam("content") String content, @RequestParam("alias") String alias,
                                       @RequestParam("likeUserId") Integer likeUserId, @RequestParam("likeUserNickname") String likeUserNickname, @RequestParam("likeUserAvatar") String likeUserAvatar){
        log.info("#### 动态点赞量更新API，开始更新！入参：movingId={}，content={}，alias={}，likeUserId={}，likeUserNickname={}，likeUserAvatar={}",movingId, content, alias, likeUserId, likeUserNickname, likeUserAvatar);
        boolean b = movingService.updateLikeCountByMovingId(movingId, likeUserId);
        if(!b){
            log.info("#### 动态点赞量更新API，数据更新失败！");
            log.error("#### 动态点赞量更新API，数据更新失败！");
            return Response.responseFail("点赞失败！",null);
        }
        // 通知被点赞用户
        final Map<String, String> extrasMap = MessageUtil.createMessage2ExtrasMap(4, content, likeUserNickname, likeUserId, likeUserAvatar, null, movingId);
        final PushResult pushResult = jPushService.alertMessage2AndroidWithAliasAndExtras(extrasMap.get("title"), extrasMap.get("content"), extrasMap, Arrays.asList(alias));
        log.info("#### 动态点赞量更新API，通知被赞用户，结果：pushResult={}",pushResult);
        log.info("#### 动态点赞量更新API，数据更新成功！");
        return Response.responseSuccess("点赞成功！",null);
    }

    @PostMapping(value = "temporary",consumes = "multipart/form-data")
    Response temporaryMoving(@RequestParam("movingType") Integer movingType, @RequestParam("movingContent")  String content,
                             @RequestParam("currentAddress")  String location, @RequestParam("userName") String author,
                             @RequestParam("selectedTopics[]")  List<String> topics, @RequestParam("files[]") MultipartFile[] files,
                             @RequestParam("userId") String authorId){
        log.info("#### 动态保存到草稿箱API，参数：movingType={},content={},topics={},author={},authorId={},location={},files={}",movingType,content,topics,author,authorId,location,files);
        try {
            boolean b = movingService.temporaryMoving(movingType, content, location, author, topics, files, authorId);
            if(b){
                log.info("#### 动态保存到草稿箱API，保存成功！");
                return Response.responseSuccess("保存成功！",null);
            }
            log.info("#### 动态保存到草稿箱API，保存失败！");
            return Response.responseFail("保存失败！",null);
        } catch (Exception e) {
            log.info("#### 动态保存到草稿箱API，因异常失败！");
            log.error("#### 动态保存到草稿箱API，异常信息：",e);
            return Response.responseFail("保存失败！",null);
        }
    }

    /**
     * 用户的所有已发布动态
     * @param userId
     * @return
     */
    @GetMapping(path = "published/{userId}")
    Response queryAllMovingByUserId(@PathVariable(value = "userId") Integer userId){
        log.info("#### 查询指定用户已发布的动态列表API，入参：userId={}",userId);
        try {
            final List<MovingVO> movingVOList = movingService.queryAllMovingByUserId(userId);
            log.info("#### 查询指定用户已发布的动态列表API，结果：movingVOList={}",movingVOList);
            if(CollectionUtils.isEmpty(movingVOList)){
                return Response.responseFail("暂无动态信息！",null);
            }
            return  Response.responseSuccess("成功获取数据！",movingVOList);
        } catch (Exception e) {
            log.info("#### 查询指定用户已发布的动态列表API，出现异常！");
            log.error("#### 查询指定用户已发布的动态列表API，异常信息：",e);
            return Response.responseFail("服务器产生异常！",null);
        }
    }

    /**
     * 用户的所有草稿的动态
     * @param userId
     * @return
     */
    @GetMapping(path = "draft/{userId}")
    Response queryAllTemporaryMovingByUserId(@PathVariable(value = "userId") Integer userId){
        log.info("#### 查询指定用户动态草稿箱API，入参：userId={}",userId);
        try {
            final List<MovingVO> movingVOList = movingService.queryAllTemporaryMovingByUserId(userId);
            log.info("#### 查询指定用户动态草稿箱API，结果：movingVOList={}",movingVOList);
            if(CollectionUtils.isEmpty(movingVOList)){
                return Response.responseFail("暂无动态信息！",null);
            }
            return  Response.responseSuccess("成功获取数据！",movingVOList);
        } catch (Exception e) {
            log.info("#### 查询指定用户动态草稿箱API，出现异常！");
            log.error("#### 查询指定用户动态草稿箱API，异常信息：",e);
            return Response.responseFail("服务器产生异常！",null);
        }
    }

    /**
     * 重新发布动态
     * @param movingId  动态ID
     * @return 返回布尔值
     */
    @PutMapping(path = "republish/{movingId}")
    Response republishMovingByMovingId(@PathVariable(value = "movingId") Integer movingId){
        log.info("#### 重新发布动态API（草稿箱中的动态发布），入参：movingId={}",movingId);
        try {
            final boolean b = movingService.republishMovingByMovingId(movingId);
            log.info("#### 重新发布动态API（草稿箱中的动态发布），结果：b={}",b);
            if(!b){
                return Response.responseFail("发布失败！",null);
            }
            return Response.responseSuccess("发布成功！",null);
        } catch (Exception e) {
            log.info("#### 重新发布动态API（草稿箱中的动态发布），出现异常！");
            log.error("#### 重新发布动态API（草稿箱中的动态发布），异常信息：",e);
            return Response.responseFail("服务器产生异常！",null);
        }
    }

    /**
     * 删除动态
     * @param movingId  动态ID
     * @return 返回布尔值
     */
    @DeleteMapping(path = "delete/{movingId}")
    Response deleteMovingByMovingId(@PathVariable(value = "movingId") Integer movingId){
        log.info("#### 删除动态（已发布or草稿箱），入参：movingId={}",movingId);
        try {
            final boolean b = movingService.deleteMovingByMovingId(movingId);
            log.info("#### 删除动态（已发布or草稿箱），结果：b={}",b);
            if(!b){
                return Response.responseFail("删除失败！",null);
            }
            return Response.responseSuccess("删除成功！",null);
        } catch (Exception e) {
            log.info("#### 删除动态（已发布or草稿箱），出现异常！");
            log.error("#### 删除动态（已发布or草稿箱），异常信息：",e);
            return Response.responseFail("服务器产生异常！",null);
        }
    }






}

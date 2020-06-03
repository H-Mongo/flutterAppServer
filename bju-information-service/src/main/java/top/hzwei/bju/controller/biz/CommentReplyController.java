package top.hzwei.bju.controller.biz;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import top.hzwei.bju.common.model.Response;
import top.hzwei.bju.model.dto.CommentDTO;
import top.hzwei.bju.service.biz.CommentReplyService;
import top.hzwei.bju.service.notification.JPushService;
import top.hzwei.bju.utils.MessageUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 评论回复Controller
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/23 0:39
 */
@Slf4j
@RestController
@RequestMapping("moving/comment")
public class CommentReplyController {

    @Autowired
    private CommentReplyService commentReplyService;

    /**
     * 推送服务
     */
    @Autowired
    private JPushService jPushService;

    /**
     * 新增动态评论
     * @param commentDTO 评论内容DTO对象
     * @return 返回响应对象
     */
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response commentMoving(@RequestBody CommentDTO commentDTO){
        log.info("#### 新增动态评论API，commentDTO={}",commentDTO);
        boolean b = commentReplyService.addMvoingComment(commentDTO);
        if(!b){
            log.info("#### 新增动态评论API，新增评论失败！");
            return Response.responseFail("评论失败！",null);
        }

        // #### 推送评论用户附加信息
        // 推送信息不正确
        if(Strings.isEmpty(commentDTO.getCommentAlias())){
            return Response.responseFail("评论推送失败！",null);
        }
        Map<String, String> commentExtrasMap = MessageUtil.createMessage2ExtrasMap(commentDTO.getCommentParent() != null ? 3 : 2, commentDTO.getCommentContent(), commentDTO.getCommentAuthor(), commentDTO.getCommentUid(),
                commentDTO.getCommentAuthorAvatar(), null, commentDTO.getMovingId());
        // 推送评论用户列表
        List<String> commentAliasList = new ArrayList<>();
        commentAliasList.add(commentDTO.getCommentAlias());
        log.info("#### 新增动态评论API，推送评论用户：commentExtrasMap={}，评论推送别名列表：commentAliasList={}",commentExtrasMap,commentAliasList);
        try {
            PushResult commentPushResult = jPushService.alertMessage2AndroidWithAliasAndExtras(commentExtrasMap.get("title"), commentExtrasMap.get("content"), commentExtrasMap, commentAliasList);
            log.info("#### 新增动态评论API，评论用户推送结果：commentPushResult={}",commentPushResult);
        } catch (Exception e) {
            log.info("#### 新增动态评论API，评论用户推送失败！");
            log.error("#### 新增动态评论API，异常信息：e=",e);
        }


        // #### 推送@用户附加信息
        if(!CollectionUtils.isEmpty(commentDTO.getAtAliasList())){
            Map<String, String> atExtrasMap = MessageUtil.createMessage2ExtrasMap(1, commentDTO.getCommentContent(), commentDTO.getCommentAuthor(), commentDTO.getCommentUid(),
                    commentDTO.getCommentAuthorAvatar(), null, commentDTO.getMovingId());
            log.info("#### 新增动态评论API，推送@用户：atExtrasMap={}，atAliasList={}",atExtrasMap,commentDTO.getAtAliasList());

            try {
                PushResult atPushResult = jPushService.alertMessage2AndroidWithAliasAndExtras(atExtrasMap.get("title"), atExtrasMap.get("content"), atExtrasMap, commentDTO.getAtAliasList());
                log.info("#### 新增动态评论API，@用户推送结果：atPushResult={}",atPushResult);
            } catch (Exception e) {
                log.info("#### 新增动态评论API，@用户推送失败！");
                log.error("#### 新增动态评论API，异常信息：e=",e);
            }
        }

        // TODO 有关推送失败的信息提示

        // 返回评论的结果
        return Response.responseSuccess("评论成功！",null);
    }

    /**
     *  更新指定评论的点赞量及点赞用户列表
     * @param commentId 评论ID
     * @param likeUserId    点赞用户ID
     * @return  返回一个业务响应对象（携带数据）
     */
    @PutMapping("/like")
    Response updateCommentLikeCountAndUser(@RequestParam("movingId") Integer movingId, @RequestParam("alias") String alias, @RequestParam("content") String content,
                                           @RequestParam("commentId") Integer commentId, @RequestParam("likeUserId") Integer likeUserId,
                                           @RequestParam("likeUserNickname") String likeUserNickname, @RequestParam("likeUserAvatar") String likeUserAvatar){
        log.info("#### 评论回复更新指定评论的点赞量及点赞用户列表API层，入参：movingId={}，alias={}，content={}，commentId={}，likeUserId={}，likeUserNickname={}，likeUserAvatar={}",movingId,alias,content,commentId,likeUserId,likeUserNickname,likeUserAvatar);
        try {
            final boolean b = commentReplyService.updateCommentLikeCountAndUser(commentId, likeUserId);
            if(!b){
                log.info("#### 评论回复更新指定评论的点赞量及点赞用户列表API层，结果：失败！");
                return Response.responseFail("点赞失败！",null);
            }
            // 通知被赞用户
            final Map<String, String> extrasMap = MessageUtil.createMessage2ExtrasMap(5, content, likeUserNickname, likeUserId, likeUserAvatar, null, movingId);
            final PushResult pushResult = jPushService.alertMessage2AndroidWithAliasAndExtras(extrasMap.get("title"), extrasMap.get("content"), extrasMap, Arrays.asList(alias));
            log.info("#### 评论回复更新指定评论的点赞量及点赞用户列表API层，通知被赞用户，结果：pushResult={}",pushResult);
            log.info("#### 评论回复更新指定评论的点赞量及点赞用户列表API层，结果：成功！");
            return Response.responseSuccess("点赞成功！",null);
        } catch (Exception e) {
            log.info("#### 评论回复更新指定评论的点赞量及点赞用户列表API层，异常失败！");
            log.error("#### 评论回复更新指定评论的点赞量及点赞用户列表API层，异常信息：",e);
            return Response.responseFail("点赞失败！",null);
        }

    }


}

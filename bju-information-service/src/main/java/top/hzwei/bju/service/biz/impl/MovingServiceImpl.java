package top.hzwei.bju.service.biz.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import top.hzwei.bju.model.entry.Moving;
import top.hzwei.bju.model.mapper.MovingMapper;
import top.hzwei.bju.model.vo.MovingDetailsVO;
import top.hzwei.bju.model.vo.MovingVO;
import top.hzwei.bju.service.biz.MovingService;
import top.hzwei.bju.utils.FileUploadUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/3 15:49
 */
@Slf4j
@Service
public class MovingServiceImpl implements MovingService {

    @Autowired
    private MovingMapper movingMapper;

    @Override
    public boolean publishMoving(Integer movingType, String content, String location, String author, List<String> topics, MultipartFile[] files, String authorId) {
        log.info("#### 发布动态服务层，入参：movingType={},content={},topics={},author={},authorId={},location={},files={}",movingType,content,topics,author,authorId,location,files);
        try {
            // 处理图片上传
            String images = FileUploadUtil.uploadFile2Local(files);
            Moving moving = new Moving()
                    .setMovingType(movingType)
                    .setMovingContent(content)
                    .setPublishLocation(location)
                    .setMovingAuthor(author)
                    .setUserId(Integer.parseInt(authorId))
                    .setTopics(String.join(",",topics))
                    .setMovingImgs(images);
            log.info("#### 发布动态服务层，处理过程数据：images={},moving={}",images,moving);
            final Integer count = movingMapper.publishMoving(moving);
            log.info("#### 发布动态服务层，影响记录数：count={}",count);
            return count != 0;
        } catch (Exception e) {
            log.info("#### 发布动态服务层，发生异常，终止发布！");
            log.error("#### 发布动态服务层，异常错误终止，异常信息如下：",e);
            return false;
        }

    }

    @Override
    public boolean temporaryMoving(Integer movingType, String content, String location, String author, List<String> topics, MultipartFile[] files, String authorId) {
        try {
            // 处理图片上传
            String images = FileUploadUtil.uploadFile2Local(files);
            Moving moving = new Moving()
                    .setMovingType(movingType)
                    .setMovingContent(content)
                    .setPublishLocation(location)
                    .setMovingAuthor(author)
                    // 草稿箱
                    .setMovingStatus(1)
                    .setUserId(Integer.parseInt(authorId))
                    .setTopics(String.join(",",topics))
                    .setMovingImgs(images);
            log.info("#### 动态保存到草稿箱服务层，处理过程数据：images={},moving={}",images,moving);
            final Integer count = movingMapper.temporaryMoving(moving);
            log.info("#### 动态保存到草稿箱服务层，影响记录数：count={}",count);
            return count != 0;
        } catch (Exception e) {
            log.info("#### 动态保存到草稿箱服务层，发生异常，保存草稿箱失败！");
            log.error("#### 动态保存到草稿箱服务层，异常错误终止，异常信息如下：",e);
            return false;
        }
    }

    @Override
    public boolean modifyMoving() {
        /**
         * TODO:
         */

        return false;
    }

    @Override
    public List<MovingVO>  queryPublishedMovingsWithNew() {
        log.info("#### 动态服务，获取所有已经发布的最新动态列表。");
        List<MovingVO> movingVOList = movingMapper.queryPublishedMovingsWithNew();
        if(CollectionUtils.isEmpty(movingVOList)){
            log.info("#### 动态服务，没有最新的动态信息！");
            return null;
        }
        return movingVOList;
    }

    @Override
    public List<MovingVO> queryPublishedMovingsWithHot() {
        log.info("#### 动态服务，获取所有已经发布的热门动态列表。");
        List<MovingVO> movingVOList = movingMapper.queryPublishedMovingsWithHot();
        if(CollectionUtils.isEmpty(movingVOList)){
            log.info("#### 动态服务，没有热门的动态信息！");
            return null;
        }
        return movingVOList;
    }

    @Override
    public List<MovingVO> queryAllMovingByUserId(Integer userId) {
        return movingMapper.queryAllMovingByUserId(userId);
    }

    @Override
    public List<MovingVO> queryAllTemporaryMovingByUserId(Integer userId) {
        return movingMapper.queryAllTemporaryMovingByUserId(userId);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public MovingDetailsVO selectMovingDetailsByMovingId(Integer movingId) {
        log.info("#### 动态服务，查询指定动态的详情信息并更新浏览量，入参：movingId={}",movingId);
        // 首先更新浏览量信息
        final Integer count = movingMapper.updateBrowseCountByMovingId(movingId);
        log.info("#### 动态服务，查询指定动态的详情信息并更新浏览量，浏览量更新结果：count",count);
        // 更新失败，即不存在该动态ID号
        if(count == 0){
            return null;
        }
        // 查询动态详情信息
        final MovingDetailsVO movingDetailsVO = movingMapper.selectMovingDetailsByMovingId(movingId);
        log.info("#### 动态服务，查询指定动态的详情信息并更新浏览量，动态详情查询结果：movingDetailsVO={}",movingDetailsVO);
        if(Objects.isNull(movingDetailsVO)){
            return null;
        }
        return movingDetailsVO;
    }

    @Override
    public List<Map<String, Object>> fuzzySearchMovingByKeyword(String searchKeyword) {
        List<Map<String, Object>> fuzzyResultMapList = movingMapper.fuzzySearchMovingByKeyword(searchKeyword);
        return fuzzyResultMapList;
    }

    @Override
    public List<MovingVO> queryMovingByModuleId(Integer moduleId) {
        log.info("#### 动态服务层，按照模块ID查询动态：入参，moduleId",moduleId);
        List<MovingVO> movingVOList = movingMapper.queryMovingByModuleId(moduleId);
        return movingVOList;
    }

    @Override
    public boolean updateBrowseCountByMovingId(Integer movingId) {
        // 执行更新操作（浏览量数据自增1）
        Integer count =  movingMapper.updateBrowseCountByMovingId(movingId);
        return false;
    }

    @Override
    public boolean updateLikeCountByMovingId(Integer movingId, Integer likeUserId) {
        // 执行更新操作（点赞量数据自增1，点赞用户ID列表拼接点赞用户ID）
        Integer count =  movingMapper.updateLikeCountByMovingId(movingId, likeUserId);
        return count != 0;
    }

    @Override
    public boolean republishMovingByMovingId(Integer movingId) {
        final Integer count = movingMapper.republishMoving(movingId);
        return count != 0;
    }

    @Override
    public boolean deleteMovingByMovingId(Integer movingId) {
        final Integer count = movingMapper.deleteMoving(movingId);
        return count != 0;
    }

}

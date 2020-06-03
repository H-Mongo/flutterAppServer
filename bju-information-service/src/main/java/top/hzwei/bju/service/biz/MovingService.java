package top.hzwei.bju.service.biz;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import top.hzwei.bju.model.entry.Moving;
import top.hzwei.bju.model.vo.MovingDetailsVO;
import top.hzwei.bju.model.vo.MovingVO;

import java.util.List;
import java.util.Map;

/**
 * 动态服务层
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/3 15:37
 */
public interface MovingService {

    /**
     * 动态发布
     * @param movingType 动态类型
     * @param content 动态内容
     * @param location 定位信息
     * @param author 作者
     * @param topics 话题
     * @param files 动态附带的图片
     * @param authorId 作者ID
     * @return 返回true发布成功，false发布失败
     */
    boolean publishMoving(Integer movingType, String content,String location,String author,List<String> topics, MultipartFile[] files, String authorId);

    /**
     *  保存草稿箱
     * @param movingType 动态类型
     * @param content 动态内容
     * @param location 定位信息
     * @param author 作者
     * @param topics 话题
     * @param files 动态附带的图片
     * @param authorId 作者ID
     * @return 返回true发布成功，false发布失败
     */
    boolean temporaryMoving(Integer movingType, String content,String location,String author,List<String> topics, MultipartFile[] files, String authorId);

    /**
     * 修改动态（暂未开启）
     * @return 返回true修改成功，false修改失败
     */
    boolean modifyMoving();

    /**
     * 查询所有已发布的最新动态列表（暂未开启分页支持）
     * @return 返回MovingVO集合
     */
    List<MovingVO>  queryPublishedMovingsWithNew();

    /**
     * 查询所有已发布的热门动态列表（暂未开启分页支持）
     * @return 返回MovingVO集合
     */
    List<MovingVO> queryPublishedMovingsWithHot();

    /**
     * 查询指定用户所有已发布动态
     * @param userId 用户ID
     * @return 返回MovingVO列表
     */
    List<MovingVO> queryAllMovingByUserId(Integer userId);

    /**
     * 查询指定用户动态草稿箱
     * @param userId 用户ID
     * @return 返回MovingVO列表
     */
    List<MovingVO> queryAllTemporaryMovingByUserId(Integer userId);

    /**
     * 查询指定动态的详情
     * @param movingId 动态ID
     * @return 返回动态详情VO
     */
    MovingDetailsVO selectMovingDetailsByMovingId(@Param("movingId") Integer movingId);

    /**
     *  模糊搜索动态系信息
     * @param searchKeyword 搜索关键字
     * @return 返回模糊搜索的Map列表
     */
    List<Map<String, Object>> fuzzySearchMovingByKeyword(String searchKeyword);

    /**
     *  按照模块查询动态信息
     * @param moduleId  模块ID编号
     * @return  返回动态信息列表
     */
    List<MovingVO> queryMovingByModuleId(Integer moduleId);

    /**
     *  按照ID更新动态浏览量数据
     * @param movingId  动态ID编号
     * @return  返回处理结果（true/false）
     */
    boolean updateBrowseCountByMovingId(Integer movingId);

    /**
     *  按照ID更新动态点赞量及点赞用户列表更新
     * @param movingId  动态ID编号
     * @param likeUserId    点赞用户ID编号
     * @return  返回处理结果（true/false）
     */
    boolean updateLikeCountByMovingId(Integer movingId, Integer likeUserId);

    /**
     *  发布草稿箱的指定动态
     * @param movingId 动态ID
     * @return 返回true草稿发布成功，false草稿发布失败
     */
    boolean republishMovingByMovingId(Integer movingId);

    /**
     * 按照动态ID删除指定动态
     * @param movingId 动态ID
     * @return 返回true删除成功，false删除失败
     */
    boolean deleteMovingByMovingId(Integer movingId);


}

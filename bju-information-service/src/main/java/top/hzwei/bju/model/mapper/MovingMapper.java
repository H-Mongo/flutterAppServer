package top.hzwei.bju.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import top.hzwei.bju.model.entry.Moving;
import top.hzwei.bju.model.vo.MovingDetailsVO;
import top.hzwei.bju.model.vo.MovingVO;

import java.util.List;
import java.util.Map;

/**
 * 动态Mapper
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/3 16:18
 */
@Mapper
public interface MovingMapper {

    /**
     * 动态发布
     * @param moving 动态数据
     * @return 返回操作成功的记录数
     */
    Integer publishMoving(Moving moving);

    /**
     * 保存草稿箱
     * @param moving 动态数据
     * @return 返回操作成功的记录数
     */
    Integer temporaryMoving(Moving moving);

    /**
     * 发布草稿箱的动态
     * @param movingId 动态ID
     * @return 返回操作成功的记录数
     */
    Integer republishMoving(@Param("movingId") Integer movingId);

    /**
     * 删除动态(已发布or草稿箱)
     * @param movingId 动态ID
     * @return 返回操作成功的记录数
     */
    Integer deleteMoving(@Param("movingId") Integer movingId);

    /**
     * 修改动态（暂未开启）
     */
    boolean modifyMoving();

    /**
     * 查询所有已发布的最新动态列表（暂未开启分页支持）
     * @return 返回MovingVO集合
     */
    List<MovingVO> queryPublishedMovingsWithNew();

    /**
     * 查询所有已发布的热门动态列表（暂未开启分页支持）
     * @return 返回MovingVO集合
     */
    List<MovingVO> queryPublishedMovingsWithHot();

    /**
     * 查询指定用户的所有动态信息
     * @param userId 用户ID号
     * @return 返回MovingVO集合
     */
    List<MovingVO> queryAllMovingByUserId(@Param("userId") Integer userId);

    /**
     * 查询指定用户的所有草稿动态
     * @param userId 用户ID号
     * @return 返回MovingVO集合
     */
    List<MovingVO> queryAllTemporaryMovingByUserId(@Param("userId") Integer userId);

    /**
     *  查询指定动态的详情
     * @param movingId 动态ID
     * @return 返回动态详情VO
     */
    MovingDetailsVO selectMovingDetailsByMovingId(@Param("movingId") Integer movingId);

    /**
     *  模糊搜索动态信息
     * @param searchKeyword 搜索关键字
     * @return 返回符合条件的Map集合列表
     */
    List<Map<String, Object>> fuzzySearchMovingByKeyword(@Param("searchKeyword") String searchKeyword);

    /**
     *  按照模块查询动态信息
     * @param moduleId  模块ID编号
     * @return  返回动态信息列表
     */
    List<MovingVO> queryMovingByModuleId(@Param("moduleId") Integer moduleId);

    /**
     *  按照ID更新动态浏览量数据
     * @param movingId  动态ID编号
     * @return  返回执行数据操作成功的记录数
     */
    Integer updateBrowseCountByMovingId(@Param("movingId") Integer movingId);

    /**
     *  按照ID更新动态点赞量及点赞用户列表更新
     * @param movingId  动态ID编号
     * @param likeUserId    点赞用户ID编号
     * @return  返回执行数据操作成功的记录数
     */
    Integer updateLikeCountByMovingId(@Param("movingId") Integer movingId, @Param("likeUserId") Integer likeUserId);
}

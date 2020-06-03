package top.hzwei.bju.model.entry;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 动态实体类
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/22 20:48
 */
@Data
@Accessors(chain = true)
public class Moving  implements Serializable {

    /**
     * 动态ID唯一
     */
    private Integer movingId;
    /**
     * 动态添加图 （多个之间使用‘,’分割）
     */
    private String movingImgs;
    /**
     * 动态正文
     */
    private String movingContent;
    /**
     * 动态对应的话题（使用“-”分割的串）
     */
    private String topics;
    /**
     * 文章动态 （例如：兼职招聘，表白墙，万能墙，校园建议等）
     */
    private Integer movingType;
    /**
     * 点赞
     */
    private Integer movingLike;
    /**
     * 点赞用户（多用户使用‘,’分割）
     */
    private String movingLikeUser;
    /**
     * 浏览量
     */
    private Integer movingBrowse;
    /**
     * 动态作者
     */
    private String movingAuthor;
    /**
     * 作者ID号（唯一）
     */
    private Integer userId;
    /**
     * 评论总条数
     */
    private Integer commentCount;
    /**
     * 动态状态（0：发布，1：草稿，2：删除）
     */
    private Integer movingStatus;
    /**
     * 发布位置（地理位置字符串）
     */
    private String publishLocation;
    private Date createTime;
    private Date updateTime;

}

package top.hzwei.bju.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 动态VO模型
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/3 16:00
 */
@Data
public class MovingVO {

    private Integer movingAuthorId;
    private String movingAuthorAvatar;
    private String movingAuthorName;
    private String movingAuthorPhone;
    private String movingContent;
    private String movingId;
    private String movingImages;
    private String movingType;
    private String movingTopics;
    private Integer movingLike;
    private String movingLikeUsers;
    private Integer movingBrowse;
    private Integer movingCommentCount;
    @JsonFormat
    private LocalDateTime movingCreateTime;
    
    
    
}

package top.hzwei.bju.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 动态详情VO
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/6 21:52
 */
@Data
public class MovingDetailsVO {

   private Integer movingId;
   private String movingContent;
   private String movingImages;
   private String movingType;
   private String movingTopics;
   private Integer movingLike;
   private String movingLikeUsers;
   private Integer movingBrowse;
    @JsonFormat
   private LocalDateTime movingCreateTime;
   private Integer movingAuthorId;
   private String movingAuthorName;
   private String movingAuthorPhone;

   List<CommentReply> commentReplyList;

   @Data
   private static class CommentReply{
        private Integer commentId;
        private String commentContent;
        private Integer commentLike;
        private String commentLikeUsers;
       @JsonFormat
        private LocalDateTime commentCreateTime;
        private Integer commentAuthorId;
        private String commentAuthorAvatar;
        private String commentAuthorName;
        private String commentAuthorPhone;
        private Integer parentCommentId;
        private String parentCommentContent;
        private Integer parentCommentLike;
       @JsonFormat
        private LocalDateTime parentCommentCreateTime;
        private Integer parentCommentAuthorId;
        private String parentCommentAuthorAvatar;
        private String parentCommentAuthorName;
        private String parentCommentAuthorPhone;
    }


}

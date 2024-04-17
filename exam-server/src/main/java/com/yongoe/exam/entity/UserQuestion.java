package com.yongoe.exam.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 考生-答案
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("e_user_question")
@Schema(name = "考生-答案")
public class UserQuestion {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long examId;

    private Long paperId;

    private Long questionId;

    private String answer;

    private Double score;

    private Boolean isTrue;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private String content;
    @TableField(exist = false)
    private Integer type;
    @TableField(exist = false)
    private Integer totalScore;

    @TableField(exist = false)
    private List<QuestionOption> questionOption;

}
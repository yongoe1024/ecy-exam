package com.yongoe.ecy.exam.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 考生-试卷
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("e_user_paper")
@Schema(name = "考生-试卷")
public class UserPaper {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long examId;

    private Integer status;

    private LocalDateTime enterTime;

    private LocalDateTime finishTime;

    private Double score;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableField(exist = false)
    private ExamInfo examInfo;

    @TableField(exist = false)
    private String name;

}
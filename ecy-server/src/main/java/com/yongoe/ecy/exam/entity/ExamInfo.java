package com.yongoe.ecy.exam.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 考试信息
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("e_exam_info")
@Schema(name = "考试信息")
public class ExamInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String examName;

    private String examDesc;

    private Integer openType;

    private Integer duration;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer totalScore;

    private Integer passScore;

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

}
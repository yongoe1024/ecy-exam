package com.yongoe.ecy.exam.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 考试-部门
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("e_exam_dept")
@Schema(name = "考试-部门")
public class ExamDept {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long examId;

    private Long departmentId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

}
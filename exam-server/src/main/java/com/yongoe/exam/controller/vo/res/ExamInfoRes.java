package com.yongoe.exam.controller.vo.res;

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
@Schema(name = "考试信息Res")
public class ExamInfoRes {

    /**
     *
     */
    private Long id;
    /**
     * 考试名称
     */
    private String examName;
    /**
     * 考试描述
     */
    private String examDesc;
    /**
     * 开放形式
     */
    private Integer openType;
    /**
     * 考试时长(分钟)
     */
    private Integer duration;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 考试总分
     */
    private Integer totalScore;
    /**
     * 及格分
     */
    private Integer passScore;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String updateBy;
}
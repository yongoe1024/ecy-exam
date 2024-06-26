package com.yongoe.exam.controller.vo.res;

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
@Schema(name = "考生-试卷Res")
public class UserPaperRes {

    /**
     *
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 考试id
     */
    private Long examId;
    /**
     * 试卷状态
     */
    private Integer status;
    /**
     * 进入时间
     */
    private LocalDateTime enterTime;
    /**
     * 交卷时间
     */
    private LocalDateTime finishTime;
    /**
     * 得分
     */
    private Double score;
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

    private ExamInfoRes examInfo;

    private String name;
}
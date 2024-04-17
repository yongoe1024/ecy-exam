package com.yongoe.exam.controller.vo.res;

import com.yongoe.exam.entity.QuestionOption;
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
@Schema(name = "考生-答案Res")
public class UserQuestionRes {

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
     * 试卷id
     */
    private Long paperId;
    /**
     * 问题id
     */
    private Long questionId;
    /**
     * 答案
     */
    private String answer;
    /**
     * 得分
     */
    private Double score;
    /**
     * 是否正确
     */
    private Boolean isTrue;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 题目
     */
    private String content;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 总分
     */
    private Integer totalScore;
    /**
     * 答案
     */
    private List<QuestionOption> questionOption;

}
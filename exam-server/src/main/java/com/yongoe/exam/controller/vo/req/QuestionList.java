package com.yongoe.exam.controller.vo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 组卷题目
 *
 * @author yongoe
 * @since 2024/1/9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "组卷题目Req")
public class QuestionList {

    private Long bankId;

    private List<Long> questionIdList;
    private Boolean isAutoSelect;

    private Integer singleChoiceNum;
    private Integer multipleChoiceNum;
    private Integer trueFalseNum;
    private Integer shortAnswerNum;

    private Integer singleChoiceScore;
    private Integer multipleChoiceScore;
    private Integer trueFalseScore;
    private Integer shortAnswerScore;
}

package com.yongoe.exam.controller.vo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 组卷
 *
 * @author yongoe
 * @since 2024/1/9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "组卷Req")
public class MakePaper {

    private Long examId;

    private Integer openType;
    private List<Long> departmentIdList;
    private List<Long> userIdList;

    private List<QuestionList> questionList;

    private Integer passScore;
    private Integer totalScore;
}

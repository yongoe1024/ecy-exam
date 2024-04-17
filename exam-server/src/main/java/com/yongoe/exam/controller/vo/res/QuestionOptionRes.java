package com.yongoe.exam.controller.vo.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 题目答案
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "题目答案Res")
public class QuestionOptionRes {

    /**
     *
     */
    private Long id;
    /**
     * 题目id
     */
    private Long questionId;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否正确
     */
    private Boolean isTrue;
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
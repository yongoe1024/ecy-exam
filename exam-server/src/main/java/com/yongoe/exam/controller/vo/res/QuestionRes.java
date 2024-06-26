package com.yongoe.exam.controller.vo.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 题目管理
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "题目管理Res")
public class QuestionRes {

    /**
     *
     */
    private Long id;
    /**
     * 题库id
     */
    private Long questionBankId;
    /**
     * 题目
     */
    private String content;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 题目难度
     */
    private String level;
    /**
     * 正确答案
     */
    private String answer;
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
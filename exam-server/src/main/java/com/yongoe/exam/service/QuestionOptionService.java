package com.yongoe.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yongoe.exam.entity.QuestionOption;

/**
 * 题目答案
 *
 * @author yongoe
 * @since 2024/01/08
 */
public interface QuestionOptionService extends IService<QuestionOption> {

    Page<QuestionOption> getQuestionOptionByPage(Page<QuestionOption> page, QuestionOption questionOption);

}

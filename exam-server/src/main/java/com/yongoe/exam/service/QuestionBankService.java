package com.yongoe.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yongoe.exam.entity.QuestionBank;

/**
 * 题库管理
 *
 * @author yongoe
 * @since 2024/01/08
 */
public interface QuestionBankService extends IService<QuestionBank> {

    Page<QuestionBank> getQuestionBankByPage(Page<QuestionBank> page, QuestionBank questionBank);

}

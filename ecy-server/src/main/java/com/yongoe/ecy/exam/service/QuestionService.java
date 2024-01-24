package com.yongoe.ecy.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yongoe.ecy.exam.entity.Question;

/**
 * 题目管理
 *
 * @author yongoe
 * @since 2024/01/08
 */
public interface QuestionService extends IService<Question> {

    Page<Question> getQuestionByPage(Page<Question> page, Question question);

}

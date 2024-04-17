package com.yongoe.exam.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yongoe.exam.entity.QuestionOption;
import com.yongoe.exam.mapper.QuestionOptionMapper;
import com.yongoe.exam.service.QuestionOptionService;
import org.springframework.stereotype.Service;

/**
 * 题目答案
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Service
public class QuestionOptionServiceImpl extends ServiceImpl<QuestionOptionMapper, QuestionOption> implements QuestionOptionService {

    @Override
    public Page<QuestionOption> getQuestionOptionByPage(Page<QuestionOption> page, QuestionOption questionOption) {
        return baseMapper.getQuestionOptionByPage(page, questionOption);
    }

}

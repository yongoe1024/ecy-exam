package com.yongoe.ecy.exam.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yongoe.ecy.exam.entity.QuestionBank;
import com.yongoe.ecy.exam.mapper.QuestionBankMapper;
import com.yongoe.ecy.exam.service.QuestionBankService;
import org.springframework.stereotype.Service;

/**
 * 题库管理
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBank> implements QuestionBankService {

    @Override
    public Page<QuestionBank> getQuestionBankByPage(Page<QuestionBank> page, QuestionBank questionBank) {
        return baseMapper.getQuestionBankByPage(page, questionBank);
    }

}

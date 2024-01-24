package com.yongoe.ecy.exam.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yongoe.ecy.exam.entity.Question;
import com.yongoe.ecy.exam.mapper.QuestionMapper;
import com.yongoe.ecy.exam.service.QuestionService;
import org.springframework.stereotype.Service;

/**
 * 题目管理
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Override
    public Page<Question> getQuestionByPage(Page<Question> page, Question question) {
        return baseMapper.getQuestionByPage(page, question);
    }

}

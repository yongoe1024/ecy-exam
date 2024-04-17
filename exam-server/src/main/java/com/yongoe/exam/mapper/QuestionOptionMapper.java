package com.yongoe.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.exam.entity.QuestionOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 题目答案
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Mapper
public interface QuestionOptionMapper extends BaseMapper<QuestionOption> {

    Page<QuestionOption> getQuestionOptionByPage(Page<QuestionOption> page, @Param("questionOption") QuestionOption questionOption);

}

package com.yongoe.ecy.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.ecy.exam.entity.QuestionBank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 题库管理
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Mapper
public interface QuestionBankMapper extends BaseMapper<QuestionBank> {

    Page<QuestionBank> getQuestionBankByPage(Page<QuestionBank> page, @Param("questionBank") QuestionBank questionBank);

}

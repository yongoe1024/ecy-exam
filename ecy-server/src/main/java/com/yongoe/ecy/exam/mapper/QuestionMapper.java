package com.yongoe.ecy.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.ecy.exam.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目管理
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    Page<Question> getQuestionByPage(Page<Question> page, @Param("question") Question question);

    /**
     * 随机抽取试题
     */
    List<Long> randomQuestion(@Param("bankId") Long bankId, @Param("type") Integer type, @Param("num") Integer num);


}

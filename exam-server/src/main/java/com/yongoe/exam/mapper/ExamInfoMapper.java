package com.yongoe.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.exam.entity.ExamInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 考试信息
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Mapper
public interface ExamInfoMapper extends BaseMapper<ExamInfo> {

    Page<ExamInfo> getExamInfoByPage(Page<ExamInfo> page, @Param("examInfo") ExamInfo examInfo);

}

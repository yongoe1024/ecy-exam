package com.yongoe.ecy.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.ecy.exam.controller.vo.excel.UserPaperExcel;
import com.yongoe.ecy.exam.entity.UserPaper;
import com.yongoe.ecy.exam.entity.UserQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考生-试卷
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Mapper
public interface UserPaperMapper extends BaseMapper<UserPaper> {

    Page<UserPaper> getUserPaperByPage(Page<UserPaper> page, @Param("userPaper") UserPaper userPaper);

    /**
     * 导出考试成绩
     */
    List<UserPaperExcel> export(@Param("examId") Long examId, @Param("status") Integer status);


    /**
     * 获取试卷题目
     */
    List<UserQuestion> getQuestionByPaper(@Param("paperId") Long paperId, @Param("type") Integer type);
}

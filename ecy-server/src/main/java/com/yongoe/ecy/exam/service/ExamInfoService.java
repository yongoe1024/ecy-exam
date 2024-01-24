package com.yongoe.ecy.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yongoe.ecy.exam.entity.ExamInfo;

/**
 * 考试信息
 *
 * @author yongoe
 * @since 2024/01/08
 */
public interface ExamInfoService extends IService<ExamInfo> {

    Page<ExamInfo> getExamInfoByPage(Page<ExamInfo> page, ExamInfo examInfo);

}

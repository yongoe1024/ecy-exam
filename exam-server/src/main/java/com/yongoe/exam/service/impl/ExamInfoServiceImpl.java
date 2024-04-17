package com.yongoe.exam.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yongoe.exam.entity.ExamInfo;
import com.yongoe.exam.mapper.ExamInfoMapper;
import com.yongoe.exam.service.ExamInfoService;
import org.springframework.stereotype.Service;

/**
 * 考试信息
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Service
public class ExamInfoServiceImpl extends ServiceImpl<ExamInfoMapper, ExamInfo> implements ExamInfoService {

    @Override
    public Page<ExamInfo> getExamInfoByPage(Page<ExamInfo> page, ExamInfo examInfo) {
        return baseMapper.getExamInfoByPage(page, examInfo);
    }

}

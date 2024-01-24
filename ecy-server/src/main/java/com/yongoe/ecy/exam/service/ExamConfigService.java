package com.yongoe.ecy.exam.service;

import com.yongoe.ecy.exam.controller.vo.req.MakePaper;

/**
 * 考试配置
 *
 * @author yongoe
 * @since 2024/01/08
 */
public interface ExamConfigService {
    void makepaper(MakePaper makePaper);

    void removeAllInfo(Long examId);
}

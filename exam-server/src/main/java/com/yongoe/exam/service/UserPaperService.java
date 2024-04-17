package com.yongoe.exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yongoe.exam.entity.UserPaper;
import com.yongoe.exam.entity.UserQuestion;

import java.util.List;
import java.util.Map;

/**
 * 考生-试卷
 *
 * @author yongoe
 * @since 2024/01/08
 */
public interface UserPaperService extends IService<UserPaper> {

    Page<UserPaper> getUserPaperByPage(Page<UserPaper> page, UserPaper userPaper);

    Map<String, Object> getQuestion(Long paperId);

    void submitScore(List<UserQuestion> userQuestions);

    void autoScore(Long examId);
}

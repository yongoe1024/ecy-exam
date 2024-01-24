package com.yongoe.ecy.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yongoe.ecy.exam.convert.UserQuestionConvert;
import com.yongoe.ecy.exam.entity.ExamInfo;
import com.yongoe.ecy.exam.entity.QuestionOption;
import com.yongoe.ecy.exam.entity.UserPaper;
import com.yongoe.ecy.exam.entity.UserQuestion;
import com.yongoe.ecy.exam.mapper.UserPaperMapper;
import com.yongoe.ecy.exam.mapper.UserQuestionMapper;
import com.yongoe.ecy.exam.service.ExamInfoService;
import com.yongoe.ecy.exam.service.QuestionOptionService;
import com.yongoe.ecy.exam.service.UserPaperService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 考生-试卷
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Service
public class UserPaperServiceImpl extends ServiceImpl<UserPaperMapper, UserPaper> implements UserPaperService {
    @Resource
    private ExamInfoService examInfoService;
    @Resource
    private QuestionOptionService questionOptionService;
    @Resource
    private UserQuestionConvert userQuestionConvert;
    @Resource
    private UserQuestionMapper userQuestionMapper;

    @Override
    public Page<UserPaper> getUserPaperByPage(Page<UserPaper> page, UserPaper userPaper) {
        Page<UserPaper> userPaperByPage = baseMapper.getUserPaperByPage(page, userPaper);
        for (UserPaper record : userPaperByPage.getRecords()) {
            examInfoService.getById(record.getExamId());
            record.setExamInfo(examInfoService.getById(record.getExamId()));
        }
        return userPaperByPage;
    }

    @Override
    public Map<String, Object> getQuestion(Long paperId) {
        List<UserQuestion> singleChoice = baseMapper.getQuestionByPaper(paperId, 1);
        List<UserQuestion> multipleChoice = baseMapper.getQuestionByPaper(paperId, 2);
        List<UserQuestion> trueFalse = baseMapper.getQuestionByPaper(paperId, 3);
        List<UserQuestion> shortAnswer = baseMapper.getQuestionByPaper(paperId, 4);
        HashMap<String, Object> map = new HashMap<>();
        map.put("singleChoice", userQuestionConvert.entity2ResList(singleChoice));
        map.put("multipleChoice", userQuestionConvert.entity2ResList(multipleChoice));
        map.put("trueFalse", userQuestionConvert.entity2ResList(trueFalse));
        map.put("shortAnswer", userQuestionConvert.entity2ResList(shortAnswer));
        return map;
    }

    @Override
    public void submitScore(List<UserQuestion> userQuestions) {
        Long paperId = userQuestions.get(0).getPaperId();
        Long examId = userQuestions.get(0).getExamId();
        int sum = 0;
        for (UserQuestion userQuestion : userQuestions) {
            userQuestion.setIsTrue(Double.valueOf(userQuestion.getTotalScore()).compareTo(userQuestion.getScore()) == 0);
            userQuestionMapper.update(null, new LambdaUpdateWrapper<UserQuestion>()
                    .eq(UserQuestion::getId, userQuestion.getId())
                    .set(UserQuestion::getScore, userQuestion.getScore())
                    .set(UserQuestion::getIsTrue, userQuestion.getIsTrue()));
            sum += userQuestion.getScore();
        }
        ExamInfo byId = examInfoService.getById(examId);
        if (sum >= byId.getPassScore()) {
            this.update(new LambdaUpdateWrapper<UserPaper>()
                    .eq(UserPaper::getId, paperId)
                    .set(UserPaper::getStatus, 4)
                    .set(UserPaper::getScore, sum));
        } else {
            this.update(new LambdaUpdateWrapper<UserPaper>()
                    .eq(UserPaper::getId, paperId)
                    .set(UserPaper::getStatus, 5)
                    .set(UserPaper::getScore, sum));
        }
    }

    @Override
    public void autoScore(Long examId) {
        List<UserPaper> list = this.list(new LambdaQueryWrapper<UserPaper>().eq(UserPaper::getExamId, examId));
        for (UserPaper userPaper : list) {
            List<UserQuestion> userQuestions = baseMapper.getQuestionByPaper(userPaper.getId(), null);
            for (UserQuestion userQuestion : userQuestions) {
                Integer type = userQuestion.getType();
                if (type == 1 || type == 3) {
                    // 单选和判断
                    String answer = userQuestion.getAnswer();
                    List<String> answerId = this.getAnswerId(userQuestion.getQuestionId());
                    String aLong = answerId.size() > 0 ? answerId.get(0) : "";
                    if (aLong.equals(answer)) {
                        userQuestion.setScore(Double.valueOf(userQuestion.getTotalScore()));
                    } else {
                        userQuestion.setScore(0.0);
                    }
                } else if (type == 2) {
                    // 多选
                    String answer = userQuestion.getAnswer();
                    List<String> answerId = this.getAnswerId(userQuestion.getId());
                    String[] split = answer.split(",");
                    //判断两个数组是否完全一样
                    if (Arrays.equals(answerId.toArray(), split)) {
                        userQuestion.setScore(Double.valueOf(userQuestion.getTotalScore()));
                    } else {
                        userQuestion.setScore(0.0);
                    }
                } else if (type == 4) {
                    // 简答
                    userQuestion.setScore(0.0);
                }
            }
            this.submitScore(userQuestions);
        }
    }


    /**
     * 正确答案id
     */
    private List<String> getAnswerId(Long questionId) {
        List<QuestionOption> list = questionOptionService.list(new LambdaQueryWrapper<QuestionOption>()
                .eq(QuestionOption::getQuestionId, questionId)
                .eq(QuestionOption::getIsTrue, true));
        //转成string
        return list.stream().map(QuestionOption::getId).map(String::valueOf).collect(Collectors.toList());
    }

}

package com.yongoe.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yongoe.exam.controller.vo.req.MakePaper;
import com.yongoe.exam.controller.vo.req.QuestionList;
import com.yongoe.exam.entity.*;
import com.yongoe.exam.mapper.*;
import com.yongoe.exam.service.ExamConfigService;
import com.yongoe.exam.service.ExamInfoService;
import com.yongoe.exam.service.QuestionService;
import com.yongoe.exam.service.UserPaperService;
import com.yongoe.exam.system.entity.User;
import com.yongoe.exam.system.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考试配置
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Service
public class ExamConfigServiceImpl implements ExamConfigService {
    @Resource
    private ExamInfoService examInfoService;
    @Resource
    private ExamDeptMapper examDeptMapper;
    @Resource
    private ExamUserMapper examUserMapper;
    @Resource
    private QuestionService questionService;
    @Resource
    private ExamQuestionMapper examQuestionMapper;
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private UserPaperService userPaperService;
    @Resource
    private UserQuestionMapper userQuestionMapper;
    @Resource
    private UserService userService;

    @Override
    public void removeAllInfo(Long examId) {
        examInfoService.removeById(examId);
        examDeptMapper.delete(new LambdaQueryWrapper<ExamDept>().eq(ExamDept::getExamId, examId));
        examUserMapper.delete(new LambdaQueryWrapper<ExamUser>().eq(ExamUser::getExamId, examId));
        examQuestionMapper.delete(new LambdaQueryWrapper<ExamQuestion>().eq(ExamQuestion::getExamId, examId));
        userPaperService.remove(new LambdaQueryWrapper<UserPaper>().eq(UserPaper::getExamId, examId));
        userQuestionMapper.delete(new LambdaQueryWrapper<UserQuestion>().eq(UserQuestion::getExamId, examId));
    }

    @Override
    public void makepaper(MakePaper makePaper) {
        config(makePaper);
        auth(makePaper);
        question(makePaper);
        issue(makePaper.getExamId());
    }

    /**
     * 配置分数，第一个执行
     */
    private void config(MakePaper makePaper) {
        ExamInfo exam = examInfoService.getById(makePaper.getExamId());
        exam.setStatus(2);
        exam.setPassScore(makePaper.getPassScore());
        exam.setTotalScore(makePaper.getTotalScore());
        exam.setOpenType(makePaper.getOpenType());
        examInfoService.updateById(exam);
    }

    /**
     * 授权，第二个执行
     */
    private void auth(MakePaper makePaper) {
        Integer openType = makePaper.getOpenType();
        if (openType == 1) {
            //全部开放
        } else if (openType == 2) {
            //部门开放
            for (Long deptId : makePaper.getDepartmentIdList()) {
                ExamDept examDept = new ExamDept();
                examDept.setExamId(makePaper.getExamId());
                examDept.setDepartmentId(deptId);
                examDeptMapper.insert(examDept);
            }
        } else if (openType == 3) {
            //指定开放
            for (Long userId : makePaper.getUserIdList()) {
                ExamUser examUser = new ExamUser();
                examUser.setExamId(makePaper.getExamId());
                examUser.setUserId(userId);
                examUserMapper.insert(examUser);
            }
        } else {
            throw new RuntimeException("OpenType类型错误");
        }
    }

    /**
     * 生成试卷题目，第三个执行
     */
    private void question(MakePaper makePaper) {
        for (QuestionList questionList : makePaper.getQuestionList()) {
            if (questionList.getIsAutoSelect()) {
                //自动选题
                random(makePaper.getExamId(), questionList);
            } else {
                // 手动选题
                for (Long questionId : questionList.getQuestionIdList()) {
                    ExamQuestion examQuestion = new ExamQuestion();
                    Integer type = questionService.getById(questionId).getType();
                    //配置分数
                    if (type == 1) {
                        examQuestion.setScore(questionList.getSingleChoiceScore());
                    } else if (type == 2) {
                        examQuestion.setScore(questionList.getMultipleChoiceScore());
                    } else if (type == 3) {
                        examQuestion.setScore(questionList.getTrueFalseScore());
                    } else if (type == 4) {
                        examQuestion.setScore(questionList.getShortAnswerScore());
                    } else {
                        throw new RuntimeException("题目类型错误");
                    }
                    examQuestion.setExamId(makePaper.getExamId());
                    examQuestion.setQuestionId(questionId);
                    examQuestionMapper.insert(examQuestion);
                }
            }
        }
    }

    /**
     * 分发试卷，第四个执行
     */
    private void issue(Long examId) {
        //1:未组卷 2:未开始 3:进行中 4:已结束
        ExamInfo exam = examInfoService.getById(examId);
        exam.setStatus(2);
        examInfoService.updateById(exam);
        // 发题
        Integer openType = exam.getOpenType();
        List<ExamQuestion> examQuestionList = examQuestionMapper.selectList(new LambdaQueryWrapper<ExamQuestion>().eq(ExamQuestion::getExamId, examId));
        if (openType == 1) {
            //全部开放
            for (User user : userService.list()) {
                createPaper(examId, examQuestionList, user.getId());
            }
        } else if (openType == 2) {
            //部门开放
            for (Long departmentId : examDeptMapper.selectList(new LambdaQueryWrapper<ExamDept>().eq(ExamDept::getExamId, examId)).stream().map(ExamDept::getDepartmentId).toList()) {
                for (User user : userService.list(new LambdaQueryWrapper<User>().eq(User::getDepartmentId, departmentId))) {
                    createPaper(examId, examQuestionList, user.getId());
                }
            }
        } else if (openType == 3) {
            //指定开放
            for (Long userId : examUserMapper.selectList(new LambdaQueryWrapper<ExamUser>().eq(ExamUser::getExamId, examId)).stream().map(ExamUser::getUserId).toList()) {
                createPaper(examId, examQuestionList, userId);
            }
        } else {
            throw new RuntimeException("开放类型错误");
        }
    }

    /**
     * 试卷选题-随机抽题
     */
    private void random(Long exanId, QuestionList questionList) {
        long bankId = questionList.getBankId();
        List<Long> singleList = questionMapper.randomQuestion(bankId, 1, questionList.getSingleChoiceNum());
        List<Long> multipleList = questionMapper.randomQuestion(bankId, 2, questionList.getMultipleChoiceNum());
        List<Long> trueFalseList = questionMapper.randomQuestion(bankId, 3, questionList.getTrueFalseNum());
        List<Long> shortAnswerList = questionMapper.randomQuestion(bankId, 4, questionList.getShortAnswerNum());
        for (Long questionId : singleList) {
            ExamQuestion examQuestion = createExamQuestion(exanId, questionId, questionList.getSingleChoiceScore());
            examQuestionMapper.insert(examQuestion);
        }
        for (Long questionId : multipleList) {
            ExamQuestion examQuestion = createExamQuestion(exanId, questionId, questionList.getMultipleChoiceScore());
            examQuestionMapper.insert(examQuestion);
        }
        for (Long questionId : trueFalseList) {
            ExamQuestion examQuestion = createExamQuestion(exanId, questionId, questionList.getTrueFalseScore());
            examQuestionMapper.insert(examQuestion);
        }
        for (Long questionId : shortAnswerList) {
            ExamQuestion examQuestion = createExamQuestion(exanId, questionId, questionList.getShortAnswerScore());
            examQuestionMapper.insert(examQuestion);
        }
    }

    private ExamQuestion createExamQuestion(Long examId, Long questionId, Integer score) {
        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setExamId(examId);
        examQuestion.setQuestionId(questionId);
        examQuestion.setScore(score);
        return examQuestion;
    }

    /**
     * 生成试卷
     */
    private void createPaper(Long examId, List<ExamQuestion> examQuestionList, Long userId) {
        UserPaper userPaper = new UserPaper();
        userPaper.setExamId(examId);
        userPaper.setUserId(userId);
        userPaper.setStatus(1); // 未参加状态
        userPaperService.save(userPaper);
        for (ExamQuestion examQuestion : examQuestionList) {
            UserQuestion userQuestion = new UserQuestion();
            userQuestion.setExamId(examId);
            userQuestion.setPaperId(userPaper.getId());
            userQuestion.setQuestionId(examQuestion.getQuestionId());
            userQuestion.setUserId(userId);
            userQuestionMapper.insert(userQuestion);
        }
    }

}

package com.yongoe.ecy.exam.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yongoe.ecy.exam.entity.ExamInfo;
import com.yongoe.ecy.exam.entity.UserPaper;
import com.yongoe.ecy.exam.service.ExamInfoService;
import com.yongoe.ecy.exam.service.UserPaperService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 考试结束监控
 *
 * @author yongoe
 * @since 2023/12/5
 */
@Component
@EnableScheduling
public class ExamTime {
    @Resource
    private ExamInfoService examInfoService;
    @Resource
    private UserPaperService userPaperService;

    /**
     * 每五秒钟检测一次是否有考试结束
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void checkExamInfoTime() {
        List<ExamInfo> ExamInfos = examInfoService.list(new LambdaQueryWrapper<ExamInfo>().eq(ExamInfo::getStatus, 3));
        for (ExamInfo examInfo : ExamInfos) {
            LocalDateTime endTime = examInfo.getEndTime();
            if (endTime.isBefore(LocalDateTime.now())) {
                examInfo.setStatus(4);
                examInfoService.updateById(examInfo);
                for (UserPaper userPaper : userPaperService.list(new LambdaQueryWrapper<UserPaper>().eq(UserPaper::getExamId, examInfo.getId()))) {
                    //只收已经答题的卷子
                    if (userPaper.getStatus() == 2 || userPaper.getStatus() == 1) {
                        handPaperByUserId(userPaper.getId());
                    }
                }
            }
        }
    }

    /**
     * 每五秒钟检测一次是否有试卷结束
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void checkPaperTime() {
        List<UserPaper> list = userPaperService.list(new LambdaQueryWrapper<UserPaper>()
                .eq(UserPaper::getStatus, 2));
        for (UserPaper userPaper : list) {
            ExamInfo examInfo = examInfoService.getById(userPaper.getExamId());
            Integer duration = examInfo.getDuration();
            LocalDateTime enterTime = userPaper.getEnterTime();
            LocalDateTime sum = enterTime.plusMinutes(duration);
            if (sum.isBefore(LocalDateTime.now())) {
                handPaperByUserId(userPaper.getId());
            }
        }
    }

    /**
     * 每五秒钟检测一次是否有考试开始
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void checkExamInfoStart() {
        List<ExamInfo> examInfos = examInfoService.list(new LambdaQueryWrapper<ExamInfo>().eq(ExamInfo::getStatus, 2));
        for (ExamInfo examInfo : examInfos) {
            LocalDateTime startTime = examInfo.getStartTime();
            if (startTime.isBefore(LocalDateTime.now())) {
                examInfo.setStatus(3);
                examInfoService.updateById(examInfo);
            }
        }
    }

    private void handPaperByUserId(Long paperId) {
        UserPaper userPaper = userPaperService.getById(paperId);
        userPaper.setStatus(3);
        userPaper.setFinishTime(LocalDateTime.now());
        userPaperService.updateById(userPaper);
    }
}

package com.yongoe.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.exam.config.aop.WebLog;
import com.yongoe.exam.controller.vo.excel.UserPaperExcel;
import com.yongoe.exam.controller.vo.req.UserPaperReq;
import com.yongoe.exam.controller.vo.res.UserPaperRes;
import com.yongoe.exam.convert.UserPaperConvert;
import com.yongoe.exam.entity.ExamInfo;
import com.yongoe.exam.entity.UserPaper;
import com.yongoe.exam.entity.UserQuestion;
import com.yongoe.exam.mapper.UserPaperMapper;
import com.yongoe.exam.mapper.UserQuestionMapper;
import com.yongoe.exam.service.ExamInfoService;
import com.yongoe.exam.service.UserPaperService;
import com.yongoe.exam.utils.ExcelUtils;
import com.yongoe.exam.utils.PageUtils;
import com.yongoe.exam.utils.R;
import com.yongoe.exam.utils.UserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 考生-试卷
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Tag(name = "考生-试卷")
@RestController
@RequestMapping("/exam/userpaper")
public class UserPaperController {
    @Resource
    private UserPaperService userPaperService;
    @Resource
    private UserPaperMapper userPaperMapper;
    @Resource
    private UserPaperConvert userPaperConvert;
    @Resource
    private ExamInfoService examInfoService;
    @Resource
    private UserQuestionMapper userQuestionMapper;

    @WebLog
    @Operation(summary = "自动判分")
    @PostMapping("/autoScore")
    public R autoScore(Long examId) {
        userPaperService.autoScore(examId);
        return R.success("自动判分完成");
    }

    @WebLog
    @Operation(summary = "交卷")
    @PostMapping("/handPaper")
    public R handPaper(Long examId) {
        Long userId = UserUtils.getUserId();
        UserPaper userPaper = userPaperService.getOne(new LambdaQueryWrapper<UserPaper>()
                .eq(UserPaper::getUserId, userId)
                .eq(UserPaper::getExamId, examId));
        userPaper.setStatus(3);
        userPaper.setFinishTime(LocalDateTime.now());
        userPaperService.updateById(userPaper);
        return R.success("成功交卷");
    }

    @Operation(summary = "提交答案")
    @PostMapping("/saveAnswer")
    public R saveAnswer(@RequestBody Map<String, Object> map) {
        if (StringUtils.isEmpty((String) map.get("paperId")) || StringUtils.isEmpty((String) map.get("id")) || StringUtils.isEmpty((String) map.get("answer"))) {
            return R.error("参数错误");
        }
        Long paperId = Long.valueOf((String) map.get("paperId"));
        UserPaper paper = userPaperService.getById(paperId);
        if (paper.getStatus() != 2) {
            return R.error("考试已经结束");
        }
        Long id = Long.valueOf((String) map.get("id"));
        String answer = (String) map.get("answer");
        userQuestionMapper.update(null, new LambdaUpdateWrapper<UserQuestion>()
                .eq(UserQuestion::getId, id)
                .set(UserQuestion::getAnswer, answer));
        return R.success();
    }

    @Operation(summary = "考试页面获取信息")
    @PostMapping("/exam")
    public R exam(Long examId) {
        if (examId == null) {
            return R.error("非法进入");
        }
        ExamInfo byId = examInfoService.getById(examId);
        if (byId == null) {
            return R.error("考试不存在");
        }
        UserPaper paper = userPaperService.getOne(new LambdaQueryWrapper<UserPaper>()
                .eq(UserPaper::getExamId, examId)
                .eq(UserPaper::getUserId, UserUtils.getUserId()));
        if (paper == null) {
            return R.error("无权访问");
        }
        if (paper.getStatus() == 1) {
            paper.setStatus(2);
            paper.setEnterTime(LocalDateTime.now());
            userPaperService.updateById(paper);
        }
        // 组装其他信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("paper", paper);
        map.put("exam", byId);
        return R.success().put(map);
    }

    @WebLog
    @Operation(summary = "提交批阅分数")
    @PostMapping("/submitScore")
    public R submitScore(@RequestBody List<UserQuestion> userQuestions) {
        userPaperService.submitScore(userQuestions);
        return R.success("批阅完成");
    }

    @Operation(summary = "批阅获取考生")
    @PostMapping("/checkPaper")
    public R checkPaper(Long current, Long size, @RequestBody UserPaperReq req) {
        UserPaper entity = userPaperConvert.req2Entity(req);
        Page<UserPaper> page = userPaperService.getUserPaperByPage(Page.of(current, size), entity);
        Page<UserPaperRes> resPage = userPaperConvert.entity2ResPage(page);
        return R.success().put(new PageUtils(resPage));
    }

    @Operation(summary = "获取考试题目")
    @PostMapping("/getQuestion")
    public R getQuestion(Long paperId) {
        Map<String, Object> map = userPaperService.getQuestion(paperId);
        return R.success().put(map);
    }

    @Operation(summary = "我的试卷")
    @PostMapping("/page")
    public R page(Long current, Long size, @RequestBody UserPaperReq req) {
        UserPaper entity = userPaperConvert.req2Entity(req);
        entity.setUserId(UserUtils.getUserId());
        Page<UserPaper> page = userPaperService.getUserPaperByPage(Page.of(current, size), entity);
        Page<UserPaperRes> resPage = userPaperConvert.entity2ResPage(page);
        return R.success().put(new PageUtils(resPage));
    }

    @Operation(summary = "导出数据")
    @GetMapping("/export")
    public void export(UserPaperReq req, HttpServletResponse response) {
        List<UserPaperExcel> export = userPaperMapper.export(req.getExamId(), req.getStatus());
        ExcelUtils.export(response, export, UserPaperExcel.class);
    }
}
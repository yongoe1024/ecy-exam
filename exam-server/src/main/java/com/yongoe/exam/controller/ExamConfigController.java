package com.yongoe.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.exam.basic.controller.vo.res.DepartmentRes;
import com.yongoe.exam.basic.convert.DepartmentConvert;
import com.yongoe.exam.basic.entity.Department;
import com.yongoe.exam.basic.service.DepartmentService;
import com.yongoe.exam.config.aop.WebLog;
import com.yongoe.exam.controller.vo.req.MakePaper;
import com.yongoe.exam.controller.vo.res.QuestionBankRes;
import com.yongoe.exam.convert.QuestionBankConvert;
import com.yongoe.exam.entity.ExamInfo;
import com.yongoe.exam.entity.Question;
import com.yongoe.exam.entity.QuestionBank;
import com.yongoe.exam.service.ExamConfigService;
import com.yongoe.exam.service.ExamInfoService;
import com.yongoe.exam.service.QuestionBankService;
import com.yongoe.exam.service.QuestionService;
import com.yongoe.exam.system.controller.vo.req.UserReq;
import com.yongoe.exam.system.controller.vo.res.UserRes;
import com.yongoe.exam.system.convert.UserConvert;
import com.yongoe.exam.system.entity.User;
import com.yongoe.exam.system.service.UserService;
import com.yongoe.exam.utils.PageUtils;
import com.yongoe.exam.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 考试配置
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Tag(name = "考试配置")
@RestController
@RequestMapping("/exam/config")
public class ExamConfigController {
    @Resource
    private ExamConfigService examConfigService;
    @Resource
    private ExamInfoService examInfoService;
    @Resource
    private QuestionService questionService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private UserService userService;
    @Resource
    private DepartmentConvert departmentConvert;
    @Resource
    private UserConvert convert;
    @Resource
    private QuestionBankService questionBankService;
    @Resource
    private QuestionBankConvert questionBankConvert;

    @WebLog
    @Operation(summary = "组卷")
    @PostMapping("/makepaper")
    public R makepaper(@RequestBody MakePaper makePaper) {
        ExamInfo byId = examInfoService.getById(makePaper.getExamId());
        Integer status = byId.getStatus();
        if (status != 1) {
            return R.error("已发放试卷，禁止组卷");
        }
        examConfigService.makepaper(makePaper);
        return R.success("组卷成功，已发放试题至考生");
    }

    @Operation(summary = "查询题库信息")
    @PostMapping("/bankInfo")
    public R bankInfo(Long bankId) {
        long singleChoiceNum = questionService.count(new LambdaQueryWrapper<Question>().eq(Question::getQuestionBankId, bankId).eq(Question::getType, 1));
        long multipleChoiceNum = questionService.count(new LambdaQueryWrapper<Question>().eq(Question::getQuestionBankId, bankId).eq(Question::getType, 2));
        long trueFalseNum = questionService.count(new LambdaQueryWrapper<Question>().eq(Question::getQuestionBankId, bankId).eq(Question::getType, 3));
        long shortAnswerNum = questionService.count(new LambdaQueryWrapper<Question>().eq(Question::getQuestionBankId, bankId).eq(Question::getType, 4));
        Map<String, Integer> map = new HashMap<>();
        map.put("singleChoiceNum", (int) singleChoiceNum);
        map.put("multipleChoiceNum", (int) multipleChoiceNum);
        map.put("trueFalseNum", (int) trueFalseNum);
        map.put("shortAnswerNum", (int) shortAnswerNum);
        return R.success().put(map);
    }

    @Operation(summary = "查询题库")
    @PostMapping("/bank")
    public R bank() {
        List<QuestionBank> list = questionBankService.list();
        List<QuestionBankRes> questionBankRes = questionBankConvert.entity2ResList(list);
        return R.success().put(questionBankRes);
    }

    @Operation(summary = "查询用户")
    @PostMapping("/user")
    public R user(Long current, Long size, @RequestBody UserReq req) {
        User entity = convert.req2Entity(req);
        Page<User> page = userService.getUserByPage(Page.of(current, size), entity);
        Page<UserRes> resPage = convert.entity2ResPage(page);
        return R.success().put(new PageUtils(resPage));
    }

    @Operation(summary = "查询部门")
    @PostMapping("/department")
    public R department() {
        List<Department> list = departmentService.getMenuByTree(new LambdaQueryWrapper<Department>()
                .eq(Department::getEnabled, true));
        List<DepartmentRes> resList = departmentConvert.entity2ResList(list);
        return R.success().put(resList);
    }

}
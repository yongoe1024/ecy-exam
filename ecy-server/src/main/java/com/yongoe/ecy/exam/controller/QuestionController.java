package com.yongoe.ecy.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.ecy.config.aop.WebLog;
import com.yongoe.ecy.exam.controller.vo.req.QuestionReq;
import com.yongoe.ecy.exam.controller.vo.res.QuestionBankRes;
import com.yongoe.ecy.exam.controller.vo.res.QuestionRes;
import com.yongoe.ecy.exam.convert.QuestionBankConvert;
import com.yongoe.ecy.exam.convert.QuestionConvert;
import com.yongoe.ecy.exam.entity.Question;
import com.yongoe.ecy.exam.entity.QuestionBank;
import com.yongoe.ecy.exam.entity.QuestionOption;
import com.yongoe.ecy.exam.service.QuestionBankService;
import com.yongoe.ecy.exam.service.QuestionOptionService;
import com.yongoe.ecy.exam.service.QuestionService;
import com.yongoe.ecy.utils.PageUtils;
import com.yongoe.ecy.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题目管理
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Tag(name = "题目管理")
@RestController
@RequestMapping("/exam/question")
public class QuestionController {
    @Resource
    private QuestionService questionService;
    @Resource
    private QuestionConvert questionConvert;
    @Resource
    private QuestionBankService questionBankService;
    @Resource
    private QuestionBankConvert questionBankConvert;
    @Resource
    private QuestionOptionService questionOptionService;

    @Operation(summary = "查询题库列表")
    @PostMapping("/banklist")
    public R banklist() {
        List<QuestionBank> list = questionBankService.list();
        List<QuestionBankRes> questionBankRes = questionBankConvert.entity2ResList(list);
        return R.success().put(questionBankRes);
    }

    @Operation(summary = "查询分页数据")
    @PostMapping("/page")
    public R page(Long current, Long size, @RequestBody QuestionReq req) {
        Question entity = questionConvert.req2Entity(req);
        Page<Question> page = questionService.getQuestionByPage(Page.of(current, size), entity);
        Page<QuestionRes> resPage = questionConvert.entity2ResPage(page);
        return R.success().put(new PageUtils(resPage));
    }

    @WebLog
    @Operation(summary = "添加数据")
    @PostMapping("/add")
    public R add(@RequestBody QuestionReq req) {
        Question question = questionConvert.req2Entity(req);
        questionService.save(question);
        if (question.getType() == 3) {
            questionOptionService.save(new QuestionOption(null, question.getId(), "对", false, null, null, null, null));
            questionOptionService.save(new QuestionOption(null, question.getId(), "错", false, null, null, null, null));
        }
        return R.success("添加成功");
    }

    @WebLog
    @Operation(summary = "修改数据")
    @PostMapping("/update")
    public R update(@RequestBody QuestionReq req) {
        Question question = questionConvert.req2Entity(req);
        questionService.updateById(question);
        return R.success("修改成功");
    }

    @WebLog
    @Operation(summary = "删除数据")
    @PostMapping("/delete/{ids}")
    public R delete(@PathVariable List<Long> ids) {
        questionService.removeByIds(ids);
        ids.forEach(id -> {
            questionOptionService.remove(new LambdaQueryWrapper<QuestionOption>()
                    .eq(QuestionOption::getQuestionId, id));
        });
        return R.success("删除成功");
    }
}
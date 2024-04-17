package com.yongoe.exam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.exam.config.aop.WebLog;
import com.yongoe.exam.controller.vo.req.QuestionOptionReq;
import com.yongoe.exam.controller.vo.res.QuestionOptionRes;
import com.yongoe.exam.convert.QuestionOptionConvert;
import com.yongoe.exam.entity.QuestionOption;
import com.yongoe.exam.service.QuestionOptionService;
import com.yongoe.exam.utils.PageUtils;
import com.yongoe.exam.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题目答案
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Tag(name = "题目答案")
@RestController
@RequestMapping("/exam/questionoption")
public class QuestionOptionController {
    @Resource
    private QuestionOptionService questionOptionService;
    @Resource
    private QuestionOptionConvert questionOptionConvert;

    @Operation(summary = "查询分页数据")
    @PostMapping("/page")
    public R page(Long current, Long size, @RequestBody QuestionOptionReq req) {
        QuestionOption entity = questionOptionConvert.req2Entity(req);
        Page<QuestionOption> page = questionOptionService.getQuestionOptionByPage(Page.of(current, size), entity);
        Page<QuestionOptionRes> resPage = questionOptionConvert.entity2ResPage(page);
        return R.success().put(new PageUtils(resPage));
    }

    @WebLog
    @Operation(summary = "添加数据")
    @PostMapping("/add")
    public R add(@RequestBody QuestionOptionReq req) {
        QuestionOption questionOption = questionOptionConvert.req2Entity(req);
        questionOptionService.save(questionOption);
        return R.success("添加成功");
    }

    @WebLog
    @Operation(summary = "修改数据")
    @PostMapping("/update")
    public R update(@RequestBody QuestionOptionReq req) {
        QuestionOption questionOption = questionOptionConvert.req2Entity(req);
        questionOptionService.updateById(questionOption);
        return R.success("修改成功");
    }

    @WebLog
    @Operation(summary = "删除数据")
    @PostMapping("/delete/{ids}")
    public R delete(@PathVariable List<Long> ids) {
        questionOptionService.removeByIds(ids);
        return R.success("删除成功");
    }
}
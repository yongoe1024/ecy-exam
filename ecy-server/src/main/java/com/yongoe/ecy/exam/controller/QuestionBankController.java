package com.yongoe.ecy.exam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.ecy.config.aop.WebLog;
import com.yongoe.ecy.exam.controller.vo.req.QuestionBankReq;
import com.yongoe.ecy.exam.controller.vo.res.QuestionBankRes;
import com.yongoe.ecy.exam.convert.QuestionBankConvert;
import com.yongoe.ecy.exam.entity.QuestionBank;
import com.yongoe.ecy.exam.service.QuestionBankService;
import com.yongoe.ecy.utils.PageUtils;
import com.yongoe.ecy.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题库管理
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Tag(name = "题库管理")
@RestController
@RequestMapping("/exam/questionbank")
public class QuestionBankController {
    @Resource
    private QuestionBankService questionBankService;
    @Resource
    private QuestionBankConvert questionBankConvert;

    @Operation(summary = "查询题库")
    @PostMapping("/bank")
    public R list() {
        List<QuestionBank> list = questionBankService.list();
        List<QuestionBankRes> questionBankRes = questionBankConvert.entity2ResList(list);
        return R.success().put(questionBankRes);
    }

    @Operation(summary = "查询分页数据")
    @PostMapping("/page")
    public R page(Long current, Long size, @RequestBody QuestionBankReq req) {
        QuestionBank entity = questionBankConvert.req2Entity(req);
        Page<QuestionBank> page = questionBankService.getQuestionBankByPage(Page.of(current, size), entity);
        Page<QuestionBankRes> resPage = questionBankConvert.entity2ResPage(page);
        return R.success().put(new PageUtils(resPage));
    }

    @WebLog
    @Operation(summary = "添加数据")
    @PostMapping("/add")
    public R add(@RequestBody QuestionBankReq req) {
        QuestionBank questionBank = questionBankConvert.req2Entity(req);
        questionBankService.save(questionBank);
        return R.success("添加成功");
    }

    @WebLog
    @Operation(summary = "修改数据")
    @PostMapping("/update")
    public R update(@RequestBody QuestionBankReq req) {
        QuestionBank questionBank = questionBankConvert.req2Entity(req);
        questionBankService.updateById(questionBank);
        return R.success("修改成功");
    }

    @WebLog
    @Operation(summary = "删除数据")
    @PostMapping("/delete/{ids}")
    public R delete(@PathVariable List<Long> ids) {
        questionBankService.removeByIds(ids);
        return R.success("删除成功");
    }
}
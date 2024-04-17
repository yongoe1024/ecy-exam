package com.yongoe.exam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.exam.config.aop.WebLog;
import com.yongoe.exam.controller.vo.req.ExamInfoReq;
import com.yongoe.exam.controller.vo.res.ExamInfoRes;
import com.yongoe.exam.convert.ExamInfoConvert;
import com.yongoe.exam.entity.ExamInfo;
import com.yongoe.exam.service.ExamConfigService;
import com.yongoe.exam.service.ExamInfoService;
import com.yongoe.exam.utils.PageUtils;
import com.yongoe.exam.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 考试信息
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Tag(name = "考试信息")
@RestController
@RequestMapping("/exam/examinfo")
public class ExamInfoController {
    @Resource
    private ExamInfoService examInfoService;
    @Resource
    private ExamInfoConvert examInfoConvert;
    @Resource
    private ExamConfigService examConfigService;

    @Operation(summary = "查询分页数据")
    @PostMapping("/page")
    public R page(Long current, Long size, @RequestBody ExamInfoReq req) {
        ExamInfo entity = examInfoConvert.req2Entity(req);
        Page<ExamInfo> page = examInfoService.getExamInfoByPage(Page.of(current, size), entity);
        Page<ExamInfoRes> resPage = examInfoConvert.entity2ResPage(page);
        return R.success().put(new PageUtils(resPage));
    }

    @WebLog
    @Operation(summary = "添加数据")
    @PostMapping("/add")
    public R add(@RequestBody ExamInfoReq req) {
        ExamInfo examInfo = examInfoConvert.req2Entity(req);
        examInfoService.save(examInfo);
        return R.success("添加成功");
    }

    @WebLog
    @Operation(summary = "修改数据")
    @PostMapping("/update")
    public R update(@RequestBody ExamInfoReq req) {
        ExamInfo byId = examInfoService.getById(req.getId());
        Integer status = byId.getStatus();
        if (status != 1 && status != 2) {
            return R.error("已开始考试，无法修改");
        }
        ExamInfo examInfo = examInfoConvert.req2Entity(req);
        examInfoService.updateById(examInfo);
        return R.success("修改成功");
    }

    @WebLog
    @Operation(summary = "删除数据")
    @PostMapping("/delete/{ids}")
    public R delete(@PathVariable List<Long> ids) {
        for (Long id : ids) {
            examConfigService.removeAllInfo(id);
        }
        return R.success("删除成功");
    }

}
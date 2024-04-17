package com.yongoe.exam.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.exam.system.controller.vo.req.LogReq;
import com.yongoe.exam.system.controller.vo.res.LogRes;
import com.yongoe.exam.system.convert.LogConvert;
import com.yongoe.exam.system.entity.Log;
import com.yongoe.exam.system.service.LogService;
import com.yongoe.exam.utils.PageUtils;
import com.yongoe.exam.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统日志
 *
 * @author yongoe
 * @since 2023/09/19
 */
@Tag(name = "系统日志")
@RestController
@RequestMapping("/system/log")
public class LogController {
    @Resource
    private LogService logService;
    @Resource
    private LogConvert logConvert;

    @Operation(summary = "查询分页数据")
    @PostMapping("/page")
    public R page(@RequestBody LogReq req) {
        Log log = logConvert.req2Entity(req);
        Page<Log> page = logService.getLogByPage(Page.of(req.getCurrent(), req.getSize()), log);
        Page<LogRes> resPage = logConvert.entity2ResPage(page);
        return R.success().put(new PageUtils(resPage));
    }

    @Operation(summary = "删除数据")
    @PostMapping("/delete")
    public R delete() {
        logService.remove(null);
        return R.success("删除成功");
    }

}
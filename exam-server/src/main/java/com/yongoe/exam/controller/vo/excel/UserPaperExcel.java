package com.yongoe.exam.controller.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 考生-试卷
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "考生-试卷Excel")
public class UserPaperExcel {

    @Excel(name = "考试名称", width = 20)
    private String examName;

    @Excel(name = "部门", width = 20)
    private String deptName;

    @Excel(name = "用户", width = 20)
    private String name;

    @Excel(name = "考试状态", width = 20, replace = {"未参加_1", "考试中_2", "未通过_3", "通过_4", "已交卷_5"})
    private String status;

    @Excel(name = "进入时间", width = 20)
    private LocalDateTime enterTime;

    @Excel(name = "交卷时间", width = 20)
    private LocalDateTime finishTime;

    @Excel(name = "得分", width = 20)
    private Double score;

}
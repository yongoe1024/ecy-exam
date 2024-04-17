package com.yongoe.exam.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yongoe.exam.basic.entity.Department;

import java.util.List;

/**
 * 部门
 *
 * @author yongoe
 * @since 2023/1/1
 */
public interface DepartmentService extends IService<Department> {

    List<Department> getMenuByTree(LambdaQueryWrapper<Department> wrapper);
}

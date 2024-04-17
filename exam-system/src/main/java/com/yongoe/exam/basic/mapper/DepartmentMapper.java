package com.yongoe.exam.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yongoe.exam.basic.entity.Department;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门
 *
 * @author yongoe
 * @since 2023/1/1
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {


}

package com.yongoe.exam.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.exam.basic.entity.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 职位
 *
 * @author yongoe
 * @since 2023/1/1
 */
@Mapper
public interface PositionMapper extends BaseMapper<Position> {

    Page<Position> getPositionByPage(Page<Position> page, @Param("position") Position position);

}

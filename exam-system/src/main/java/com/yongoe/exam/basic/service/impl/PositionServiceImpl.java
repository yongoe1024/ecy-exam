package com.yongoe.exam.basic.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yongoe.exam.basic.entity.Position;
import com.yongoe.exam.basic.mapper.PositionMapper;
import com.yongoe.exam.basic.service.PositionService;
import org.springframework.stereotype.Service;

/**
 * 职位
 *
 * @author yongoe
 * @since 2023/1/1
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {

    @Override
    public Page<Position> getPositionByPage(Page<Position> page, Position position) {
        return baseMapper.getPositionByPage(page, position);
    }

}

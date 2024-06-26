package com.yongoe.exam.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yongoe.exam.basic.entity.Dict;
import com.yongoe.exam.basic.entity.DictData;
import com.yongoe.exam.basic.mapper.DictMapper;
import com.yongoe.exam.basic.service.DictDataService;
import com.yongoe.exam.basic.service.DictService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据字典
 *
 * @author yongoe
 * @since 2023/1/1
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Resource
    private DictDataService dictDataService;

    @Override
    public Page<Dict> getDictByPage(Page<Dict> page, Dict dict) {
        return baseMapper.getDictByPage(page, dict);
    }

    @Override
    public List<DictData> getDict(String name) {
        Dict dict = this.getOne(new LambdaQueryWrapper<Dict>().eq(Dict::getName, name));
        List<DictData> dictData = dictDataService.list(new LambdaQueryWrapper<DictData>()
                .eq(DictData::getDictId, dict.getId())
                .orderByDesc(DictData::getSort));
        return dictData;
    }

}

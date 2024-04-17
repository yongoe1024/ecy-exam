package com.yongoe.exam.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yongoe.exam.basic.entity.DictData;

/**
 * 数据字典-数据
 *
 * @author yongoe
 * @since 2023/1/1
 */
public interface DictDataService extends IService<DictData> {

    Page<DictData> getDictDataByPage(Page<DictData> page, DictData dictData);

}

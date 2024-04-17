package com.yongoe.exam.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.exam.basic.entity.DictData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 数据字典-数据
 *
 * @author yongoe
 * @since 2023/1/1
 */
@Mapper
public interface DictDataMapper extends BaseMapper<DictData> {

    Page<DictData> getDictDataByPage(Page<DictData> page, @Param("dictData") DictData dictData);

}

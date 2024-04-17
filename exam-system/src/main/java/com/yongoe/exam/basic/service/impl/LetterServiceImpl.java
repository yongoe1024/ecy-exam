package com.yongoe.exam.basic.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yongoe.exam.basic.entity.Letter;
import com.yongoe.exam.basic.mapper.LetterMapper;
import com.yongoe.exam.basic.service.LetterService;
import org.springframework.stereotype.Service;

/**
 * 信件
 *
 * @author yongoe
 * @since 2023/1/1
 */
@Service
public class LetterServiceImpl extends ServiceImpl<LetterMapper, Letter> implements LetterService {

    @Override
    public Page<Letter> getLetterByPage(Page<Letter> page, Letter letter) {
        return baseMapper.getLetterByPage(page, letter);
    }

}

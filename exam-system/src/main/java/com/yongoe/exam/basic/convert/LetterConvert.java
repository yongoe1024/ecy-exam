package com.yongoe.exam.basic.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.exam.basic.controller.vo.req.LetterReq;
import com.yongoe.exam.basic.controller.vo.res.LetterRes;
import com.yongoe.exam.basic.entity.Letter;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 信件 convert
 *
 * @author yongoe
 * @since 2023/1/1
 */
@Mapper(componentModel = "spring")
public interface LetterConvert {
    /**
     * entity to vo
     */
    LetterReq entity2Req(Letter entity);

    LetterRes entity2Res(Letter entity);

    /**
     * entity to vo List
     */
    List<LetterReq> entity2ReqList(List<Letter> list);

    List<LetterRes> entity2ResList(List<Letter> list);


    /**
     * entity to vo Page
     */
    Page<LetterRes> entity2ResPage(Page<Letter> page);


    /**
     * vo 转 entity
     */
    Letter req2Entity(LetterReq reqVo);


}
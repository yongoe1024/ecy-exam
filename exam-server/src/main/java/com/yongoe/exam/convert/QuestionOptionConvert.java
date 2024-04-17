package com.yongoe.exam.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.exam.controller.vo.req.QuestionOptionReq;
import com.yongoe.exam.controller.vo.res.QuestionOptionRes;
import com.yongoe.exam.entity.QuestionOption;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 题目答案 convert
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Mapper(componentModel = "spring")
public interface QuestionOptionConvert {
    /**
     * entity to vo
     */
    QuestionOptionReq entity2Req(QuestionOption entity);

    QuestionOptionRes entity2Res(QuestionOption entity);

    /**
     * entity to vo List
     */
    List<QuestionOptionReq> entity2ReqList(List<QuestionOption> list);

    List<QuestionOptionRes> entity2ResList(List<QuestionOption> list);

    /**
     * entity to vo Page
     */
    Page<QuestionOptionRes> entity2ResPage(Page<QuestionOption> page);

    /**
     * vo to entity
     */
    QuestionOption req2Entity(QuestionOptionReq req);


}
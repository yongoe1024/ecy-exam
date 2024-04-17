package com.yongoe.exam.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.exam.controller.vo.req.QuestionBankReq;
import com.yongoe.exam.controller.vo.res.QuestionBankRes;
import com.yongoe.exam.entity.QuestionBank;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 题库管理 convert
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Mapper(componentModel = "spring")
public interface QuestionBankConvert {
    /**
     * entity to vo
     */
    QuestionBankReq entity2Req(QuestionBank entity);

    QuestionBankRes entity2Res(QuestionBank entity);

    /**
     * entity to vo List
     */
    List<QuestionBankReq> entity2ReqList(List<QuestionBank> list);

    List<QuestionBankRes> entity2ResList(List<QuestionBank> list);

    /**
     * entity to vo Page
     */
    Page<QuestionBankRes> entity2ResPage(Page<QuestionBank> page);

    /**
     * vo to entity
     */
    QuestionBank req2Entity(QuestionBankReq req);

}
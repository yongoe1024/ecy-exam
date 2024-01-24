package com.yongoe.ecy.exam.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.ecy.exam.controller.vo.req.QuestionReq;
import com.yongoe.ecy.exam.controller.vo.res.QuestionRes;
import com.yongoe.ecy.exam.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 题目管理 convert
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Mapper(componentModel = "spring")
public interface QuestionConvert {
    /**
     * entity to vo
     */
    QuestionReq entity2Req(Question entity);

    QuestionRes entity2Res(Question entity);

    /**
     * entity to vo List
     */
    List<QuestionReq> entity2ReqList(List<Question> list);

    List<QuestionRes> entity2ResList(List<Question> list);

    /**
     * entity to vo Page
     */
    Page<QuestionRes> entity2ResPage(Page<Question> page);

    /**
     * vo to entity
     */
    Question req2Entity(QuestionReq req);

}
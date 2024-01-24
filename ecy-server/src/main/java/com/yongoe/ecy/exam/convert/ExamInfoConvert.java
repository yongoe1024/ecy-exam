package com.yongoe.ecy.exam.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.ecy.exam.controller.vo.req.ExamInfoReq;
import com.yongoe.ecy.exam.controller.vo.res.ExamInfoRes;
import com.yongoe.ecy.exam.entity.ExamInfo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 考试信息 convert
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Mapper(componentModel = "spring")
public interface ExamInfoConvert {
    /**
     * entity to vo
     */
    ExamInfoReq entity2Req(ExamInfo entity);

    ExamInfoRes entity2Res(ExamInfo entity);

    /**
     * entity to vo List
     */
    List<ExamInfoReq> entity2ReqList(List<ExamInfo> list);

    List<ExamInfoRes> entity2ResList(List<ExamInfo> list);

    /**
     * entity to vo Page
     */
    Page<ExamInfoRes> entity2ResPage(Page<ExamInfo> page);

    /**
     * vo to entity
     */
    ExamInfo req2Entity(ExamInfoReq req);

}
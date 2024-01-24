package com.yongoe.ecy.exam.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.ecy.exam.controller.vo.excel.UserPaperExcel;
import com.yongoe.ecy.exam.controller.vo.req.UserPaperReq;
import com.yongoe.ecy.exam.controller.vo.res.UserPaperRes;
import com.yongoe.ecy.exam.entity.UserPaper;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 考生-试卷 convert
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Mapper(componentModel = "spring")
public interface UserPaperConvert {
    /**
     * entity to vo
     */
    UserPaperReq entity2Req(UserPaper entity);

    UserPaperRes entity2Res(UserPaper entity);

    UserPaperExcel entity2Excel(UserPaper entity);

    /**
     * entity to vo List
     */
    List<UserPaperReq> entity2ReqList(List<UserPaper> list);

    List<UserPaperRes> entity2ResList(List<UserPaper> list);

    List<UserPaperExcel> entity2ExcelList(List<UserPaper> list);

    /**
     * entity to vo Page
     */
    Page<UserPaperRes> entity2ResPage(Page<UserPaper> page);

    /**
     * vo to entity
     */
    UserPaper req2Entity(UserPaperReq req);

    UserPaper excel2Entity(UserPaperExcel excel);

    /**
     * vo to entity List
     */
    List<UserPaper> excel2EntityList(List<UserPaperExcel> list);


}
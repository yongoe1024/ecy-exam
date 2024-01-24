package com.yongoe.ecy.exam.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.ecy.exam.controller.vo.res.UserQuestionRes;
import com.yongoe.ecy.exam.entity.UserQuestion;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 考生-答案 convert
 *
 * @author yongoe
 * @since 2024/01/08
 */
@Mapper(componentModel = "spring")
public interface UserQuestionConvert {

    UserQuestionRes entity2Res(UserQuestion entity);

    /**
     * entity to vo List
     */
    List<UserQuestionRes> entity2ResList(List<UserQuestion> list);

    /**
     * entity to vo Page
     */
    Page<UserQuestionRes> entity2ResPage(Page<UserQuestion> page);


}
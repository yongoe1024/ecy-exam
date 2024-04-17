package com.yongoe.exam.system.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongoe.exam.system.controller.vo.req.RoleReq;
import com.yongoe.exam.system.controller.vo.res.RoleRes;
import com.yongoe.exam.system.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 角色 convert
 *
 * @author yongoe
 * @since 2023/1/1
 */
@Mapper(componentModel = "spring")
public interface RoleConvert {

    Role req2Entity(RoleReq reqVo);

    List<RoleRes> entity2Res(List<Role> list);

    Page<RoleRes> entity2ResPage(Page<Role> page);
}
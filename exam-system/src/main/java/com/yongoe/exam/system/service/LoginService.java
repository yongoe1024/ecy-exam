package com.yongoe.exam.system.service;

import com.yongoe.exam.system.controller.vo.req.ForgetReq;
import com.yongoe.exam.system.controller.vo.req.LoginReq;
import com.yongoe.exam.system.controller.vo.req.RegisterReq;
import com.yongoe.exam.utils.R;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 登录
 *
 * @author yongoe
 * @since 2023/1/1
 */
public interface LoginService {

    /**
     * 登陆之后返回token
     */
    R login(LoginReq loginReq, HttpServletRequest request);

    /**
     * 注册之后返回token
     */
    R register(RegisterReq register);

    /**
     * 找回密码
     */
    R forget(ForgetReq forget);
}

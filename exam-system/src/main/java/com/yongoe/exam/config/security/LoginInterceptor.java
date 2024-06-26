package com.yongoe.exam.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yongoe.exam.config.aop.IgnoreLogin;
import com.yongoe.exam.system.entity.User;
import com.yongoe.exam.system.service.UserService;
import com.yongoe.exam.utils.JwtUtils;
import com.yongoe.exam.utils.R;
import com.yongoe.exam.utils.UserThreadLocal;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录拦截器
 *
 * @author yongoe
 * @since 2023/1/1
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private UserService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        String token;
        token = request.getParameter("Authorization");
        if (StringUtils.isEmpty(token)) {
            token = request.getHeader(JwtUtils.requestHeader);
        }
        if (!StringUtils.isEmpty(token) && token.startsWith(JwtUtils.tokenHead)) {
            token = token.substring(JwtUtils.tokenHead.length());
            String id = JwtUtils.getKeyFromToken(token);
            if (!StringUtils.isEmpty(id)) {
                User user = userService.getUserById(Long.valueOf(id));
                if (user != null) {
                    if (!user.getEnabled()) {
                        R bean = R.error(403, "账号被禁用");
                        PrintWriter writer = response.getWriter();
                        writer.write(new ObjectMapper().writeValueAsString(bean));
                        writer.flush();
                        writer.close();
                        return false;
                    }
                    UserThreadLocal.put(user);
                    return true;
                }
            }
        }
        IgnoreLogin ignoreLogin;
        if (handler instanceof HandlerMethod) {
            ignoreLogin = ((HandlerMethod) handler).getMethodAnnotation(IgnoreLogin.class);
            if (ignoreLogin != null) {
                return true;
            }
        } else {
            return true;
        }
        R bean = R.error(401, "尚未登录，请登录！");
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(bean));
        writer.flush();
        writer.close();
        return false;
    }

    //防止内存溢出
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserThreadLocal.remove();
    }

}
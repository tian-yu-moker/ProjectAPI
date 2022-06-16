package com.hku.projectapi.Tools;



import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JwtInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //从 http 请求头中取出 token
        String token = request.getHeader("token");
//        System.out.println("此处测试是否拿到了token：" + token);

        if (token == null) {
            throw new RuntimeException("No valid token detected, please login first.");
        }

        // Check the token, see whether it is valid.
        JwtUtil.checkSign(token);
        return true;
    }
}



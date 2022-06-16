package com.hku.projectapi.Tools;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.hku.projectapi.Beans.Result;
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
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        //UTF-8编码
        httpResponse.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        if (token == null)
        {
            Result result = new Result("98", "No valid token detected, please login first.", "");
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(result);
            httpResponse.getWriter().print(json);
            return false;
        }


        // Check the token, see whether it is valid.
        boolean isValid = JwtUtil.checkSign(token);
        if(!isValid)
        {
            Result result = new Result("97", "Invalid token, permission denied.", "");
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(result);
            httpResponse.getWriter().print(json);
            return false;
        }

        return true;
    }
}



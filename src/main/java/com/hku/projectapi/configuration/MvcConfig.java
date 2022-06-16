package com.hku.projectapi.configuration;



import com.hku.projectapi.Tools.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

@Configuration
public class MvcConfig implements WebMvcConfigurer
{

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/author/**").addPathPatterns("/knowledge_service");
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}


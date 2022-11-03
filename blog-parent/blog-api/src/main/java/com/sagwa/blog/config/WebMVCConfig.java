package com.sagwa.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Sagwa
 * @Date 2022/10/30 9:47
 * @ClassName WebMVCConfig
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域配置:允许 8080 端口访问接口服务
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }
}

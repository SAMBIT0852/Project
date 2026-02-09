package com.cosmos.vcs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward Angular routes to index.html
        registry.addViewController("/dashboard").setViewName("forward:/index.html");
        registry.addViewController("/repository").setViewName("forward:/index.html");
        registry.addViewController("/history").setViewName("forward:/index.html");
        registry.addViewController("/changes").setViewName("forward:/index.html");
    }
}
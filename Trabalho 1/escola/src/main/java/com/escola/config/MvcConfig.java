package com.escola.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // Define o caminho para a pasta de uploads, funcionando em qualquer sistema operacional
    private static final String UPLOAD_DIR = "file:" + System.getProperty("user.home") + "/uploads/img/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("/uploads/img/**")
                .addResourceLocations(UPLOAD_DIR);
    }
}
package com.smu.multitenantapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
            registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/static/","classpath:/static/**","classpath:/image/")
            .setCachePeriod(0);
    }
}
package com.test.fakebook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.test.fakebook.interceptor.AuditInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Injecting AuditInterceptor bean
    @Autowired
    private AuditInterceptor auditInterceptor;

    // Configuring interceptors
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Adding AuditInterceptor to the interceptor registry
        registry.addInterceptor(auditInterceptor);
    }
}

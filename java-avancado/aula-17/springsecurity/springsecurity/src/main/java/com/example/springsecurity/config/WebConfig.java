package com.example.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.springsecurity.interceptor.AuditInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuditInterceptor auditInterceptor;

//    @Autowired
//    private IpAddressInterceptor ipAddressInterceptor;    

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(auditInterceptor);       //para a opção via Interceptor
        //registry.addInterceptor(ipAddressInterceptor); //para a opção via AOP
    }
}
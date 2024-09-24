package com.stlouiscatclinic.room_status_api;

import com.stlouiscatclinic.room_status_api.services.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Trevor Gruber
 */
@Component
public class WebApplicationConfig implements WebMvcConfigurer {
    @Autowired
    AuthenticationFilter authenticationFilter;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authenticationFilter);
    }
}

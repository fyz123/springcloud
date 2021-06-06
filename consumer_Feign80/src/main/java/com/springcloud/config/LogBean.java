package com.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogBean {

    @Bean
    public Logger.Level setLog(){
        return Logger.Level.FULL;
    }

}

package com.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableHystrixDashboard
@EnableEurekaClient
@Configuration
public class HistrixDashboardApp9001 {
    public static void main(String[] args) {
        SpringApplication.run(HistrixDashboardApp9001.class);
    }

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean regBean = new ServletRegistrationBean(streamServlet);
        regBean.setLoadOnStartup(1);
        List mappingList = new ArrayList();
        mappingList.add("/hystrix.stream");
        regBean.setUrlMappings(mappingList);
        regBean.setName("hystrixServlet");
        return regBean;
    }

}

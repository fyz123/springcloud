package com.cloud.payment2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("com.cloud.payment.dao")
@SpringBootApplication
@EnableEurekaClient
public class PaymentApp8082 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApp8082.class);
    }
}

package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentConsul8086Controller {
    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/consul/getPort")
    public String getConnect(){
        return "端口："+port;
    }

    @GetMapping("/payment/consul/hello")
    public String hello(){
        return "hello";
    }
}

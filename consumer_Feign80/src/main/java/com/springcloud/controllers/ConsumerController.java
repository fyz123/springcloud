package com.springcloud.controllers;

import com.springcloud.services.ConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    private ConsumerService consumerService;

    @GetMapping("/consumer/payment/connect")
    public String testConnect(){
        return consumerService.testConnect();
    }

    @GetMapping("/consumer/payment/timeout")
    public String testTimeout(){
        return consumerService.testTimeout();
    }

}

package com.springcloud.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.services.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class PaymenController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/histrix/ok")
    public String isOk(){
        return paymentService.isOk();
    }

    @GetMapping("/payment/histrix/timeout")
    @HystrixCommand(fallbackMethod = "getDeault",commandProperties = {@HystrixProperty(name = "" +
            "execution.isolation.thread.timeoutInMilliseconds",value = "2000")})
    public String isTimeout() throws InterruptedException {
        return paymentService.isTimeout();
    }

    public String getDeault(){
        return "payment端服务超时或异常，我来兜底！";
    }

}

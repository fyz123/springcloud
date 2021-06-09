package com.springcloud.controllers;

import cn.hutool.core.date.DateUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.services.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    /** 测试熔断 */
    @GetMapping("/payment/histrix/testDown/{id}")
    @HystrixCommand(fallbackMethod = "downFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String testDown(@PathVariable int id){
        if(id < 0){
            throw new RuntimeException("入参不能小于0");
        }
        return "请求成功，"+ DateUtil.current();
    }

    public String downFallback(@PathVariable int id){
        return "熔断期间的降级";
    }

}

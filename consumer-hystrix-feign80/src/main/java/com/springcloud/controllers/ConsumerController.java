package com.springcloud.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.services.ConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
/*@DefaultProperties(defaultFallback = "globelFallback")*/
public class ConsumerController {

    @Resource
    private ConsumerService consumerService;

    @GetMapping("/consumer/payment/histrix/ok")
    public String isOk(){
        return consumerService.isOk();
    }

    /*@HystrixCommand(fallbackMethod = "getDeault",commandProperties = {@HystrixProperty(name = "" +
            "execution.isolation.thread.timeoutInMilliseconds",value = "20")})*/
    @GetMapping("/consumer/payment/histrix/timeout")
    /*@HystrixCommand*/
    public String isTimeout() throws InterruptedException {
        /*int a = 1/0;*/
        TimeUnit.SECONDS.sleep(2);
        return consumerService.isTimeout();
    }

    public String getDeault(){
        return "我是消费端，你太慢了，我自己兜底！";
    }

    public String globelFallback(){
        return "我是消费端全局兜底！";
    }
}

package com.springcloud.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("payment")
public interface ConsumerService {

    @GetMapping("/payment/connect")
    public String testConnect();

    @GetMapping("/payment/timeout")
    public String testTimeout();

}


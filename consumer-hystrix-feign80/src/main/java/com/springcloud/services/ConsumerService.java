package com.springcloud.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(value = "PAYMENT-HYSTRIX",fallback = ConsumerServiceimp.class)
public interface ConsumerService {

    @GetMapping("/payment/histrix/ok")
    public String isOk();

    @GetMapping("/payment/histrix/timeout")
    public String isTimeout();

}

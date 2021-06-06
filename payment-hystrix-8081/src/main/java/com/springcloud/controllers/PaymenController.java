package com.springcloud.controllers;

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
    public String isTimeout() throws InterruptedException {
        return paymentService.isTimeout();
    }

}

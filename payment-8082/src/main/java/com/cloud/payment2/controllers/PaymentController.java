package com.cloud.payment2.controllers;

import com.cloud.common.entities.CommonResult;
import com.cloud.common.entities.Payment;
import com.cloud.payment2.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/query/{id}")
    public CommonResult query(@PathVariable long id){
        return new CommonResult(200,"查询成功，端口："+port,paymentService.query(id));
    }

    @PostMapping("/payment/save")
    public CommonResult save(@RequestBody Payment payment){
        return new CommonResult(200,"新增成功",paymentService.save(payment));
    }

    @GetMapping("/payment/connect")
    public String testConnect(){
        return "端口号：" + port;
    }

}

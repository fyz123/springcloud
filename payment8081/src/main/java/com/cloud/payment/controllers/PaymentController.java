package com.cloud.payment.controllers;

import com.cloud.common.entities.CommonResult;
import com.cloud.common.entities.Payment;
import com.cloud.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/query/{id}")
    public CommonResult query(@PathVariable long id){
        return new CommonResult(200,"查询成功",paymentService.query(id));
    }

    @PostMapping("/payment/save")
    public CommonResult save(@RequestBody Payment payment){
        return new CommonResult(200,"新增成功",paymentService.save(payment));
    }

}

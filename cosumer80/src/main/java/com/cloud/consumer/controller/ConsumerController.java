package com.cloud.consumer.controller;

import com.cloud.common.entities.CommonResult;
import com.cloud.common.entities.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    private final String URL = "http://PAYMENT";

    @GetMapping("/consumer/payment/query/{id}")
    public ResponseEntity<CommonResult> query(@PathVariable long id){
        String url = URL + "/payment/query/"+ id;
        return restTemplate.getForEntity(url, CommonResult.class);
    }

    @PostMapping("/consumer/payment/save")
    public ResponseEntity<CommonResult> save(Payment payment){
        String url = URL + "/payment/save";
        return restTemplate.postForEntity(url, payment, CommonResult.class);
    }

}

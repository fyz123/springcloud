package com.cloud.consumer.controller;

import com.cloud.common.entities.CommonResult;
import com.cloud.common.entities.Payment;
import com.cloud.consumer.libs.MyLb;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private MyLb myLb;

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

    @GetMapping("/consumer/payment/connect")
    public Object testConnectPayment(){
        String url = URL + "/payment/connect";
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/consumber/payment/myLab")
    public Object testMyLb(){
        List<ServiceInstance> payment = discoveryClient.getInstances("PAYMENT");
        ServiceInstance instance = myLb.chooseService(payment);

        String url = instance.getUri() + "/payment/connect";
        return restTemplate.getForObject(url, String.class);
    }

}

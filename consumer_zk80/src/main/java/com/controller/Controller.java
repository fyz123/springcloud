package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class Controller {

    private final String URL = "http://payment8084";
    @Resource
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/getPort")
    public String doConsumer(){
        return restTemplate.getForObject(URL + "/payment/getPort",String.class);
    }

}

package com.spring.controller;

import com.spring.ImessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class Controller {

    @Resource
    private ImessageSender messageSender;

    @GetMapping("/send")
    public String send(){
        return messageSender.send();
    }

}

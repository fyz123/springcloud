package com.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope
public class ClientController {

    @Value("${systemInfo}")
    private String systemInfo;

    @Value("${server.port}")
    private String port;

    @GetMapping("/config/client/connect")
    public String testConnect(){
        return systemInfo;
    }

}

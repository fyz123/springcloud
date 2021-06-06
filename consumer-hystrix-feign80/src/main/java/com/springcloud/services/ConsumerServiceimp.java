package com.springcloud.services;

import org.springframework.stereotype.Component;

@Component
public class ConsumerServiceimp implements ConsumerService{

    @Override
    public String isOk() {
        return "服务挂了，晚点再试 ！";
    }

    @Override
    public String isTimeout() {
        return "服务挂了，晚点再试 ！";
    }

}

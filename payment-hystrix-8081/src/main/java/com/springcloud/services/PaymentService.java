package com.springcloud.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String isOk(){
        return "当前线程："+Thread.currentThread().getName()+"调用ok方法成功";
    }


    public String isTimeout() throws InterruptedException {
        //TimeUnit.SECONDS.sleep(3);
        //int age = 2/0;
        return "当前线程："+Thread.currentThread().getName()+"调用timeOut方法成功";
    }
}

package com.cloud.payment.controllers;

import ch.qos.logback.core.util.TimeUtil;
import com.cloud.common.entities.CommonResult;
import com.cloud.common.entities.Payment;
import com.cloud.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    private Log log = LogFactory.getLog(PaymentController.class);

    @Value("${server.port}")
    private String port;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/query/{id}")
    public CommonResult query(@PathVariable long id){
        return new CommonResult(200,"查询成功,端口："+ port,paymentService.query(id));
    }

    @PostMapping("/payment/save")
    public CommonResult save(@RequestBody Payment payment){
        return new CommonResult(200,"新增成功",paymentService.save(payment));
    }

    @GetMapping("/payment/discovery")
    public Object doDiscovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("serviceName: "+ service);
        }

        List<ServiceInstance> payment = discoveryClient.getInstances("PAYMENT");
        for (ServiceInstance instance : payment) {
            log.info("instance: "+ instance.getHost()+"\t"+instance.getPort()+"\t"+
                    instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/payment/connect")
    public String testConnect(){
        return "端口号：" + port;
    }

    @GetMapping("/payment/timeout")
    public String testTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "超时接口,端口号：" + port;
    }
}

package com.cloud.payment.controllers;

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

}

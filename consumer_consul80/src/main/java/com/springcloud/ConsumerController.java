package com.springcloud;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    DiscoveryClient discoveryClient;
    @Resource
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/testSys")
    public String testSys(){
        return "consumer consul 80 is ok";
    }

    @GetMapping("/testConnect")
    public Object testConnect(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("payment-consul");
        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/payment/consul/hello", String.class);
        return callServiceResult;
        /*ServiceInstance choose = loadBalancerClient.choose("payment-consul");
        String url = "http://" + "payment-consul" +"/payment/consul/hello";
        return restTemplate.getForObject(url,String.class);*/
        //return discoveryClient.getInstances("payment-consul");
        /*String url = "http://payment-consul"+"/payment/consul/hello";
        return restTemplate.getForObject(url,String.class);*/
    }

}

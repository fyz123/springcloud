package com.cloud.consumer.libs;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLb implements LoadBalancer{
    //请求次数
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance chooseService(List<ServiceInstance> serviceInstances) {
        int next = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(next);
    }

    private final int getAndIncrement(){
        int current ;
        int next ;
        do{
            current = atomicInteger.get();
            next = current + 1 ;
        }while(!atomicInteger.compareAndSet(current,next));
        System.out.println("这是第"+next+"次调用！");
        return next;
    }

}

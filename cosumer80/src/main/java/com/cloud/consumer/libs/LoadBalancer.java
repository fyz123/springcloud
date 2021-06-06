package com.cloud.consumer.libs;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    ServiceInstance chooseService(List<ServiceInstance> serviceInstances);

}

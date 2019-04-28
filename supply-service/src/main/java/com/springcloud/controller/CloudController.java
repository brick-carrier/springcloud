package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CloudController {
    @Autowired
    private DiscoveryClient client;

    @Autowired
    private Registration registration;

    @RequestMapping("/add")
    public int add(@RequestParam int a,@RequestParam int b){
        ServiceInstance instance=getServiceInstance();
        int r=a+b;
        return r;
    }


    public ServiceInstance getServiceInstance(){
        List<ServiceInstance> instanceList=client.getInstances(registration.getServiceId());
        if (instanceList!=null){
            for (ServiceInstance s:instanceList){
                if (s.getPort()==1111){
                    return s;
                }
            }
        }
        return null;
    }

}

package com.miracle.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by naonao on 18/4/20.
 */
@RestController
@RequestMapping("/miracle")
public class TestController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String test() {
        return "miracle is naonao";
    }

    @RequestMapping(path = "/userInstances", method = RequestMethod.GET)
    public List<ServiceInstance> showInfo() {
        return discoveryClient.getInstances("MIRACLE-SERVICE");
    }
}

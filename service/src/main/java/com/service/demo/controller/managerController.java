package com.service.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class managerController {

    @Value("${server.port}")
    String port;


    @RequestMapping("/test")
    public String test(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am ------- port:"+port ;
    }
}

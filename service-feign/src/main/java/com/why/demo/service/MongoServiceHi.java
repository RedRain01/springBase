package com.why.demo.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "mongodbServer")
public interface MongoServiceHi {


    @RequestMapping(value = "/mong",method = RequestMethod.GET)
    String  mong(@RequestParam(value = "name") String name);


    @RequestMapping(value = "/mongo",method = RequestMethod.GET)
    String  mongo();
}


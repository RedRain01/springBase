package com.why.demo.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Component
@FeignClient(name = "serviceImpl")
public interface SchedualServiceHi {
    @RequestMapping(value = "/hiqq",method = RequestMethod.GET)
    String  demo();



   /* @RequestMapping(value = "/hiqq",method = RequestMethod.GET)
    String  hiqq(@RequestParam(value = "name")String name);*/

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    String  test(@RequestParam(value = "name")String name);


}


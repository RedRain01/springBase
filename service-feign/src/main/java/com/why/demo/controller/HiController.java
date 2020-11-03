package com.why.demo.controller;


import com.why.demo.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
@Autowired
    SchedualServiceHi schedualServiceHi;

    @RequestMapping("/test3")
    public String test3(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return schedualServiceHi.test(name);
    }

   @GetMapping(value = "hi")
    public  String sayHi(@RequestParam String  name){
    return schedualServiceHi.demo(name);
}

    @GetMapping(value = "test")
    public  String test(@RequestParam String  name){
        return schedualServiceHi.test(name);
    }



}



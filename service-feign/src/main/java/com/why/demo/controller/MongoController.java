package com.why.demo.controller;


import com.why.demo.service.MongoServiceHi;
import com.why.demo.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongoController {
@Autowired
MongoServiceHi mongoServiceHi;



    @GetMapping(value = "mong")
    public  String mong(@RequestParam String  name){
        return mongoServiceHi.mong(name);
    }


    @GetMapping(value = "ss")
    public  String test(){
        return mongoServiceHi .mongo();
    }
}



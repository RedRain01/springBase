package com.example.mysqlserver.controller;

import com.alibaba.fastjson.JSON;
import com.example.mysqlserver.entity.Whytest;
import com.example.mysqlserver.service.WhyTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;


@RestController
public class WhyTestController {

    @Resource
    private WhyTestService service;


    @GetMapping(value="/tttttt")
    public String GetIndoorCheckItemEntities(){
        Whytest whytest=new Whytest();
        whytest.setName("1");
        ArrayList<Whytest> why = service.findWhy();

        return JSON.toJSONString(why);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        return "99999999";
    }
}

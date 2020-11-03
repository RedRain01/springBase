package com.example.oracleserver.controller;

import com.alibaba.fastjson.JSON;
import com.example.oracleserver.entity.Whytest;
import com.example.oracleserver.mapper.WhyTestMapper;
import com.example.oracleserver.service.WhyTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
public class WhyTestController {

    @Autowired
    private WhyTestService service;

    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public String GetIndoorCheckItemEntities() {
        Whytest whytest = new Whytest();
        whytest.setName("1");
        ArrayList<Whytest> why = service.queryByWhytest(whytest);
        return JSON.toJSONString(why);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert() {
        try {
            Whytest whytest = new Whytest();
            for (int i = 0; i < 10000; i++) {
                whytest.setId(i);
                whytest.setName(String.valueOf(i));
                Integer insert = service.insert(whytest);
            }
            return "success";
        } catch (Exception e) {
            return "error";
        } finally {
        }
    }





}

package com.example.mysqlservers.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.example.mysqlservers.model.Orderdetail;
import com.example.mysqlservers.model.UserDomain;
import com.example.mysqlservers.user.OrderdetailService;
import com.example.mysqlservers.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;




    @Autowired
    private OrderdetailService orderdetailService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(UserDomain user){
        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        return userService.findAllUser(pageNum,pageSize);
    }

/*
商品名称，商品价格，商品数量
 */
    @ResponseBody
    @GetMapping("/addOrder")
    public Object addOrder(
            @RequestParam(name = "pageNum", required = false, defaultValue = "8")
                    String price,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    String name,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        try {
            for (int i = 0; i < pageSize; i++) {
                UUID uuid=UUID.randomUUID();
                String uuidStr=uuid.toString();
                Orderdetail orderdetail=new Orderdetail();
                orderdetail.setCustomer("bob");
                orderdetail.setCommodityName("phone");
                orderdetail.setOrderid(uuidStr);
                orderdetail.setOrderTime(new Date());
                orderdetail.setPrice(price);
                int ss = orderdetailService.create(orderdetail);
                if (ss!=1){
                    System.out.printf("======eeeeeee====="+ss);
                }
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "erroy";
        }
    }



    @ResponseBody
    @GetMapping("/aaa")
    public String aaa( ){
        List<Orderdetail> all = orderdetailService.all();
        return JSONUtils.toJSONString(all);
    }
}

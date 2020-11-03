package com.example.springkafka;

import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {

        List<Stu> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            UUID uuid=UUID.randomUUID();
            String uuidStr=uuid.toString();
            Stu stu=new Stu();
            stu.setId(uuidStr);
            list.add(stu);
        }

        System.out.printf("=========="+JSONArray.toJSONString(list));
    }
}

package com.example.mysqlserver.service;

import com.example.mysqlserver.entity.Whytest;
import com.example.mysqlserver.mapper.WhyTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;



@Service
public class WhyTestService {

        @Autowired
        private WhyTestMapper dao;

        public ArrayList<Whytest> findWhy() {
            return dao.queryByWhytest();
        }

}

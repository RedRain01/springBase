package com.example.oracleserver.service;


import com.example.oracleserver.entity.Whytest;
import com.example.oracleserver.mapper.WhyTestMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class WhyTestService {

        @Autowired
        private WhyTestMapper dao;

        public ArrayList<Whytest> queryByWhytest(Whytest whytest) {
            return dao.queryByWhytest(whytest);
        }

    public Integer insert(Whytest whytest) {
        return dao.insert(whytest);
    }

}

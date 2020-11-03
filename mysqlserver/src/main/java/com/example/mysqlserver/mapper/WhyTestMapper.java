package com.example.mysqlserver.mapper;

import com.example.mysqlserver.entity.Whytest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface WhyTestMapper {

    public ArrayList<Whytest> queryByWhytest();
}

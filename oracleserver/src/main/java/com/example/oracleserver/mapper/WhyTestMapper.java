package com.example.oracleserver.mapper;

import com.example.oracleserver.entity.Whytest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;


@Mapper //添加此注解，便可以被扫描到
public interface WhyTestMapper {

    @Select("SELECT * FROM WHYTEST")
    public ArrayList<Whytest> queryByWhytest(Whytest whytest);


    @Insert({ "insert into WHYTEST(ID, NAME) values(#{id}, #{name})" })
    public Integer insert(Whytest whytest);
}

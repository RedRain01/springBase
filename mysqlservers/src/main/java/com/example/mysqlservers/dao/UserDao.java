package com.example.mysqlservers.dao;



import com.example.mysqlservers.model.UserDomain;

import java.util.List;

public interface UserDao {


    int insert(UserDomain record);



    List<UserDomain> selectUsers();
}
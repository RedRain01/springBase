package com.example.oracleserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.example.oracleserver"})

//@MapperScan(basePackages={"com.example.oracleserver.service","com.example.oracleserver.mapper"}) //开启注解扫描，指定扫描文件为app.mapper包底下的所有包含`@mapper`的类。
@ServletComponentScan
public class OracleserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(OracleserverApplication.class, args);
    }

}

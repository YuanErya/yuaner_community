package com.yuanerya.yuaner_community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.yuanerya.yuaner_community.mapper")
public class YuanerCommunityApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(YuanerCommunityApplication.class, args);
    }

}

package com.yuanerya.yuaner_community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yuanerya.yuaner_community.mapper")
public class YuanerCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuanerCommunityApplication.class, args);
    }

}

package com.wangchuanan.wjj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan("com.wangchuanan.wjj.dao")
public class WjjApplication {

    public static void main(String[] args) {
        SpringApplication.run(WjjApplication.class, args);
    }

}

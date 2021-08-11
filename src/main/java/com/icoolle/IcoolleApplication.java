package com.icoolle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan
@EnableTransactionManagement
@MapperScan(basePackages = {"com.icoolle.provider.*.mapper"})
@SpringBootApplication
@EnableScheduling
public class IcoolleApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcoolleApplication.class, args);
    }

}

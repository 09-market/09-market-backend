package com.gonggu.market.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class GongguMarketApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GongguMarketApiApplication.class, args);
    }

    @PostConstruct
    public void before() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

}

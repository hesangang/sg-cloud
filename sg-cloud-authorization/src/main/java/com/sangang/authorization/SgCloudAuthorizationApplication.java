package com.sangang.authorization;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.sangang.authorization.web.mapper")
public class SgCloudAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SgCloudAuthorizationApplication.class, args);
    }

}

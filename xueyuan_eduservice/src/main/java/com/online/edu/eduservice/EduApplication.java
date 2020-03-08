package com.online.edu.eduservice;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableFeignClients
@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication()
@MapperScan("com.online.edu.eduservice.mapper")
public class EduApplication {
    public static void main(String[] args) {

        SpringApplication.run(EduApplication.class, args);
    }
}

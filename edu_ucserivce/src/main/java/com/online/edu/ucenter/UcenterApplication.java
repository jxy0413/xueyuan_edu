package com.online.edu.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther jxy
 * @Date 2020-03-01
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@MapperScan("com.online.edu.ucenter.mapper")
public class UcenterApplication {
        public static void main(String[] args) {
            SpringApplication.run(UcenterApplication.class, args);
        }

}

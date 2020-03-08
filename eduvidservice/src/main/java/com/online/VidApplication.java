package com.online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Auther jxy
 * @Date 2020-02-24
 */
@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
public class VidApplication {
    public static void main(String[] args) {
        SpringApplication.run(VidApplication.class,args);
    }
}

package com.online.edu.sta.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther jxy
 * @Date 2020-03-03
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.online.edu.sta.mapper")
public class staConfig {
}

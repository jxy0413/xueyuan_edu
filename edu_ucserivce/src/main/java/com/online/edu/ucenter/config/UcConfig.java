package com.online.edu.ucenter.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther jxy
 * @Date 2020-03-01
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.online.edu.ucenter.mapper")
public class UcConfig {

}

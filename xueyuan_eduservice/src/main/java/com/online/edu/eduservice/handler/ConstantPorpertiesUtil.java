package com.online.edu.eduservice.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Auther jxy
 * @Date 2020-02-01
 * 常量类 读取配置文件applicaiton.properties中的配置
 */
@Component
public class ConstantPorpertiesUtil implements InitializingBean {
    //服务器启动的时候 就会直接初始化
    @Value("${aliyun.oss.file.endpoint}")
    private  String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyid;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    @Value("career")
    private String career;

    //定义常量

    public static String ENDPOINT;

    public static String KEYID;

    public static String KEYSECRET;

    public static String BUCKETNANME;

    public static String CAREER;

    @Override
    public void afterPropertiesSet() throws Exception {
         ENDPOINT=endpoint;
         KEYID=keyid;
         KEYSECRET=keySecret;
         BUCKETNANME=bucketName;
         CAREER=career;
    }
}

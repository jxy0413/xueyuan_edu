package com.online.edu.eduservice.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.online.edu.common.R;
import com.online.edu.eduservice.handler.ConstantPorpertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther jxy
 * @Date 2020-01-31
 */
@CrossOrigin
@RequestMapping("/eduservice/oss")
@RestController
public class FileUploadController {
    /**
     * 上传操作
     */
    @PostMapping("/upload")
    public R uploadTeacherImg(@RequestParam("file") MultipartFile file,@RequestParam(value="host",required = false) String host) throws Exception{
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPorpertiesUtil.ENDPOINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPorpertiesUtil.KEYID;
        String accessKeySecret = ConstantPorpertiesUtil.KEYSECRET;
        String bucketName = ConstantPorpertiesUtil.BUCKETNANME;

        //1.获取上传文件 MultipartFile file
       //2.获取名称 文件名
        String filename = file.getOriginalFilename();
        //在文件名称添加之前 文件名称不重复
        String uuid = UUID.randomUUID().toString();
        filename = uuid+filename;


        //获取当前日期
        String filePath = new DateTime().toString("yyyy/MM/dd");
        //filename=filePath+"/"+filename;

        String hostName = ConstantPorpertiesUtil.CAREER;
        //如果上传头像 host没有值
        if(!StringUtils.isEmpty(host)){
            hostName = host;
        }

        filename = filePath+"/"+hostName+"/"+filename;

        InputStream in = file.getInputStream();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        /**
         * 三个参数
         * 第一个 BucketName
         * 第二个 文件参数名称
         * 第三个 文件参数输入流
         */
        ossClient.putObject(bucketName, filename,in);

        // 关闭OSSClient。
        ossClient.shutdown();


        //返回OSS路径的存储Path
        String path = "http://"+bucketName+"."+endpoint+"/"+filename;

        System.out.println(path);

        return  R.ok().data("imgUrl",path);
    }

}

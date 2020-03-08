package com.online.controller;
import com.online.edu.common.R;
import com.online.service.VidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther jxy
 * @Date 2020-02-24
 */
@RestController
@CrossOrigin
@RequestMapping("/vidservice/vod")
public class VidController{

    @Autowired
    private VidService vidService;
    //实现上传视频到阿里云服务器
    @PostMapping("/upload")
    public R uploadAliyunVideo(@RequestParam("file") MultipartFile file){
        //调用方法实现视频上传,返回上传之后视频id
        String videoId =vidService.uploadVideoAlyun(file);
        return R.ok().data("videoId",videoId);
    }

    //实现删除云端视频的方法
    @DeleteMapping("/{videoId}")
    public R deleteVideoIdAliyun(@PathVariable("videoId")String videoId){
         System.out.println("调用了么。。。。。。");
         vidService.deleteAliyun(videoId);
         return R.ok();
    }
}

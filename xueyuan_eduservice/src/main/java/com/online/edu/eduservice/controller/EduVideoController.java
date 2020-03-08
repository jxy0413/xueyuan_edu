package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-14
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-video/")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService ;

    //添加小结
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduChapter){
        boolean save = eduVideoService.save(eduChapter);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //根据id查询
    @GetMapping("{videoId}")
    public R getVideoId(@PathVariable Long videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.ok().data("eduVideo",eduVideo);
    }

    //修改方法
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        boolean b = eduVideoService.updateById(eduVideo);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //删除操作
    @DeleteMapping("deleteVideoId/{videoId}")
    public R deleteVideo(@PathVariable Long videoId) {
        System.out.println(videoId+"jiji");
        boolean b = eduVideoService.removeVideo(videoId);
        System.out.println(videoId+"hhh");
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }


}


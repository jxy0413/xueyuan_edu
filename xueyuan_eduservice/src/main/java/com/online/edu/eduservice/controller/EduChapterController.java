package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-14
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/chapter/")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    //根据id查询出章节跟小节
    @GetMapping("getChapterVideoList/{id}")
    public R getChapterVideoList(@PathVariable("id") String id){
        long l = Long.parseLong(id);
        List<EduChapterDto> list =  eduChapterService.getChapterVideoListCourseId(l);

       return R.ok().data("items",list);
    }

    //添加章节的方法
    @PostMapping("add")
    public R addChapter(@RequestBody EduChapter eduChapter){
        boolean save = eduChapterService.save(eduChapter);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据章节的id进行查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable("chapterId") Long chapterId){
        EduChapter byId = eduChapterService.getById(chapterId);
        return R.ok().data("eduChapter",byId);
    }

    //修改章节的方法
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        boolean result  = eduChapterService.updateById(eduChapter);
        if(result){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //删除功能
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable Long chapterId){
        boolean result = eduChapterService.removeChapterId(chapterId);
        if(result){
            return R.ok();
        }else {
            return R.error();
        }
    }
}


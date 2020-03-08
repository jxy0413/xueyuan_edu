package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.CourseInfoForm;
import com.online.edu.eduservice.entity.CourseQuery;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;
import com.online.edu.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-05
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    //2.根据课程信息 查询
    @GetMapping("getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable Long id){
        CourseInfoForm courseInfoForm = eduCourseService.getIdCourse(id);
        return R.ok().data("courseInfoForm",courseInfoForm);
   }


     //1.添加课程信息的方法
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        Long courseId = eduCourseService.insertCourse(courseInfoForm);

        return R.ok().data("courseId",courseId);
    }

    //3.修改课程的方法
    @PostMapping("/updateCourseInfo/{id}")
    public R updateCourseInfo(@PathVariable Long id,@RequestBody CourseInfoForm courseInfoForm){
       boolean flag =  eduCourseService.updateCourse(courseInfoForm);
       if(flag){
           return  R.ok();
       }else{
           return  R.error();
       }
    }

    //4.课程列表
    //TODU 完善列表带分页 带条件查询 （课程名称，价格）
    @ApiModelProperty(value = "分页课程类列表")
    @GetMapping("listCourse/{page}/{limit}")
    public R listCourse(@PathVariable Long page, @PathVariable Long limit, @RequestBody(required = false) CourseQuery courseQuery){
        Page<EduCourse> pageParam = new Page(page,limit);

        eduCourseService.pageQuery(pageParam,courseQuery);

        List<EduCourse> records = pageParam.getRecords();

        long total = pageParam.getTotal();

        return R.ok().data("total",total).data("rows",records);
    }

    //5.删除课程的方法
    @DeleteMapping("/deleteCourseId/{id}")
    public R deleteCourseId(@PathVariable("id")Long id){
       boolean flag =  eduCourseService.removeCourseById(id);
       if(flag){
           return R.ok();
       }else{
           return R.error();
       }
    }

    //根据课程id 查询课程详情信息
    @GetMapping("/getAllCourseInfo/{courseId}")
    public R getAllCourseInfo(@PathVariable String courseId){
        long l = Long.parseLong(courseId);
        CourseInfoDto courseInfoDto= eduCourseService.getCourseInfoAll(l);
        System.out.println(courseInfoDto+"----");
      return R.ok().data("courceInfo",courseInfoDto);
    }

    //最终课程发方法
    @PostMapping("/publicCourse/{courseId}")
    public R publicCourse(@PathVariable("courseId") Long courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        boolean result = eduCourseService.updateById(eduCourse);
        System.out.println(courseId);
        if(result){
            return R.ok();
        }else{
            return R.error();
        }
    }

}


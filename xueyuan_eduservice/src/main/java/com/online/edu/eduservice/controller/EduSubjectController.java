package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.entity.SubjectNestedVo;
import com.online.edu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-02
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;



   //通过上传Excel文件获取内容
    @PostMapping("import")
    public R importExcelSubject(@RequestParam("file") MultipartFile file){
        //1.获取上传excel文件
        List<String> list = eduSubjectService.importSubject(file);
        return  R.ok().data("msg",list);
    }

    //展示前台页面
    @GetMapping("list")
    public R nestedList(){
      List<SubjectNestedVo> subjectNestedVoList= eduSubjectService.nestedList();
        System.out.println(subjectNestedVoList);
      return R.ok().data("items",subjectNestedVoList);
    }

    //删除一级分类
    @DeleteMapping("{id}")
    public R deleteSubjectId(@PathVariable Long id){
        System.out.println(id);
        boolean flag = eduSubjectService.deleteSubjectById(id);
        System.out.println(id);
        System.out.println(flag);
        if(flag==true){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //实现添加一级分类
    @PostMapping("/addOneLevel")
    public R addOneLevel(@RequestBody EduSubject eduSubject){
        boolean flag = eduSubjectService.saveOneLevel(eduSubject);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //实现二级分类
    @PostMapping("addTwoLevel")
    public R addTwoLevel(@RequestBody EduSubject eduSubject){
        boolean flag = eduSubjectService.saveTwoLevel(eduSubject);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

}


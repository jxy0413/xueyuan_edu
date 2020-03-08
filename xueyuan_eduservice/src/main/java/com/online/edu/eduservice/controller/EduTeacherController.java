package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.QueryTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-25
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;


    /**
     * 查询所有讲师的功能
     */
    @GetMapping("list")
    public R getAllTeacherList(){
        //调用Service的方法
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    /**
     * 模拟登陆
     */
     @PostMapping("/login")
     public R login(){
         return R.ok().data("token","admin");
     }

     @GetMapping("/info")
     public R info(){
         return R.ok().data("roles","[admin]").data("name","admin")
                 .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
     }


    /**
     * 实现逻辑删除讲师
     */
    @DeleteMapping("{id}")
    public boolean deleteTeacher(@PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        return b;
    }

    /**
     * 分页查询 讲师列表
     */
    @GetMapping("/pageList/{page}/{limit}")
    public R getPageTeacherList(@PathVariable Long page,@PathVariable Long limit){
        //创建page对象，传递两个参数
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        //调用分页查询方法
        eduTeacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();

        List<EduTeacher> records = pageTeacher.getRecords();


        return R.ok().data("total",total).data("items",records);
    }


    /**
     * 多条件组合查询 并且带分页
     */
    @PostMapping("moreCondtionPageList/{page}/{limit}")
    public R getmoreConditionPageList(@PathVariable Long page, @PathVariable Long limit,@RequestBody(required = false) QueryTeacher queryTeacher){
       Page<EduTeacher> pageTeacher = new Page<>(page,limit);
       //调用service来实现
        eduTeacherService.pageListCondition(pageTeacher,queryTeacher);

        long total = pageTeacher.getTotal();

        List<EduTeacher> records = pageTeacher.getRecords();


        return R.ok().data("total",total).data("items",records);
    }


    /**
     * 添加讲师的方法
     */
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        System.out.println(eduTeacher);
        long l = System.currentTimeMillis();
        eduTeacher.setId(l);
        boolean save = eduTeacherService.save(eduTeacher);
        System.out.println(save);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }

    }

    /**
     * 根据id查询
     */
    @GetMapping("/getTeacherInfo/{id}")
    public R getTeacherInfo(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);

        return R.ok().data("teacher",eduTeacher);
    }


    /**
     * 根据Id修改的方法
     */
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher teacher){

        System.out.println(teacher);
        boolean b = eduTeacherService.updateById(teacher);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }

    }
}


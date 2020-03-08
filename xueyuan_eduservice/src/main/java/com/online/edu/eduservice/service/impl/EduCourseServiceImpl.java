package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.Exception.EduException;
import com.online.edu.eduservice.entity.*;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;
import com.online.edu.eduservice.mapper.EduCourseMapper;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.online.edu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.Date;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-05
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService{

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    /**
     * 添加课程的一些信息
     * @param courseInfoForm
     * @return
     */
    @Override
    public Long insertCourse(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        //插入到课程表
        int insert = baseMapper.insert(eduCourse);
        if(insert==0){  //失败
            //抛出异常
            throw new EduException(20001,"课程添加失败");
        }else{
            EduCourseDescription eduCourseDescription = new EduCourseDescription();

            //获取描述信息
            BeanUtils.copyProperties(courseInfoForm,eduCourseDescription);

            //课程id

            System.out.println("++++"+eduCourseDescription);

            //真正添加
            eduCourseDescription.setIdbak(eduCourse.getId());
            eduCourseDescriptionService.save(eduCourseDescription);

            System.out.println(eduCourseDescription);

            //eduCourseService.save(eduCourseDescription);

            return eduCourse.getId();

        }

    }

    //根据
    @Override
    public CourseInfoForm getIdCourse(Long id) {
        //查询两张表
        //根据Id查询基本信息表
        EduCourse eduCourse = baseMapper.selectById(id);

        if(eduCourse==null){
            //没有课程信息
            throw new EduException(20001,"没有课程信息");
        }
        //复制信息
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);

        //已经有了基本信息  没有描述信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("idbak",id);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getOne(queryWrapper);
        //EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);

        System.out.println(eduCourseDescription);
        String description = eduCourseDescription.getDescription();

        courseInfoForm.setDescription(description);
        return courseInfoForm;
    }


    @Override
    public boolean updateCourse(CourseInfoForm courseInfoForm) {
        //1.修改基本信息表
        EduCourse eduCourse  = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int res = baseMapper.updateById(eduCourse);

        if(res==0){
            throw new EduException(20001,"修改分类");
        }

        EduCourseDescription eduCourseDescription = new EduCourseDescription();

        BeanUtils.copyProperties(courseInfoForm,eduCourseDescription);


        boolean b = eduCourseDescriptionService.updateById(eduCourseDescription);

        //2。修改描述表
        return b;
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        //直接全查的时候
        if(courseQuery==null){
            baseMapper.selectPage(pageParam,queryWrapper);
            return;
        }

        //有参数的时候
        String title = courseQuery.getTitle();
        String subjectId = courseQuery.getSubjectId();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();

        if(!StringUtils.isEmpty(title)){
            queryWrapper.like("title",title);
        }

        if(!StringUtils.isEmpty(teacherId)){
            queryWrapper.eq("teacher_id",teacherId);
        }

        if(!StringUtils.isEmpty(subjectId)){
            queryWrapper.ge("subject_id",subjectId);
        }

        if(!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.ge("subject_parent_id",subjectParentId);
        }

        baseMapper.selectPage(pageParam,queryWrapper);
    }

    @Override
    public boolean removeCourseById(Long id) {

        //根据课程Id删除
        eduChapterService.deleteChapterByCourseId(id);

        //根据课程Id删除
        eduVideoService.deleteVideoByCourseId(id);

        //根据课程id删除课程描述
        eduCourseDescriptionService.deleteDescription(id);

        //删除课程本身
        int delete = baseMapper.deleteById(id);
        return delete>0;
    }

    //根据课程id来创建
    @Override
    public CourseInfoDto getCourseInfoAll(Long courseId) {
        CourseInfoDto courseInfoAll = baseMapper.getCourseInfoAll(courseId);
        return courseInfoAll;
    }
}

package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.CourseInfoForm;
import com.online.edu.eduservice.entity.CourseQuery;
import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-05
 */
public interface EduCourseService extends IService<EduCourse> {

    Long insertCourse(CourseInfoForm courseInfoForm);

    CourseInfoForm getIdCourse(Long id);

   // void save(EduCourseDescription eduCourseDescription);

    boolean updateCourse(CourseInfoForm courseInfoForm);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(Long id);

    CourseInfoDto getCourseInfoAll(Long courseId);
}

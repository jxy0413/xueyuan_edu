package com.online.edu.eduservice.mapper;

import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.entity.dto.CourseInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-02-05
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    void save(EduCourseDescription eduCourseDescription);

    //根据课程id查询课程详情
    CourseInfoDto getCourseInfoAll(@RequestParam("courseId") Long courseId);
}

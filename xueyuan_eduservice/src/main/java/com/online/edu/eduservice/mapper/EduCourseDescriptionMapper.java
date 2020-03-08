package com.online.edu.eduservice.mapper;

import com.online.edu.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 * 课程简介 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-02-05
 */
@Mapper
public interface EduCourseDescriptionMapper extends BaseMapper<EduCourseDescription> {
    void addDescription(@Param("eduCourseDescription") EduCourseDescription eduCourseDescription);
}

package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-05
 */
@Component
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    void add(EduCourseDescription eduCourseDescription);

    void deleteDescription(Long id);
}

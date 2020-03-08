package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.mapper.EduCourseDescriptionMapper;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-05
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService{

    @Autowired(required = false)
    private EduCourseDescriptionMapper eduCourseDeseriptionMapper;

    @Override
    public void add(EduCourseDescription eduCourseDescription) {
        eduCourseDeseriptionMapper.addDescription(eduCourseDescription);
    }

    @Override
    public void deleteDescription(Long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("idbak",id);
        baseMapper.delete(queryWrapper);
    }
}

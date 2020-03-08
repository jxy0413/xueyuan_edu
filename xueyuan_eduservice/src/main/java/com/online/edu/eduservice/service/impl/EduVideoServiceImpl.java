package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.online.edu.eduservice.client.VodClient;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.mapper.EduVideoMapper;
import com.online.edu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-14
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService{
      @Autowired
      private VodClient vodClient;

    @Override
    public void deleteVideoByCourseId(Long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id",id);
        baseMapper.delete(queryWrapper);
    }

    @Override
    public boolean removeVideo(Long videoId) {
        EduVideo eduVideo = baseMapper.selectById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();


            vodClient.deleteVideoIdAliyun(videoSourceId);
            System.out.println("");
        //TODO 删除小结的时候 还要删除阿里云的视频
        int i = baseMapper.deleteById(videoId);

        System.out.println(i);
        return i>0;
    }
}

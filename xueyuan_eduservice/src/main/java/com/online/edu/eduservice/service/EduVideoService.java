package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-14
 */
public interface EduVideoService extends IService<EduVideo> {

    void deleteVideoByCourseId(Long id);

    boolean removeVideo(Long videoId);
}

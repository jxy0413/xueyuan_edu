package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.Exception.EduException;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.entity.dto.EduVideoDto;
import com.online.edu.eduservice.mapper.EduChapterMapper;
import com.online.edu.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-14
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService{

    @Autowired
    private EduVideoService eduVideoService;

    //根据课程id来删除章节
    @Override
    public void deleteChapterByCourseId(Long id) {

        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);

    }


    @Override
    public List<EduChapterDto> getChapterVideoListCourseId(Long courseId) {
        //1.根据课程Id查询出章节
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(queryWrapper);

        //2.根据课程id查询小节
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        List<EduVideo>eduVideos = eduVideoService.list(queryWrapper);

        List<EduChapterDto>list = new ArrayList();
        //遍历所有章节 复制到dto对象里面
        for(int i=0;i<eduChapters.size();i++){
            List<EduVideoDto> dtoList = new ArrayList<>();
            //获取每个章节
            EduChapter eduChapter = eduChapters.get(i);
            //复制到Dto里面去
            EduChapterDto eduChapterDto = new EduChapterDto();
            BeanUtils.copyProperties(eduChapter,eduChapterDto);
            //dto对象放到list集合里面
            list.add(eduChapterDto);

            //构建小结数据
            for(int m=0;m<eduVideos.size();m++){
                //获取小节
                EduVideo eduVideo = eduVideos.get(m);
                //判断小节chapterid和章节的是否一样
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    //转换dto对象
                    EduVideoDto eduVideoDto = new EduVideoDto();
                    BeanUtils.copyProperties(eduVideo,eduVideoDto);
                    dtoList.add(eduVideoDto);
                }

            }
            eduChapterDto.setChildren(dtoList);
        }
        return list;
    }

    @Override
    public boolean removeChapterId(Long chapterId) {
        //如果章节下面有小结 就不删除 否则删除
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(queryWrapper);
        //不能删除
        if(count>0){
            throw new EduException(20001,"删除失败");
        }else{
        //进行真正的删除
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }
    }
}

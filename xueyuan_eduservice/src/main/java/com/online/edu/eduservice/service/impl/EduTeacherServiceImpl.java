package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.QueryTeacher;
import com.online.edu.eduservice.mapper.EduTeacherMapper;
import com.online.edu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import static com.baomidou.mybatisplus.core.toolkit.StringUtils.isEmpty;


/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-25
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    //条件查询 再分页
    @Override
    public void pageListCondition(Page<EduTeacher> pageTeacher, QueryTeacher queryTeacher) {
      if(queryTeacher == null){
          IPage<EduTeacher> eduTeacherIPage = baseMapper.selectPage(pageTeacher, null);
          return;

      }else{
          //从QueryTeacher获取值
          String name = queryTeacher.getName();
          String level = queryTeacher.getLevel();
          String begin = queryTeacher.getBegin();
          String end = queryTeacher.getEnd();

          //判断是否有值
          QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
          if(!isEmpty(name)){
              //拼接条件
              wrapper.like("name",name);
          }

          if(!isEmpty(level)){
              //拼接条件
              wrapper.eq("level",level);
          }

          if (!isEmpty(begin)) {
              wrapper.ge("gmt_create",begin);
          }

          if (!isEmpty(end)) {
              wrapper.le("gmt_modified",end);
          }

          //条件查询带分页
          baseMapper.selectPage(pageTeacher,wrapper);

      }
    }
}

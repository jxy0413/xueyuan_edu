package com.online.edu.eduservice.entity.dto;

import lombok.Data;

/**
 * @Auther jxy
 * @Date 2020-02-20
 * 用于封装课程信息的实体类
 */
@Data
public class CourseInfoDto {
       private Long id;
       private String title;
       private String cover;
       private String price;
       private String descrption;
       private String teacherName;
       private String levelOne;
       private String levelTwo;
}

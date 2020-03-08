package com.online.edu.eduservice.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther jxy
 * @Date 2020-02-13
 */
@ApiModel(value = "Course查询对象",description = "课程查询封装对象")
@Data
public class CourseQuery implements Serializable {
    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value="讲师id")
    private String teacherId;

    @ApiModelProperty(value = "一级类别Id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级类别Id")
    private String subjectId;

}

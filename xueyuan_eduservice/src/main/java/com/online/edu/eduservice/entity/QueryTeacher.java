package com.online.edu.eduservice.entity;

import lombok.Data;

@Data
public class QueryTeacher {
    private String name;
    private String level;
    private String begin; //开始
    private String end ;  //结束
}

package com.online.edu.eduservice.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther jxy
 * @Date 2020-02-03
 */
@Data
public class SubjectNestedVo {
    private Long id;
    private String title;
    private List<SubjectVo> children = new ArrayList<>();
}

package com.online.edu.eduservice.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther jxy
 * @Date 2020-02-14
 */
@Data
public class EduChapterDto {
    private Long id;
    private String title;
    //一个章节很多个小节
    private List<EduVideoDto> children = new ArrayList<>();
}

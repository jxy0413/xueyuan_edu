package com.online.edu.eduservice.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther jxy
 * @Date 2020-02-14
 */
@Data
public class EduVideoDto {
    private Long id;

    private String title;


    @ApiModelProperty(value = "云服务器上存储的视频文件名称")
    private String videoOriginalName;
}

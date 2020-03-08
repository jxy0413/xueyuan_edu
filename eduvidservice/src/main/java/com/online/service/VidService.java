package com.online.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther jxy
 * @Date 2020-02-24
 */
public interface VidService {
    String uploadVideoAlyun(MultipartFile file);

    void deleteAliyun(String videoId);
}

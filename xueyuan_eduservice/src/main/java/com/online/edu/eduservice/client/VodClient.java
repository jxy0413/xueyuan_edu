package com.online.edu.eduservice.client;

import com.online.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("eduvidservice")
@Component
public interface VodClient {
    @DeleteMapping(value="/vidservice/vod/{videoId}")
    public R deleteVideoIdAliyun(@PathVariable("videoId")String videoId);
}

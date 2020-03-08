package com.online.edu.sta.controller;


import com.online.edu.common.R;
import com.online.edu.sta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-03
 */
@CrossOrigin
@RestController
@RequestMapping("/sta/statistics-daily/")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

      //获取某一天的注册人数
       @GetMapping("getRegisterNum/{day}")
       public R getRegisterNum(@PathVariable("day")String day){
           statisticsDailyService.getCountRegisterNum(day);
           return R.ok();
       }
}


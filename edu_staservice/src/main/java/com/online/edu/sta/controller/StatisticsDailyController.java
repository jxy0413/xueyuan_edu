package com.online.edu.sta.controller;


import com.online.edu.common.R;
import com.online.edu.sta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    //返回图表显示的使用数据
    //第一部分时间：['2019-01-02','2019-01-19']
    //第二部分数量：[5,3]
    //第一个查询因子
    //第二个参数 开始时间  第三个参数 结束时间
    @GetMapping("getCountData/{type}/{begin}/{end}")
    public R getCountData(@PathVariable String type,@PathVariable String begin,@PathVariable String end){
        Map<String,Object> map = statisticsDailyService.getDataCount(type,begin,end);
        return R.ok().data(map);
    }

}


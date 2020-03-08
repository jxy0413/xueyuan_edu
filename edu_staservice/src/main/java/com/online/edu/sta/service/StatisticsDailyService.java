package com.online.edu.sta.service;

import com.online.edu.sta.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-03-03
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void getCountRegisterNum(String day);
}

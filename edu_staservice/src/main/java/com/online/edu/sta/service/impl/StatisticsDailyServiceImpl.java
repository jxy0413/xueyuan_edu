package com.online.edu.sta.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.common.R;
import com.online.edu.sta.client.UcenterClient;
import com.online.edu.sta.entity.StatisticsDaily;
import com.online.edu.sta.mapper.StatisticsDailyMapper;
import com.online.edu.sta.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-03-03
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService{
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void getCountRegisterNum(String day) {
        //首先判断是否有
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("date_calculated",day);
        baseMapper.delete(queryWrapper);

        R r = ucenterClient.countRegisterNum(day);
        Integer registerCount =(Integer)r.getData().get("result");
        //把获取数据添加到统计分析表里面
        Integer countseNum = RandomUtils.nextInt(100, 200);
        Integer loginseNum = RandomUtils.nextInt(100, 200);
        Integer videoseNum = RandomUtils.nextInt(100, 200);


        StatisticsDaily daily = new StatisticsDaily();
        UUID uuid = UUID.randomUUID();
        daily.setId(uuid.toString().substring(0,8));
        daily.setDateCalculated(day);
        daily.setRegisterNum(registerCount);
        daily.setCourseNum(countseNum);
        daily.setLoginNum(loginseNum);
        daily.setVideoViewNum(videoseNum);
        daily.setGmtCreate(new Date());
        daily.setGmtModified(new Date());

        baseMapper.insert(daily);
    }
}

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

import java.util.*;

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

    //根据时间范围查询统计数据
    @Override
    public Map<String, Object> getDataCount(String type, String begin, String end) {
        //构建查询条件
        QueryWrapper<StatisticsDaily>wrapper = new QueryWrapper();
        //大于开始时间   小于结束时间
        wrapper.ge("date_calculated",begin);
        wrapper.le("date_calculated",end);

        //查询指定的字段
        //具体和时间查询因子 前端传递type就是字段名字
        wrapper.select("date_calculated",type);
        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(wrapper);

        //把查询出来的list集合 转成map集合
        //创建集合 存储时间
        List<String> timeList = new ArrayList<>();
        //创建数据 存储数据
        List<Integer> dataList = new ArrayList<>();
        //遍历list集合
        for(int i=0;i<statisticsDailies.size();i++){
            StatisticsDaily daily = statisticsDailies.get(i);
            //构建所有list的集合
            String dateCalculated = daily.getDateCalculated();
            timeList.add(dateCalculated);

            //因为要获取哪个数据不一定 要根据type根据判断
            switch (type){
                case "login_num":
                    dataList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    dataList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    dataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    dataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        //把我们构建出来的list集合 放到map集合中
        Map<String,Object> map = new HashMap<>();
        map.put("timeList",timeList);
        map.put("dataList",dataList);
        return map;
    }
}

package com.pak.schedu;

import com.pak.service.PKLotteryService;
import com.pak.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by huyiwei on 2018/10/25.
 */
@Component
public class LotteryTimeTask {

    @Autowired
    PKLotteryService pkLotteryService;
    @Autowired
    StatisticService statisticService;

    /**
     * 8秒鈡调一次接口录接口
     */
    @Scheduled(initialDelay = 10, fixedRate = 8000)
    public void addPKResult() {
        pkLotteryService.addPKResult();
    }

    /**
     * 10计算一次
     */
    @Scheduled(initialDelay = 10, fixedRate = 10000)
    public void calNumStatistic() {
        statisticService.calNumStatistic(1);
    }
}
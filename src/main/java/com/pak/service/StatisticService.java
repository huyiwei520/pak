package com.pak.service;

import com.pak.service.impl.StatisticServiceImpl;

/**
 * Created by huyiwei on 2018/10/28.
 */
public interface StatisticService{

    void calNumStatistic(Integer userId);

    default void testF(){
        System.out.println("==parent");
    }
}

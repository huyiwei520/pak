package com.pak.service.impl;

import com.pak.common.Constants;
import com.pak.dto.ResultDto;
import com.pak.dto.StatisticDto;
import com.pak.repository.IPKLotteryDao;
import com.pak.repository.StatisticLotteryDao;
import com.pak.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by huyiwei on 2018/10/28.
 */
@Component
public class StatisticServiceImpl implements StatisticService{

    @Autowired
    private IPKLotteryDao pklotteryDao;

    @Autowired
    private StatisticLotteryDao statisticLotteryDao;

    @Override
    public void calNumStatistic(Integer userId) {
        int status = pklotteryDao.findTaskStatus(Constants.NUM_TWO);
        if(status ==0){
            Map<String,Object> params = new HashMap<>();
            List<StatisticDto> statisticList = new ArrayList<>();
            int limitCount = 15;
            List<Integer> numList = new ArrayList<>();
            ResultDto openDto = pklotteryDao.findResultObject(params);
            int count = statisticLotteryDao.findStatisticCount(openDto.getPeriod());
            if(count==0){
                params.put("limitCount",limitCount);
                params.put("period",openDto.getPeriod());
                List<ResultDto> resultList = pklotteryDao.findResultList(params);
                numList.add(Constants.NUM_ONE);
                numList.add(Constants.NUM_TWO);
                numList.add(Constants.NUM_THREE);
                numList.add(Constants.NUM_FOUR);
                numList.add(Constants.NUM_FIVE);
                numList.add(Constants.NUM_SIX);
                numList.add(Constants.NUM_SEVEN);
                numList.add(Constants.NUM_EIGHT);
                numList.add(Constants.NUM_NIGHT);
                numList.add(Constants.NUM_TEN);
                for(int t = 0;t<numList.size();t++){
                    int num = numList.get(t);
                    for(int i=1;i<=10;i++){
                        for(int j=1;j<=10;j++){
                            for(int z=1;z<=10;z++){
                                for(int k=1;k<=10;k++){
                                    if(i!=j&&i!=z&&i!=k&&j!=z&&j!=k&&z!=k){
                                        int occrCount = 0;
                                        for(int q=0;q<resultList.size();q++){
                                            ResultDto resultDto = resultList.get(q);
                                            int num1 = resultDto.getNum1();
                                            long hisPeriod = resultDto.getPeriod();
                                            if(i==num1||j==num1|z==num1||k==num1){
                                                occrCount = 0;
                                            }else{
                                                occrCount = occrCount +1;
                                            }
                                            if(occrCount>=limitCount-3){
                                                StatisticDto saveDto = new StatisticDto();
                                                long period = openDto.getPeriod();
                                                long periodCount = period-hisPeriod;
                                                saveDto.setPeriod(period);
                                                saveDto.setUserId(userId);
                                                String groupNum = i+","+j+","+z+","+k;
                                                saveDto.setGroupNumber(groupNum);
                                                saveDto.setNum(num);
                                                saveDto.setPeriodCount(Integer.valueOf(String.valueOf(periodCount)));
                                                statisticList.add(saveDto);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(!CollectionUtils.isEmpty(statisticList)){
                    statisticLotteryDao.addStatisticRecored(statisticList);
                }
            }
        }
    }

    @Override
    public void testF(){
        System.out.println("test child");
    }
}

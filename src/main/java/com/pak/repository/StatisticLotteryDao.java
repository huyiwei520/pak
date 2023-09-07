package com.pak.repository;

import com.pak.dto.*;
import org.apache.ibatis.annotations.Mapper;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * Created by huyiwei on 2018/9/26.
 */
@Mapper
public interface StatisticLotteryDao {

     void addStatisticRecored(List<StatisticDto> list);

     int findStatisticCount(@PathParam("period") Long period);
}

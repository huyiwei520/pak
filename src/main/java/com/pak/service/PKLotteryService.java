package com.pak.service;

import com.pak.dto.HistoryDto;
import com.pak.dto.ProgramDto;
import com.pak.dto.ResultDto;
import com.pak.dto.TimeDto;

import java.util.List;
import java.util.Map;

/**
 * Created by huyiwei on 2018/9/26.
 */
public interface PKLotteryService {

    /**
     * 添加开奖结果
     */
    void addPKResult();

    ResultDto findOpenResult(Map<String,Object> parmas);

    /**
     * 获取上几期位置结果
     * @return
     */
    List<ResultDto> getLastPositionData(Map<String,Object> map);
    List<HistoryDto> findHistoryList(Map<String,Object> map);
    void updatePKFlag(Map<String,Object> map);
    void updateDataFlag(Map<String,Object> map);
    void updateOneFlag(Map<String,Object> map);
    void updateTwoFlag(Map<String,Object> map);
    void updateThreeFlag(Map<String,Object> map);
    void updateFourFlag(Map<String,Object> map);
    void updateFiveFlag(Map<String,Object> map);
    void updateSixFlag(Map<String,Object> map);
    void updateSevenFlag(Map<String,Object> map);
    void updateEightFlag(Map<String,Object> map);
    void updateNightFlag(Map<String,Object> map);
    void updateTenFlag(Map<String,Object> map);
    void addHistoryMsg(HistoryDto historyDto);
    List<Integer> getLastPosition(Map<String,Object> map,int numType,int num);
    ProgramDto findProgramById(Map<String,Object> map);
    void updateProgramStatus(ProgramDto programDto);
    void updateProgramLineStatus(ProgramDto programDto);
    List<ProgramDto> findProgramList(Map<String,Object> map);
    void updateProgram(ProgramDto dto);
    TimeDto findTimeResult(Map<String,Object> map);
    Map<Integer,List<Integer>> findLastDoubleThreeSpace(Map<String,Object> parmas);
    int findLastEightCoolNumber(Map<String,Object> parmas,Integer num1,Integer num2);
    void testmaotai();
}

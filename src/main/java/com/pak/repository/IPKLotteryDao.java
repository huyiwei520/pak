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
public interface IPKLotteryDao {

     void addPKResult(ResultDto resultDto);

     void addIdentify(List<IdentifyDto> list);

     int findResultCount(Map<String,Object> map);

     List<ResultDto> findResultList(Map<String,Object> map);
     ResultDto findOpenResult(Map<String,Object> map);
     ResultDto findResultObject(Map<String,Object> map);
     ProgramDto findProgramById(Map<String,Object> map);
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
     void updateProgram(ProgramDto dto);
     void updateProgramStatus(ProgramDto programDto);
     void updateProgramLineStatus(ProgramDto programDto);
     void addHistoryMsg(HistoryDto historyDto);
     List<ProgramDto> findProgramList(Map<String,Object> map);
     int findTaskStatus(@PathParam("id") Integer id);
     int updateTaskStatus(Map<String,Object> params);
}

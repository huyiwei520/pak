package com.pak.controller;

import com.pak.common.ResultModel;
import com.pak.dto.HistoryDto;
import com.pak.dto.ProgramDto;
import com.pak.dto.TimeDto;
import com.pak.dto.UserDto;
import com.pak.operation.xiantou.PlanOne;
import com.pak.service.PKLotteryService;
import com.pak.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lujun.chen on 2017/3/13.
 */
@RestController
@RequestMapping("/")
public class PKLotteryController {

  @Autowired
  private PKLotteryService pkLotteryService;

    @Autowired
    PlanOne planOne;

  @RequestMapping(value="program",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
  public ResultModel getProgram(Authentication authentication,
                              @RequestParam(value = "programId", required = true) Integer programId){
      UserDto userDto = (UserDto) authentication.getPrincipal();
      ResultModel result = new ResultModel();
      Map<String,Object> params = new HashMap<String,Object>();
      params.put("userId",userDto.getUserId());
      params.put("programId",programId);
      ProgramDto programDto = pkLotteryService.findProgramById(params);
      result.setData(programDto);
      return result;
  }

    @RequestMapping(value="program",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ResultModel updateProgram(@RequestBody ProgramDto programDto,Authentication authentication){
        UserDto userDto = (UserDto) authentication.getPrincipal();
        ResultModel result = new ResultModel();
        programDto.setUserId(userDto.getUserId());
        pkLotteryService.updateProgram(programDto);
        result.setInfo("OK");
        return result;
    }

    @RequestMapping(value="history/list",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResultModel findHistoryList(Authentication authentication,
                                       Integer programId,
                                       Integer msgType,
                                       String position){
      ResultModel result = new ResultModel();
        UserDto userDto = (UserDto) authentication.getPrincipal();
      Map<String,Object> params = new HashMap<String,Object>();
      params.put("userId",userDto.getUserId());
      params.put("programId",programId);
      params.put("msgType",msgType);
      params.put("position",position);
      List<HistoryDto> hisList = pkLotteryService.findHistoryList(params);
      result.setData(hisList);
      return result;
    }

    @RequestMapping(value="program/list",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResultModel findProgramList(Authentication authentication){
        UserDto userDto = (UserDto) authentication.getPrincipal();
        ResultModel result = new ResultModel();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userId",userDto.getUserId());
        List<ProgramDto> programList = pkLotteryService.findProgramList(params);
        result.setData(programList);
        return result;
    }

    @RequestMapping(value="program/start",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ResultModel startPlan(@RequestBody ProgramDto programDto,
                                 Authentication authentication,
                                 HttpServletRequest request) throws ParseException, InterruptedException {
        ResultModel result = new ResultModel();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        planOne.submitPlan(userDto.getUserId(),programDto.getProgramId(),programDto.getStatus());
        return result;
    }

    @RequestMapping(value="result",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResultModel getResult(){
        ResultModel result = new ResultModel();
        Map<String,Object> params = new HashMap<String,Object>();
        TimeDto timeDto = pkLotteryService.findTimeResult(params);
        result.setData(timeDto);
        return result;
    }

    @RequestMapping(value="testmaotai",method= RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public ResultModel testmaotai(){
        ResultModel result = new ResultModel();
        Map<String,Object> params = new HashMap<String,Object>();
        pkLotteryService.testmaotai();
        return result;
    }
}

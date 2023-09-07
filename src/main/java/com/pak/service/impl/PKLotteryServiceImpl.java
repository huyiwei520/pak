package com.pak.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pak.common.Constants;
import com.pak.common.HttpClientUtils;
import com.pak.dto.*;
import com.pak.repository.IPKLotteryDao;
import com.pak.repository.UserDao;
import com.pak.service.PKLotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by huyiwei on 2018/9/26.
 */
@Component
public class PKLotteryServiceImpl implements PKLotteryService {

    @Autowired
    private IPKLotteryDao pklotteryDao;

    @Autowired
    private UserDao userDao;

    public void addPKResult(){
        int status = pklotteryDao.findTaskStatus(Constants.NUM_ONE);
        if(status ==0){
            Map<String,Object> parmas = new HashMap<>();
            parmas.put("id",Constants.NUM_ONE);
            try{
                parmas.put("status",Constants.NUM_ONE);
                pklotteryDao.updateTaskStatus(parmas);
                String url = "http://api.kaijiangtong.com/lottery/?name=bjpks&format=json&uid=916138&token=240ae54ac96736549d756937454518ad6c591187&num=1&format=json3";
                ResultDto resultDto = new ResultDto();
                Long period = 0l;
                Date openDate = null;
                String[] numArr = null;
                JSONArray jsonArr = HttpClientUtils.httpGet(url);
                if (jsonArr != null && jsonArr.size()>0){
                    for(int z=0;z<jsonArr.size();z++){
                        JSONObject jsonObj = JSONObject.parseObject(JSON.toJSONString(jsonArr.get(z)));
                        period = jsonObj.getLong("cTerm");
                        String numStr = jsonObj.getString("cTermResult");
                        openDate = jsonObj.getDate("cTermDT");
                        numArr = numStr.split(",");
                    }
                }
                parmas.put("period",period);
                int count = pklotteryDao.findResultCount(parmas);
                if (count <= 0) {
                    if (numArr != null){
                        int num1 = Integer.valueOf(numArr[0]);
                        int num2 = Integer.valueOf(numArr[1]);
                        int num3 = Integer.valueOf(numArr[2]);
                        int num4 = Integer.valueOf(numArr[3]);
                        int num5 = Integer.valueOf(numArr[4]);
                        int num6 = Integer.valueOf(numArr[5]);
                        int num7 = Integer.valueOf(numArr[6]);
                        int num8 = Integer.valueOf(numArr[7]);
                        int num9 = Integer.valueOf(numArr[8]);
                        int num10 = Integer.valueOf(numArr[9]);
                        resultDto.setPeriod(period);
                        resultDto.setNum1(num1);
                        resultDto.setNum2(num2);
                        resultDto.setNum3(num3);
                        resultDto.setNum4(num4);
                        resultDto.setNum5(num5);
                        resultDto.setNum6(num6);
                        resultDto.setNum7(num7);
                        resultDto.setNum8(num8);
                        resultDto.setNum9(num9);
                        resultDto.setNum10(num10);
                        resultDto.setOpenDate(openDate);
                        pklotteryDao.addPKResult(resultDto);
                        List<UserDto> userList = userDao.findUserList(parmas);
                        if(!CollectionUtils.isEmpty(userList)){
                            List<IdentifyDto> identifyList = new ArrayList<IdentifyDto>();
                            for(int i=0;i<userList.size();i++){
                                IdentifyDto identifyDto = new IdentifyDto();
                                UserDto userDto = userList.get(i);
                                Integer userId = userDto.getUserId();
                                identifyDto.setUserId(userId);
                                identifyDto.setPeriod(period);
                                identifyList.add(identifyDto);
                            }
                            pklotteryDao.addIdentify(identifyList);
                        }
                    }
                }
                parmas.put("status",Constants.NUM_ZERO);
                pklotteryDao.updateTaskStatus(parmas);
            }catch (Exception e){
                parmas.put("status",Constants.NUM_ZERO);
                pklotteryDao.updateTaskStatus(parmas);
            }
        }
    }

    public ResultDto findOpenResult(Map<String, Object> parmas) {
        return pklotteryDao.findOpenResult(parmas);
    }

    public List<ResultDto> getLastPositionData(Map<String, Object> map) {
        return null;
    }

    public List<HistoryDto> findHistoryList(Map<String, Object> map) {
        return pklotteryDao.findHistoryList(map);
    }

    public void updatePKFlag(Map<String, Object> map) {
        pklotteryDao.updatePKFlag(map);
    }

    public void updateDataFlag(Map<String, Object> map) {
        pklotteryDao.updateDataFlag(map);
    }

    public void updateOneFlag(Map<String, Object> map) {
        pklotteryDao.updateOneFlag(map);
    }

    public void updateTwoFlag(Map<String, Object> map) {
        pklotteryDao.updateTwoFlag(map);
    }

    public void updateThreeFlag(Map<String, Object> map) {
        pklotteryDao.updateThreeFlag(map);
    }

    public void updateFourFlag(Map<String, Object> map) {
        pklotteryDao.updateFourFlag(map);
    }

    public void updateFiveFlag(Map<String, Object> map) {
        pklotteryDao.updateFiveFlag(map);
    }

    public void updateSixFlag(Map<String, Object> map) {
        pklotteryDao.updateSixFlag(map);
    }

    public void updateSevenFlag(Map<String, Object> map) {
        pklotteryDao.updateSevenFlag(map);
    }

    public void updateEightFlag(Map<String, Object> map) {
        pklotteryDao.updateEightFlag(map);
    }

    public void updateNightFlag(Map<String, Object> map) {
        pklotteryDao.updateNightFlag(map);
    }

    public void updateTenFlag(Map<String, Object> map) {
        pklotteryDao.updateTenFlag(map);
    }

    public void addHistoryMsg(HistoryDto historyDto) {
        pklotteryDao.addHistoryMsg(historyDto);
    }

    public List<Integer> getLastPosition(Map<String, Object> map,int numType,int num) {
        HashSet<Integer> positionSetList = new HashSet<>();
        List<Integer> positionList = new ArrayList<>();
        int retult = 0;
        List<ResultDto> resultList = pklotteryDao.findResultList(map);
        if(!CollectionUtils.isEmpty(resultList)){
            for(int i=0;i<resultList.size();i++){
                ResultDto resultDto = resultList.get(i);
                if(num== Constants.NUM_ONE){
                    retult = resultDto.getNum1();
                }else if(num== Constants.NUM_TWO){
                    retult = resultDto.getNum2();
                }else if(num== Constants.NUM_THREE){
                    retult = resultDto.getNum3();
                }else if(num== Constants.NUM_FOUR){
                    retult = resultDto.getNum4();
                }else if(num== Constants.NUM_FIVE){
                    retult = resultDto.getNum5();
                }else if(num== Constants.NUM_SIX){
                    retult = resultDto.getNum6();
                }else if(num== Constants.NUM_SEVEN){
                    retult = resultDto.getNum7();
                }else if(num== Constants.NUM_EIGHT){
                    retult = resultDto.getNum8();
                }else if(num== Constants.NUM_NIGHT){
                    retult = resultDto.getNum9();
                }else if(num== Constants.NUM_TEN){
                    retult = resultDto.getNum10();
                }
                positionSetList.add(retult);
                if(numType== Constants.NUM_FIVE&&positionSetList.size()== Constants.NUM_FIVE){
                    break;
                }else if(numType ==Constants.NUM_SIX&&positionSetList.size()==Constants.NUM_SIX){
                    break;
                }
            }
            Iterator<Integer> its = positionSetList.iterator();
            while (its.hasNext()) {
                positionList.add(its.next());
            }
        }
        return positionList;
    }

    public ProgramDto findProgramById(Map<String, Object> map) {
        return pklotteryDao.findProgramById(map);
    }

    public void updateProgramStatus(ProgramDto programDto) {
        pklotteryDao.updateProgramStatus(programDto);
    }

    public void updateProgramLineStatus(ProgramDto programDto) {
        pklotteryDao.updateProgramLineStatus(programDto);
    }

    public List<ProgramDto> findProgramList(Map<String, Object> map) {
        return pklotteryDao.findProgramList(map);
    }

    public void updateProgram(ProgramDto dto) {
        pklotteryDao.updateProgram(dto);
    }

    @Override
    public TimeDto findTimeResult(Map<String, Object> map) {
        int minute = 0;
        TimeDto timeDto = new TimeDto();
        map.put("time",new Date());
        int openCount = pklotteryDao.findResultCount(map);
        ResultDto resultDto = pklotteryDao.findResultObject(map);
        Date openDate = resultDto.getOpenDate();
        Date systemDate = resultDto.getSystemDate();
        int sysminute = systemDate.getMinutes();
        int syssecond = systemDate.getSeconds();
        int openminute = openDate.getMinutes();
        int opensecond = openDate.getSeconds();
        minute = Math.abs(sysminute-openminute);
        if(minute>Constants.NUM_FOUR){
            minute = Constants.NUM_ONE;
        }else{
            minute = Constants.NUM_ONE-Math.abs(sysminute-openminute);
        }
        timeDto.setBeenCount(openCount);
        timeDto.setLeftCount(Constants.TOTAL_RESULT_COUNT-openCount);
        timeDto.setMinute(minute);
        timeDto.setCurrentperiod(resultDto.getPeriod());
        timeDto.setNextperiod(resultDto.getPeriod()+1);
        timeDto.setSecond(Constants.NUM_SIXTY-Math.abs(syssecond-opensecond));
        timeDto.setNum1(resultDto.getNum1());
        timeDto.setNum2(resultDto.getNum2());
        timeDto.setNum3(resultDto.getNum3());
        timeDto.setNum4(resultDto.getNum4());
        timeDto.setNum5(resultDto.getNum5());
        timeDto.setNum6(resultDto.getNum6());
        timeDto.setNum7(resultDto.getNum7());
        timeDto.setNum8(resultDto.getNum8());
        timeDto.setNum9(resultDto.getNum9());
        timeDto.setNum10(resultDto.getNum10());
        return timeDto;
    }

    public Map<Integer,List<Integer>> findLastDoubleThreeSpace(Map<String,Object> parmas){
        Map<Integer,List<Integer>> returnMapSet = null;
        int lastNum11 = 0;
        int lastNum12 = 0;
        int lastNum13 = 0;
        int lastNum14 = 0;
        int lastNum15 = 0;
        int lastNum16 = 0;
        parmas.put("limitCount", 6);
        List<ResultDto> list = pklotteryDao.findResultList(parmas);
        if (list !=null && list.size()==6){
            for(int i=0;i<list.size();i++){
                if(i+1==list.size()){
                    break;
                }
                int position = 0;
                boolean outFlag = false;
                for(int j=1;j<=10;j++){
                    LinkedHashSet<Integer> setInt = new LinkedHashSet<>();
                    ResultDto oneObj = list.get(i);
                    ResultDto twoObj = list.get(i+1);
                    ResultDto threeObj = list.get(i+2);
                    ResultDto fourObj = list.get(i+3);
                    ResultDto fiveObj = list.get(i+4);
                    ResultDto sixObj = list.get(i+5);
                    if(j==1){
                        lastNum11 = oneObj.getNum1();
                        lastNum12 = twoObj.getNum1();
                        lastNum13 = threeObj.getNum1();
                        lastNum14 = fourObj.getNum1();
                        lastNum15 = fiveObj.getNum1();
                        lastNum16 = sixObj.getNum1();
                    }else if(j==2){
                        lastNum11 = oneObj.getNum2();
                        lastNum12 = twoObj.getNum2();
                        lastNum13 = threeObj.getNum2();
                        lastNum14 = fourObj.getNum2();
                        lastNum15 = fiveObj.getNum2();
                        lastNum16 = sixObj.getNum2();
                    }else if(j==3){
                        lastNum11 = oneObj.getNum3();
                        lastNum12 = twoObj.getNum3();
                        lastNum13 = threeObj.getNum3();
                        lastNum14 = fourObj.getNum3();
                        lastNum15 = fiveObj.getNum3();
                        lastNum16 = sixObj.getNum3();
                    }else if(j==4){
                        lastNum11 = oneObj.getNum4();
                        lastNum12 = twoObj.getNum4();
                        lastNum13 = threeObj.getNum4();
                        lastNum14 = fourObj.getNum4();
                        lastNum15 = fiveObj.getNum4();
                        lastNum16 = sixObj.getNum4();
                    }else if(j==5){
                        lastNum11 = oneObj.getNum5();
                        lastNum12 = twoObj.getNum5();
                        lastNum13 = threeObj.getNum5();
                        lastNum14 = fourObj.getNum5();
                        lastNum15 = fiveObj.getNum5();
                        lastNum16 = sixObj.getNum5();
                    }else if(j==6){
                        lastNum11 = oneObj.getNum6();
                        lastNum12 = oneObj.getNum6();
                        lastNum13 = oneObj.getNum6();
                        lastNum14 = oneObj.getNum6();
                        lastNum15 = oneObj.getNum6();
                        lastNum16 = oneObj.getNum6();
                    }else if(j==7){
                        lastNum11 = oneObj.getNum7();
                        lastNum12 = twoObj.getNum7();
                        lastNum13 = threeObj.getNum7();
                        lastNum14 = fourObj.getNum7();
                        lastNum15 = fiveObj.getNum7();
                        lastNum16 = sixObj.getNum7();
                    }else if(j==8){
                        lastNum11 = oneObj.getNum8();
                        lastNum12 = twoObj.getNum8();
                        lastNum13 = threeObj.getNum8();
                        lastNum14 = fourObj.getNum8();
                        lastNum15 = fiveObj.getNum8();
                        lastNum16 = sixObj.getNum8();
                    }else if(j==9){
                        lastNum11 = oneObj.getNum9();
                        lastNum12 = twoObj.getNum9();
                        lastNum13 = threeObj.getNum9();
                        lastNum14 = fourObj.getNum9();
                        lastNum15 = fiveObj.getNum9();
                        lastNum16 = sixObj.getNum9();
                    }else if(j==10){
                        lastNum11 = oneObj.getNum10();
                        lastNum12 = twoObj.getNum10();
                        lastNum13 = threeObj.getNum10();
                        lastNum14 = fourObj.getNum10();
                        lastNum15 = fiveObj.getNum10();
                        lastNum16 = sixObj.getNum10();
                    }
                    setInt.add(lastNum14);
                    setInt.add(lastNum15);
                    setInt.add(lastNum16);

                    if((lastNum11%2!=0&&lastNum12%2!=0&&lastNum13%2!=0&&lastNum14%2==0&&lastNum15%2==0&&lastNum16%2==0)&&setInt.size()>=2){
                        //3单3双，则买(双)
                        returnMapSet = new HashMap<>();
                        List<Integer> numList = new ArrayList<>();
                        position = j;
                        Iterator<Integer> ite = setInt.iterator();
                        while (ite.hasNext()) {
                            Integer num = ite.next();
                            numList.add(num);
                            if(numList.size()==2){
                                break;
                            }
                        }
                        returnMapSet.put(position, numList);
                        outFlag = true;
                        break;
                    }else if((lastNum11%2==0&&lastNum12%2==0&&lastNum13%2==0&&lastNum14%2!=0&&lastNum15%2!=0&&lastNum16%2!=0)&&setInt.size()>=2){
                        //3双3单，则买(单)
                        returnMapSet = new HashMap<>();
                        List<Integer> numList = new ArrayList<>();
                        position = j;
                        Iterator<Integer> ite = setInt.iterator();
                        while (ite.hasNext()) {
                            Integer num = ite.next();
                            numList.add(num);
                            if(numList.size()==2){
                                break;
                            }
                        }
                        returnMapSet.put(position, numList);
                        outFlag = true;
                        break;
                    }else if((lastNum11>5&&lastNum12>5&&lastNum13>5&&lastNum14<=5&&lastNum15<=5&&lastNum16<=5)&&setInt.size()>=2){
                        //3大3小，则买(小)
                        returnMapSet = new HashMap<>();
                        List<Integer> numList = new ArrayList<>();
                        position = j;
                        Iterator<Integer> ite = setInt.iterator();
                        while (ite.hasNext()) {
                            Integer num = ite.next();
                            numList.add(num);
                            if(numList.size()==2){
                                break;
                            }
                        }
                        returnMapSet.put(position, numList);
                        outFlag = true;
                        break;
                    }else if((lastNum11<=5&&lastNum12<=5&&lastNum13<=5&&lastNum14>5&&lastNum15>5&&lastNum16>5)&&setInt.size()>=2){
                        //3小3大，则买(大)
                        returnMapSet = new HashMap<>();
                        List<Integer> numList = new ArrayList<>();
                        position = j;
                        Iterator<Integer> ite = setInt.iterator();
                        while (ite.hasNext()) {
                            Integer num = ite.next();
                            numList.add(num);
                            if(numList.size()==2){
                                break;
                            }
                        }
                        returnMapSet.put(position, numList);
                        outFlag = true;
                        break;
                    }
                }
                if(outFlag){
                    break;
                }
            }
        }
        return returnMapSet;
    }

    public int findLastEightCoolNumber(Map<String,Object> parmas,Integer num1,Integer num2){
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        int count7 = 0;
        int count8 = 0;
        int count9 = 0;
        int count10 = 0;
        int returnPosition = 0;
        List<Integer> countList = new ArrayList<Integer>();
        List<Integer> twoList = new ArrayList<Integer>();
        List<Integer> dataList = new ArrayList<Integer>();
        parmas.put("limitCount", 80);
        twoList.add(num1);
        twoList.add(num2);
        List<ResultDto> list = pklotteryDao.findResultList(parmas);
        if (list !=null && list.size()==80){
            for(int i=0;i<list.size();i++){
                ResultDto obj = list.get(i);
                Integer lastNum1 = obj.getNum1();
                if(twoList.contains(lastNum1)){
                    String space = i+"1";
                    countList.add(Integer.valueOf(space));
                    break;
                }
            }
            for(int i=0;i<list.size();i++){
                ResultDto obj = list.get(i);
                Integer lastNum2 = obj.getNum2();
                if(twoList.contains(lastNum2)){
                    String space = i+"2";
                    countList.add(Integer.valueOf(space));
                    break;
                }
            }
            for(int i=0;i<list.size();i++){
                ResultDto obj = list.get(i);
                Integer lastNum3 = obj.getNum3();
                if(twoList.contains(lastNum3)){
                    String space = i+"3";
                    countList.add(Integer.valueOf(space));
                    break;
                }
            }
            for(int i=0;i<list.size();i++){
                ResultDto obj = list.get(i);
                Integer lastNum4 = obj.getNum4();
                if(twoList.contains(lastNum4)){
                    String space = i+"4";
                    countList.add(Integer.valueOf(space));
                    break;
                }
            }
            for(int i=0;i<list.size();i++){
                ResultDto obj = list.get(i);
                Integer lastNum5 = obj.getNum5();
                if(twoList.contains(lastNum5)){
                    String space = i+"5";
                    countList.add(Integer.valueOf(space));
                    break;
                }
            }
            for(int i=0;i<list.size();i++){
                ResultDto obj = list.get(i);
                Integer lastNum6 = obj.getNum6();
                if(twoList.contains(lastNum6)){
                    String space = i+"6";
                    countList.add(Integer.valueOf(space));
                    break;
                }
            }
            for(int i=0;i<list.size();i++){
                ResultDto obj = list.get(i);
                Integer lastNum7 = obj.getNum7();
                if(twoList.contains(lastNum7)){
                    String space = i+"7";
                    countList.add(Integer.valueOf(space));
                    break;
                }
            }
            for(int i=0;i<list.size();i++){
                ResultDto obj = list.get(i);
                Integer lastNum8 = obj.getNum8();
                if(twoList.contains(lastNum8)){
                    String space = i+"8";
                    countList.add(Integer.valueOf(space));
                    break;
                }
            }
            for(int i=0;i<list.size();i++){
                ResultDto obj = list.get(i);
                Integer lastNum9 = obj.getNum9();
                if(twoList.contains(lastNum9)){
                    String space = i+"9";
                    countList.add(Integer.valueOf(space));
                    break;
                }
            }
            for(int i=0;i<list.size();i++){
                ResultDto obj = list.get(i);
                Integer lastNum10 = obj.getNum10();
                if(twoList.contains(lastNum10)){
                    String space = i+"0";
                    countList.add(Integer.valueOf(space));
                    break;
                }
            }
            Collections.sort(countList);
            Integer strPosition = countList.get(countList.size()-1);
            String numStr = String.valueOf(strPosition);
            String nn = numStr.substring(numStr.length()-1, numStr.length());
            returnPosition = Integer.valueOf(nn);
            if(returnPosition==0){
                returnPosition = 10;
            }
        }
        return returnPosition;
    }

    public void testmaotai(){
        HttpClientUtils.doPostObj();
    }

    public static void main(String[] args) {
        System.out.println(9&8);
    }
}

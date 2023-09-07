$(function(){
    var domainUrl = window.location.host;
    var minit = 0;
    var second = 0;
    var beenCount = 0;
    var leftCount = 0;
    var result = function(){
        var resultObj = new Object();
        resultObj.getOpenResult = function(){
            var trContent = "";
            var hisUrl = domainUrl+"/pak/result";
            $.ajax({
                url : "http://"+hisUrl,
                type : 'get',
                dataType : 'json',
                success: function(data){
                    var resultObj = eval(data);
                    if(resultObj != null && resultObj != undefined){
                        var numArr = [];
                        var resultJsonObj = resultObj.data;
                        minit = resultJsonObj.minute;
                        second = resultJsonObj.second;
                        beenCount = resultJsonObj.beenCount;
                        leftCount = resultJsonObj.leftCount;
                        $("#pk10_publish").empty().append(beenCount);
                        $("#pk10_level").empty().append(leftCount);
                        $("#pk10_curperiod").empty().append(resultJsonObj.currentperiod);
                        $("#pk10_period").empty().append(resultJsonObj.nextperiod);
                        numArr.push(resultJsonObj.num1);
                        numArr.push(resultJsonObj.num2);
                        numArr.push(resultJsonObj.num3);
                        numArr.push(resultJsonObj.num4);
                        numArr.push(resultJsonObj.num5);
                        numArr.push(resultJsonObj.num6);
                        numArr.push(resultJsonObj.num7);
                        numArr.push(resultJsonObj.num8);
                        numArr.push(resultJsonObj.num9);
                        numArr.push(resultJsonObj.num10);
                        $("#pk10_nums").html("");
                        var openNumHtml = "";
                        for(var k = 0;k<numArr.length;k++){
                            var num = numArr[k];
                            if(num == 1){
                                openNumHtml = openNumHtml+"<li class='nub01'></li>";
                            }else if(num == 2){
                                openNumHtml = openNumHtml+"<li class='nub02'></li>";
                            }else if(num == 3){
                                openNumHtml = openNumHtml+"<li class='nub03'></li>";
                            }else if(num == 4){
                                openNumHtml = openNumHtml+"<li class='nub04'></li>";
                            }else if(num == 5){
                                openNumHtml = openNumHtml+"<li class='nub05'></li>";
                            }else if(num == 6){
                                openNumHtml = openNumHtml+"<li class='nub06'></li>";
                            }else if(num == 7){
                                openNumHtml = openNumHtml+"<li class='nub07'></li>";
                            }else if(num == 8){
                                openNumHtml = openNumHtml+"<li class='nub08'></li>";
                            }else if(num == 9){
                                openNumHtml = openNumHtml+"<li class='nub09'></li>";
                            }else if(num == 10){
                                openNumHtml = openNumHtml+"<li class='nub10'></li>";
                            }
                        }
                        $("#pk10_nums").html(openNumHtml);
                    }
                },
                error:function(){
                    alert("get result data error");
                }
            });
        }
        return resultObj;
    };

    openResult = function(){
        var totalSecond = minit*60;
        $(function(){
            setTimeout(function(){
                var r1 = new result();
                r1.getOpenResult();
            },totalSecond*1000);
            after();
        });
        //自动刷新页面上的时间
        function after(){
            totalSecond=totalSecond-1;
            if(totalSecond%60==0){
                minit = minit-1;
                second = 60;
            }else{
                second = second-1;
            }
            $("#pk10_minute").empty().append(minit);
            $("#pk10_second").empty().append(second);
            setTimeout(function(){
                after();
            },1000);
        }
    }
    var r = new result();
    r.getOpenResult();
    openResult();
});
$(function(){
    var domainUrl = window.location.host;
    $('#main').load('html/dialog_program.html');
    var history = function(){
        var hisObj = new Object();
        var programId = 1;
        hisObj.getHistoryList = function(){
            var trContent = "";
            var hisUrl = domainUrl+'/pak/history/list?programId='+programId;
            $.ajax({
                url : "http://"+hisUrl,
                type : 'get',
                dataType : 'json',
                success: function(data){
                    var historyObj = eval(data);
                    if(historyObj != null && historyObj != undefined){
                        var hisArr = historyObj.data;
                        for(var i=0;i<hisArr.length;i++){
                            var his = hisArr[i];
                            trContent = trContent+"<tr><td>"+his.description+"</td><td>"+his.period+"</td>" +
                                "<td>"+his.msg+"</td><td>"+his.createTime+"</td></tr>";
                        }
                        $("#historyData").html(trContent);
                    }
                },
                error:function(){
                    alert("get history data error");
                }
            });
        }
        return hisObj;
    };

    var program = function(){
        var proObj = new Object();
        proObj.getProgramList = function(){
            var programUrl = domainUrl+'/pak/program/list';
            $.ajax({
                url : "http://"+programUrl,
                type : 'get',
                dataType : 'json',
                success: function(data){
                    var programObj = eval(data);
                    if(programObj != null && programObj != undefined){
                        var programArr = programObj.data;
                        for(var i=0;i<programArr.length;i++){
                            var program = programArr[i];
                            var msg = "";
                            if(program.status==1){
                                msg = "运行中";
                            }else{
                                msg = "启动";
                            }
                            $("#programListtable").append("<tr style='height: 35px; '><td>"+program.numCount+"数 追"+program.passCount+"盘计划</td><td>"+program.description+"</td><td><input type='button' value='"+msg+"' class='programbtn' onclick='startProgram("+program.programId+","+program.status+")'>"+"<input type='button' value='设置规则' class='programbtn' onclick='showBg("+program.programId+");'>" +"<input type='button' value='投注记录' class='programbtn' onclick='linktoHistory("+program.programId+")'></td></tr>");
                        }
                    }
                },
                error:function(){
                    alert("get program data error");
                }
            });
        }

        proObj.getProgramById = function(programId){
            var programUrl = domainUrl+'/pak/program?programId='+programId;
            $.ajax({
                url : "http://"+programUrl,
                type : 'get',
                dataType : 'json',
                success: function(data){
                    var programObj = eval(data);
                    if(programObj != null && programObj != undefined){
                        var programObj = programObj.data;
                        $("#passCount").val(programObj.passCount);
                        $("#numCount").val(programObj.numCount);
                        $("#description").val(programObj.description);
                        $("#oneMoney").val(programObj.oneMoney);
                        $("#twoMoney").val(programObj.twoMoney);
                        $("#threeMoney").val(programObj.threeMoney);
                        $("#fourMoney").val(programObj.fourMoney);
                        $("#fiveMoney").val(programObj.fiveMoney);
                        $("#sixMoney").val(programObj.sixMoney);
                        $("#sevenMoney").val(programObj.sevenMoney);
                        $("#eightMoney").val(programObj.eightMoney);
                        $("#programId").val(programId);
                        $("#num1").val(programObj.num1);
                        $("#num2").val(programObj.num2);
                        $("#num3").val(programObj.num3);
                        $("#num4").val(programObj.num4);
                    }
                },
                error:function(){
                    alert("get program data error");
                }
            });
        }
        return proObj;
    };

    startProgram = function(programId,status){
        var program = {};
        var programUrl = domainUrl+'/pak/program/start';
        program.programId = programId;
        program.status = status;
        $.ajax({
            url : "http://"+programUrl,
            data:JSON.stringify(program),
            contentType : "application/json",
            async : true,
            type : 'POST',
            dataType : 'json',
            success: function(data){
                if(data != null && data != undefined && data.status == 0){
                    window.location.reload();
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert("start program error");
            }
        });
    };

    saveProgramRule = function(){
        var proObj = {};
        var programUrl = "http://"+domainUrl+'/pak/program';
        proObj.programId = $("#programId").val();
        proObj.passCount = $("#passCount").val();
        proObj.numCount = $("#numCount").val();
        proObj.oneMoney = $("#oneMoney").val();
        proObj.twoMoney = $("#twoMoney").val();
        proObj.threeMoney = $("#threeMoney").val();
        proObj.fourMoney = $("#fourMoney").val();
        proObj.fiveMoney = $("#fiveMoney").val();
        proObj.sixMoney = $("#sixMoney").val();
        proObj.sevenMoney = $("#sevenMoney").val();
        proObj.eightMoney = $("#eightMoney").val();
        proObj.description = $("#description").val();
        proObj.num1 = $("#num1").val();
        proObj.num2 = $("#num2").val();
        proObj.num3 = $("#num3").val();
        proObj.num4 = $("#num4").val();


        $.ajax({
            url : programUrl,
            data:JSON.stringify(proObj),
            contentType : "application/json",
            async : true,
            type : 'POST',
            dataType : 'json',
            success: function(data){
                if(data != null && data != undefined && data.status == 0){
                    window.location.reload();
                }
            },
            error:function(){
                alert("save program error");
            }
        });
    }

    showBg = function(programId) {
        var p = new program();
        var bh = $("body").height();
        var bw = $("body").width();
        $("#fullbg").css({
            height:bh,
            width:bw,
            display:"block"
        });
        p.getProgramById(programId);
        $("#dialog").show();
    }

    closeBg = function() {
        $("#fullbg,#dialog").hide();
    }

    linktoHistory = function(programId){
        window.open("/pak/historylist?programId="+programId);
    }

    var p = new program();
    p.getProgramList();
})
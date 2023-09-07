$(function(){
    var firstProgramId = 0;
    var acceptProgramId = window.location.search.substring(1);
    if(acceptProgramId != ""){
        var paramIds = acceptProgramId.split("=");
        acceptProgramId = paramIds[1];
    }
    $(".selectTravelCity").change(function(){
        changeHistoryList();
    });

    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S": this.getMilliseconds()
        }
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    }

    var domainUrl = window.location.host;
    var history = function(){
        var hisObj = new Object();
        hisObj.getHistoryList = function(programId){
            var trContent = "";
            $("#programListbody").html("");
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
                            $("#programListbody").append("<tr class='historylisttr' style='height: 35px; '><td style='width: 20%;border: solid 1px #ccc;'>"+his.description+"</td>" +
                                "<td style='width: 12%;border: solid 1px #ccc;'>"+his.period+"</td>" +
                                "<td style='width: 50%;border: solid 1px #ccc;'>"+his.msg+"</td>" +
                                "<td style='width: 18%;border: solid 1px #ccc;'>"+formatDate(his.createTime)+"</td></tr>");
                        }
                    }
                },
                error:function(){
                    alert("get history data error");
                }
            });
        }
        return hisObj;
    };

    var program =function(){
        var programObj = new Object();
        programObj.showProgramList = function(programId){
            var programUrl = domainUrl+'/pak/program/list';
            $("#selectTravelCity").html("");
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
                            $("#selectTravelCity") .append("<option value='"+program.programId+"'>"+program.numCount+"数追"+program.passCount+"盘计划</option>");
                        }
                        if(programId != ""){
                            var optobj = document.getElementById("selectTravelCity");
                            for(var i=0;i<optobj.length;i++){
                                if(optobj[i].value == programId)
                                    optobj[i].selected = true;
                            }
                        }
                    }
                },
                error:function(){
                    alert("get program data error");
                }
            });
        }
        return programObj;
    }

    function changeHistoryList(){
        var h = new history();
        var options=$("#selectTravelCity option:selected");
        var programId = options.val();
        h.getHistoryList(programId);
    }

    function formatDate(ltime){
        var dt = new Date(ltime*1000);
        var pattern = "yyyy-MM-dd hh:mm:ss";
        return dt.format(pattern);
    }

    var h = new history();
    var p = new program();
    p.showProgramList(acceptProgramId);
    h.getHistoryList(acceptProgramId);
})
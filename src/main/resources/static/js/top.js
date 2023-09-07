$(function(){
    var url = "http://"+window.location.host;
    $("#logoimg").attr("src", url+"/pak/images/logo.jpg");
    $("#opta").attr("href",url+"/pak/index.html");
    $("#history").attr("href",url+"/pak/html/historylist.html");
    $("ul").on("click","li",function(){
        $("li").addClass("mentlicss").siblings().removeClass("mentlicss");
    })
});
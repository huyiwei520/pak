$(function(){
    var domainUrl = window.location.host;

    loginMethod = function() {
        var username = $("#username").val();
        var password = $("#password").val();
        alert("aa");
        var userObj = {};
        var loginUrl = domainUrl+'/pak/user/login';
        alert(loginUrl);
        userObj.username = username;
        userObj.password = password;
        $.ajax({
            url : "http://"+loginUrl,
            data:JSON.stringify(userObj),
            contentType : "application/json",
            async : true,
            type : 'POST',
            dataType : 'json',
            success: function(data){
                if(data != null && data != undefined && data.status == 0){
                    window.location.href=domainUrl+"/pak/index.html";
                }
            },
            error:function(){
                alert("login error");
            }
        });
    }
})
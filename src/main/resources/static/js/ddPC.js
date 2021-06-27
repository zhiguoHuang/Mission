dd.config({
    agentId : _config.agentId,
    corpId : _config.corpId,
    timeStamp : _config.timeStamp,
    nonceStr : _config.nonceStr,
    signature : _config.signature,
    jsApiList : [ 'runtime.info', 'biz.contact.choose',
        'device.notification.confirm', 'device.notification.alert',
        'device.notification.prompt', 'biz.ding.post',
        'biz.util.openLink' ]
});


dd.ready(function() {


    dd.runtime.info({
        onSuccess : function(info) {
            logger.e('runtime info: ' + JSON.stringify(info));
        },
        onFail : function(err) {
            logger.e('fail: ' + JSON.stringify(err));
        }
    });



    dd.runtime.permission.requestAuthCode({
        corpId : _config.corpId,
        onSuccess : function(info) {
            alert(info.code);
            $.ajax({
                url : '/userinfo?code=' + info.code + '&corpid='
                    + _config.corpId,
                type : 'GET',
                success : function(data, status, xhr) {
                    document.getElementById("userName").innerHTML = data.userName;
                    document.getElementById("userId").innerHTML = data.userId;

                    $("#name").val("admin");
                    $("#password").val("123456");


                    // 图片
                    // if(info.avatar.length != 0){
                    //     var img = document.getElementById("userImg");
                    //     img.src = info.avatar;
                    //     img.height = '100';
                    //     img.width = '100';
                    // }

                },
                error : function(xhr, errorType, error) {
                    logger.e("yinyien:" + _config.corpId);
                    alert(errorType + ', ' + error);
                }
            });

        },
        onFail : function(err) {
            alert('fail: ' + JSON.stringify(err));
        }
    });
});

dd.error(function(err) {
    alert('dd error: ' + JSON.stringify(err));
});

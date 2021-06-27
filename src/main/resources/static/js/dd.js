
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




/*

*在dd.config()验证通过的情况下，就会执行ready()函数，

*dd.ready参数为回调函数，在环境准备就绪时触发，jsapi的调用需要保证在

*该回调函数触发后调用，否则无效,所以你会发现所有对jsapi接口的调用都会在

*ready的回调函数里面

*/

dd.ready(function() {

    /*

    *获取容器信息，返回值为ability:版本号，也就是返回容器版本

    *用来表示这个版本的jsapi的能力，来决定是否使用jsapi

    */

    dd.runtime.info({

        onSuccess : function(info) {

            logger.e('runtime info: ' + JSON.stringify(info));

        },

        onFail : function(err) {

            logger.e('fail: ' + JSON.stringify(err));

        }

    });


    /*

    *获得免登授权码，需要的参数为corpid，也就是企业的ID

    *成功调用时返回onSuccess,返回值在function的参数info中，具体操作可以在function中实现

    *返回失败时调用onFail

    */
    dd.runtime.permission.requestAuthCode({
        corpId : _config.corpId,
        onSuccess : function(info) {
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



/*

*在dd.config函数验证没有通过下执行这个函数

*/

dd.error(function(err) {
    alert('dd error: ' + JSON.stringify(err));

});



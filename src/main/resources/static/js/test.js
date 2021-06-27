dd.ready(function() {

    dd.runtime.permission.requestAuthCode({
        corpId: _config.corpId,

        onSuccess: function (res) {
            // 调用成功时回调
            alert(res.code);
            $.ajax({
                url : '/userinfo?code=' + res.code + '&corpid='
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



            console.log(res)
        },
        onFail: function (err) {
            // 调用失败时回调
            console.log(err)
        }
    });
});
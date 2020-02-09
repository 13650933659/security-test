$(function(){
alert('login');
    $('#smsCodeBtn').click(function () {
        alert('请填写手机');
        var mobile = $('#mobile').val();
        if (!mobile) {
            alert('请填写手机');
            return false;
        }

        // $.ajax({
        //     type: "GET",
        //     url: "/code/sms?mobile=" + mobile,
        //     success: function (msg) {
        //         alert('验证码发送成功！');
        //     }
        // });
    });



});
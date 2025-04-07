$(function() {
    $('#loginForm').submit(function(e) {
        e.preventDefault();

        var userName = $("#username").val();
        var password = $("#password").val();
        if (isNull(userName)) {
            showErrorInfo("ユーザー名を入力してください。");
            return;
        }
        if (!validUserName(userName)) {
            showErrorInfo("入力したユーザー名は正しくない。");
            return;
        }
        if (isNull(password)) {
            showErrorInfo("パスワードを入力してください。");
            return;
        }
        if (!validPassword(password)) {
            showErrorInfo("入力したパスワードは正しくない");
            return;
        }

        const formData = {
            username: $('#username').val(),
            password: $('#password').val(),
            "remember-me": $('#remember-me').prop('checked'),
            _csrf: $('input[name="_csrf"]').val()
        };

        $.ajax({
            type: "POST",
            url: $(this).attr('action'),
            data: $.param(formData),
            success: function (result) {
                if (result.resultCode == 200) {
                    $('.alert-danger').css("display", "none");
                    // Spring security will do , no need anymoe
                    if (result.data != undefined && result.data !=null) {
                        setCookie("remember-me", result.data.userToken);
                    }
                    window.location.href = "/IdeageSys";
                }

                if (result.resultCode == 500) {
                    showErrorInfo("ログイン失敗！");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                const errorResponse = JSON.parse(jqXHR.responseText);
                console.error("Server error details:", errorResponse.message);
                $('.alert-danger').css("display", "none");
                showErrorInfo(errorResponse.message);
            }
        });
    });
    // jQuery code here
});
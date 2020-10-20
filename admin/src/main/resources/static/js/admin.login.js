$(function() {
    $("#submit").on('click', function () {
        var $username = $("#username").val();
        var $password = $("#password").val();
//      var $autologin =$("#autologin").val();
        if ($username == '' || $password == '') {
            alert("用户名及密码不能为空");
            return false;
        } else {
            var datas = {
                username: $username,
                password: $password
            };
            //console.log(datas);
            $.ajax({
                url: '/admin/login',
                type: 'post',
                dataType: 'json',
                data: datas,
                success: function (json) {
                    console.log(json);
                    if (json.status == -2) {
                        alert('用户名不存在');
                    } else if (json.status == -1) {
                        alert("密码错误");
                    } else if(json.authority == -1){
                        localStorage.setItem("username",$username);
                        localStorage.setItem("viewname",$username);
                        window.location.href = 'center/center_index.html';//普通用户跳转到交流中心
                    } else {
                        localStorage.setItem("username",$username);
                        localStorage.setItem("viewname",$username);
                        window.location.href = 'index.html';//登录成功跳转
                    }
                },
                error: function () {
                    alert('false');
                }
            })
        }
        return false;
    })
});
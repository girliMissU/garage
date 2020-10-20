// 获取cookie值(key)
function getCookie(cname)
{
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++)
    {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) return c.substring(name.length,c.length);
    }
    return "";
}

function if_login(){
    var $ticket= getCookie("ticket")
    console.log($ticket);
    if($ticket != ""){
        var datas = {
            ticket: $ticket
        };
        //判断ticket是否有效
        $.ajax({
            url: '/admin/ifTicket',
            type: 'post',
//          dataType: 'json',
            data: datas,
            success: function (json) {
                console.log(json);
                if (json == "0") {
                    window.location.href = 'login.html';//未登录跳转到登录界面
                }
            },
            error: function () {
                alert('false');
            }
        })
    }else{
        window.location.href = 'login.html';//未登录跳转到登录界面
    }
}

function logout(){
    $.ajax({
        url: '/admin/logout',
        type: 'post',
//                            dataType: 'json',
        //data: datas,
        success: function (json) {
            console.log(json);
            if (json == "1") {
                window.location.href = 'login.html';//登录成功跳转
            }
        },
        error: function () {
            alert('false');
        }
    })
}

//添加用户名到页面
var $username = localStorage.getItem("username");
$("#username").html($username);



